package com.backend.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend da plataforma de Agendamentos")
                        .version("1.0.0")
                        .description("Esta Api foi construida para modelar a lógica de negócio da aplicação.")
                        .contact(new Contact()
                            .name("Pedro Cezar")
                            .url("https://meu-portifolio-lime.vercel.app/")
                            .email("pcdasilvabeserra@gmail.com")));
    }
}
