# Notes

## [Customizing overlay](http://hapifhir.io/doc_server_tester.html#Adding_the_Overlay)

## Injection

* The id of the injected item may need to be changed.

## Building

* run maven goal *package*

## Commands
*  psql -h cedar-pgsql-vm username_db < OMOP\ CDM\ postgresql\ ddl.txt

## Import
```
\COPY DRUG_STRENGTH FROM '/home/username/scratch/OHDSI/DRUG_STRENGTH.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY CONCEPT FROM '/home/username/scratch/OHDSI/CONCEPT.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY CONCEPT_RELATIONSHIP FROM '/home/username/scratch/OHDSI/CONCEPT_RELATIONSHIP.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY CONCEPT_ANCESTOR FROM '/home/username/scratch/OHDSI/CONCEPT_ANCESTOR.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY CONCEPT_SYNONYM FROM '/home/username/scratch/OHDSI/CONCEPT_SYNONYM.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY VOCABULARY FROM '/home/username/scratch/OHDSI/VOCABULARY.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' NULL AS 'null';

\COPY RELATIONSHIP FROM '/home/username/scratch/OHDSI/RELATIONSHIP.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY CONCEPT_CLASS FROM '/home/username/scratch/OHDSI/CONCEPT_CLASS.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;

\COPY DOMAIN FROM '/home/username/scratch/OHDSI/DOMAIN.csv' WITH DELIMITER E'\t' CSV HEADER QUOTE E'\b' ;
```
