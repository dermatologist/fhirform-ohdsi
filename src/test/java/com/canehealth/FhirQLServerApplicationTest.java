package com.canehealth;

import ca.uhn.fhir.context.FhirContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FhirQLServerApplicationTest {

    @Autowired
    FhirContext fhirContext;

    @LocalServerPort
    int port;

    @Test
    public void createAndRead() {
//        IGenericClient client = fhirContext.newRestfulGenericClient("http://localhost:" + port + "/fhir");
//
//        Patient patient = new Patient();
//        patient.addName().setFamily("Test");
//        IIdType id = client.create().resource(patient).execute().getId();
//
//        System.out.println(id);
//
//        Patient result = client.read().resource(Patient.class).withId(id).execute();
//
//        System.out.println(result);
    }

}
