FROM maven:3.9-eclipse-temurin-21-focal AS build
WORKDIR /app

COPY ./notificaciones/pom.xml .
COPY ./notificaciones/mvnw* ./
COPY ./notificaciones/.mvn .mvn/

COPY ./notificaciones/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

RUN apt-get update && \
    apt-get install -y sqlite3 && \
    rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/*.jar app.jar

ENV SERVER_PORT=8081
ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "/app/app.jar"]