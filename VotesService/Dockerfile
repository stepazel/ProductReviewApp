# syntax=docker/dockerfile:1.4
FROM eclipse-temurin:19-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/extracted && (java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted)

FROM eclipse-temurin:19-jdk-alpine
VOLUME /tmp
ARG EXTRACTED=/workspace/app/target/*.jar
COPY --from=build ${EXTRACTED} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
