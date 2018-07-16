package com.canehealth.service;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.exceptions.FHIRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class InjectorService {
    private final Logger log = LoggerFactory.getLogger(InjectorService.class);
    private final IGenericClient fhirClient;
    @Value("${spring.application.uri}")
    protected String uri = "http://canehealth.com/fhirform/";
    @Value("${spring.application.demap}")
    protected String demap = "http://hl7.org/fhir/StructureDefinition/questionnaire-deMap";

    public InjectorService(IGenericClient fhirClient) {
        super();
        this.fhirClient = fhirClient;
    }

    /* function to check if url valid */
    private static boolean urlValidator(String url) {
        /*validating url*/
        try {
            new URL(url).toURI();
            return true;
        } catch (URISyntaxException exception) {
            return false;
        } catch (MalformedURLException exception) {
            return false;
        }
    }

    public Questionnaire processQuestionnaire(Questionnaire questionnaire) {

        log.info("Processing Questionnaire: {}", questionnaire.getId());
        List<Questionnaire.QuestionnaireItemComponent> empty_list = new ArrayList<>();
        Questionnaire newQ = questionnaire.copy();
        newQ.setItem(empty_list);
        if (questionnaire.getItem().isEmpty())
            return questionnaire;

        for (Questionnaire.QuestionnaireItemComponent itemComponent : questionnaire.getItem()) {
            newQ = processItem(newQ, itemComponent);
        }

        return newQ;
    }

    private Questionnaire processItem(Questionnaire questionnaire, Questionnaire.QuestionnaireItemComponent itemComponent) {

        if (itemComponent.getType() == Questionnaire.QuestionnaireItemType.REFERENCE) {
            try {
                Reference reference = itemComponent.getInitialReference();
                String extension_value = reference.getReference();
                if (urlValidator(extension_value)) {
                    DataElement dataElement = fhirClient.read().resource(DataElement.class).withUrl(extension_value).execute();
                    log.info("About to inject: {}", extension_value);
                    questionnaire = inject(questionnaire, dataElement);
                }

            } catch (FHIRException e) {
                e.printStackTrace();
            }

        } else if (itemComponent.getType() == Questionnaire.QuestionnaireItemType.GROUP) {
            for (Questionnaire.QuestionnaireItemComponent item : itemComponent.getItem()) {
                processItem(questionnaire, item);
            }
        } else {
            questionnaire.addItem(itemComponent);
        }
        return questionnaire;
    }



    public Questionnaire inject(Questionnaire questionnaire, DataElement dataElement) {
        // Add *contained* to the Questionnaire
        for (Resource res : dataElement.getContained()) {
            questionnaire.addContained(res);
        }

        ElementDefinition elementDefinition = dataElement.getElementFirstRep();
        Questionnaire.QuestionnaireItemComponent newItem = new Questionnaire.QuestionnaireItemComponent();

        newItem.setLinkId(elementDefinition.getPath());
        newItem.setCode(elementDefinition.getCode());


        if(elementDefinition.getDefinition() == "STRING")
            newItem.setType(Questionnaire.QuestionnaireItemType.STRING);
        if(elementDefinition.getDefinition() == "CHOICE")
            newItem.setType(Questionnaire.QuestionnaireItemType.CHOICE);
        if(elementDefinition.getDefinition() == "NUMBER")
            newItem.setType(Questionnaire.QuestionnaireItemType.DECIMAL);
        if(elementDefinition.getDefinition() == "DATETIME")
            newItem.setType(Questionnaire.QuestionnaireItemType.DATETIME);

        newItem.setText(elementDefinition.getLabel());

        questionnaire.addItem(newItem);

        return questionnaire;
    }


}
