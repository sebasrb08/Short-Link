FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/url-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_url.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_url.jar"]
