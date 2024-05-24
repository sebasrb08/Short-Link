FROM maven:3.8.1-openjdk-17 AS build

COPY . /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/url-0.0.1-SNAPSHOT.jar

COPY --from=build /app/${JAR_FILE} app_url.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app_url.jar"]