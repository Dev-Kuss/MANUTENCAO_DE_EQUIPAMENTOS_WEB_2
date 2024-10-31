package com.tads.me.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica o CORS a todas as rotas
                .allowedOrigins("http://localhost:8081")  // Permite apenas o front-end no localhost:8081
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permite métodos HTTP específicos
                .allowedHeaders("*")  // Permite todos os cabeçalhos
                .exposedHeaders("Authorization")  // Expõe cabeçalhos específicos, se necessário
                .allowCredentials(true);  // Permite o envio de credenciais, se necessário
    }
}
