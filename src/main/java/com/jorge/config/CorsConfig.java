package com.jorge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica a todas as rotas que começam com /api/
                        .allowedOrigins("http://localhost:3000") // Permite o frontend local
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite esses métodos
                        .allowedHeaders("*") // Permite todos os headers
                        .allowCredentials(true); // Permite credenciais (se necessário)
            }
        };
    }
}