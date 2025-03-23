FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy pom and mvnw file at first for better caching
COPY  pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Install deps only once (next times it will be cached)
RUN ./mvnw dependency:go-offline

COPY . .

EXPOSE $AURORA_PORT


RUN ./mvnw package -DskipTests

RUN source .env

CMD ["java", "-jar", "./target/aurora-0.0.1-SNAPSHOT.jar"]