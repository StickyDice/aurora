FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

EXPOSE 3000

WORKDIR /app

COPY . /app

RUN ./mvnw package -DskipTests

CMD ["java", "-jar", "target/aurora-0.0.1-SNAPSHOT.jar"]