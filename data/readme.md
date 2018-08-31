# The OHDSI CDM DDL

## Any sql file in the data folder will be automatically executed in the pgsql container on startup.
Add the CDM ddl file here and name it cdm.sql (This file is added to .gitignore)

* visit [this repo](https://github.com/OHDSI/CommonDataModel)
* Navigate to PostgreSQL folder
* Add the contents of the following files to cdm.sql in this order

1. OMOP CDM postgresql ddl.txt
2. OMOP CDM postgresql indexes.txt
3. OMOP CDM postgresql constraints.txt

## OR use cdm.sh