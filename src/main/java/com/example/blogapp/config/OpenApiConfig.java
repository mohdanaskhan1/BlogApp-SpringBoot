package com.example.blogapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Application API")
                        .version("v1.0")
                        .description("Spring Boot Blog Application REST API documentation")
                        .contact(new Contact().name("Mohd Anas Khan").email("you@example.com"))
                        .license(new License().name("Apache 2.0"))
                );
    }
}
