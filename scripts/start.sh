export $(grep -v '^#' .env | xargs)

unset DB_HOST
export DB_HOST=localhost

mvn clean package

java -jar "target/aurora-0.0.1-SNAPSHOT.jar"