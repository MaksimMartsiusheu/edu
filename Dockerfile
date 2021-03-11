FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY E-Edu-af45e04fc886.json /tmp/key/key.json
ENV GOOGLE_APPLICATION_CREDENTIALS /tmp/key/key.json
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]