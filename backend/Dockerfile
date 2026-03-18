# syntax=docker/dockerfile:1.7

FROM maven:3.9.10-eclipse-temurin-22 AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:22-jre
WORKDIR /opt/app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
