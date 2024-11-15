FROM maven:3.9-eclipse-temurin-21-focal AS build
WORKDIR /app

COPY ./api-gateway/pom.xml .

COPY ./api-gateway/src ./src

COPY ./api-gateway/mvnw* ./
COPY ./api-gateway/.mvn .mvn/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

VOLUME /tmp

COPY --from=build /app/target/*.jar app.jar

ENV SERVER_PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "/app/app.jar"]