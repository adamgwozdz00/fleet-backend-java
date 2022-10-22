FROM openjdk:11.0.15-oracle
ARG JAR_FILE=application/build/libs/application.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]