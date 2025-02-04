package com.jorge.controller;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jorge.model.Course;
import com.jorge.repository.CourseRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {

            // Verifica se já existem cursos, e só insere se estiver vazio
            if (courseRepository.count() == 0) {
                Course course1 = new Course();
                course1.setTitle("Smartphone Pro");
                course1.setSubtitle("Celular de última geração");
                course1.setLocal("São Paulo");
                course1.setImage("smartphone.jpg");
                course1.setPrice(new BigDecimal("3500.00"));
                course1.setUrgency("Alta");
                course1.setLink("https://example.com/smartphone");

                Course course2 = new Course();
                course2.setTitle("Notebook Ultra");
                course2.setSubtitle("Notebook para produtividade");
                course2.setLocal("Rio de Janeiro");
                course2.setImage("notebook.jpg");
                course2.setPrice(new BigDecimal("5500.00"));
                course2.setUrgency("Média");
                course2.setLink("https://example.com/notebook");

                Course course3 = new Course();
                course3.setTitle("Fone de Ouvido Pro");
                course3.setSubtitle("Som de qualidade profissional");
                course3.setLocal("Belo Horizonte");
                course3.setImage("fone.jpg");
                course3.setPrice(new BigDecimal("800.00"));
                course3.setUrgency("Baixa");
                course3.setLink("https://example.com/fone");

                courseRepository.save(course1);
                courseRepository.save(course2);
                courseRepository.save(course3);
            }
        };
    }
}
