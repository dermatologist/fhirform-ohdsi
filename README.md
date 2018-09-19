# fhirform-ohdsi | *A FHIR has been lit on this server*!


## About

This is an experimental extension of the [FHIRForm](https://github.com/E-Health/fhirform) framework.
The aim is to provide the fhirform server (A HAPI FHIR server for handling FHIR questionnaires with special extensions)
the functionality to convert QuestionnaireResponses to [OHDSI OMOP CDM](https://www.ohdsi.org/).

### Work in progress...  [Contact](https://nuchange.ca/contact)

If successful, this will be merged with [fhirform-server](https://github.com/dermatologist/fhirform-server).

## Requirements

* java 8
* maven 3
* docker
* docker-compose

## How to Use:

```
mvn spring-boot:repackage
docker-compose up
```

*How to manage the whole [FHIRForm](https://github.com/E-Health/fhirform) framework is described elsewhere.*

* Access FHIR server at http://localhost:8080/fhir
* OHDSI [Atlas](http://www.ohdsi.org/web/atlas/#/home) at http://localhost:8080/atlas
* Rstudio at http://localhost:8080/rstudio

## Contributors

[Bell Eapen](https://nuchange.ca) |  (McMaster U)
