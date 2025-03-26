package com.jorge;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.jorge.repository")
@EnableCaching
public class CrudSpringApplication {

  static {

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    String dbUrl = dotenv.get("SPRING_DATASOURCE_URL");
    String dbUsername = dotenv.get("SPRING_DATASOURCE_USERNAME");
    String dbPassword = dotenv.get("SPRING_DATASOURCE_PASSWORD");

    if (dbUrl != null) {
      System.setProperty("SPRING_DATASOURCE_URL", dbUrl);
    }
    if (dbUsername != null) {
      System.setProperty("SPRING_DATASOURCE_USERNAME", dbUsername);
    }
    if (dbPassword != null) {
      System.setProperty("SPRING_DATASOURCE_PASSWORD", dbPassword);
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(CrudSpringApplication.class, args);
  }
}