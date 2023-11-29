#FROM openjdk:19
FROM eclipse-temurin:latest
COPY ./target/ana-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]