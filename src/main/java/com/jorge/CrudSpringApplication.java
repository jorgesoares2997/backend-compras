package com.jorge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.jorge.repository")
public class CrudSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudSpringApplication.class, args);
  }
}
