package com.canehealth.interceptor;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.InterceptorAdapter;
import com.canehealth.service.InjectorService;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.hl7.fhir.dstu3.model.QuestionnaireResponse;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.canehealth")
public class DataElementResponseInterceptor extends InterceptorAdapter {

    @Autowired
    private InjectorService injectorService;

    /**
     * Intercepts outgoing response and pass them through the injectorService
     *
     * @param theRequestDetails
     * @param theResponseObject
     * @return boolean
     */
    @Override
    public boolean outgoingResponse(RequestDetails theRequestDetails, IBaseResource theResponseObject) {
        if (theResponseObject instanceof Resource) {
            if (theResponseObject.getClass() == Questionnaire.class) {
                Questionnaire questionnaire = (Questionnaire) theResponseObject;
                theResponseObject = injectorService.processQuestionnaire(questionnaire);
            }
        }

        return true;
    }

    /**
     * Transforms any QuestionnaireResponse to CDM for storage in database
     *
     * @param theOperation
     * @param theProcessedRequest
     */
    @Override
    public void incomingRequestPreHandled(RestOperationTypeEnum theOperation, ActionRequestDetails theProcessedRequest) {
        super.incomingRequestPreHandled(theOperation, theProcessedRequest);
        IBaseResource baseResource = theProcessedRequest.getResource();
        if (baseResource instanceof Resource) {
            if (baseResource.getClass() == QuestionnaireResponse.class) {
                QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) baseResource;
                // TODO: to process the QuestionnaireResponse here.
            }
        }

    }
}
