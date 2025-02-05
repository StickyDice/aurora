package com.aurora.aurora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AuroraApplication {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    System.setProperty("POSTGRESQL_HOST", dotenv.get("POSTGRESQL_HOST"));
    System.setProperty("POSTGRESQL_PORT", dotenv.get("POSTGRESQL_PORT"));
    System.setProperty("POSTGRESQL_DATABASE", dotenv.get("POSTGRESQL_DATABASE"));
    System.setProperty("POSTGRESQL_USERNAME", dotenv.get("POSTGRESQL_USERNAME"));
    System.setProperty("POSTGRESQL_PASSWORD", dotenv.get("POSTGRESQL_PASSWORD"));

    SpringApplication.run(AuroraApplication.class, args);
  }

}
