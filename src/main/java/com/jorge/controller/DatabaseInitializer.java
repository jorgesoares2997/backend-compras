package com.jorge.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jorge.repository.CourseRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {

            // Verifica se já existem cursos, e só insere se estiver vazio

        };
    }
}
