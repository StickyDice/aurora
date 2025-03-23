export $(grep -v '^#' .env | xargs)

mvn clean package

java -jar "target/aurora-0.0.1-SNAPSHOT.jar"