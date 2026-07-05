package com.br.timn.Gosto.config;

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
                registry.addMapping("/**")
                        .allowedOrigins("https://gostoo.vercel.app", "http://localhost:4321")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }
}
