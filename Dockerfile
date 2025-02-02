FROM openjdk:17-jdk-slim

EXPOSE 3000

WORKDIR /app

COPY . /app

RUN ./mvnw package -DskipTests

CMD ["./mvnw", "spring-boot:run"]