export $(grep -v '^#' .env | xargs)

unset DB_HOST
export DB_HOST=localhost

mvn spring-boot:run