# Simple Spring Boot Project with GCP Integration

Todo's
* add value's to the application.properties

## Deploy the Application

mvn clean package appengine:deploy 

gcloud functions deploy edu_cf --entry-point functions.CloudFunction --runtime java11 --trigger-topic edu_cf