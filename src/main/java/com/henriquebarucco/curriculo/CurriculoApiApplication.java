package com.henriquebarucco.curriculo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
    info = @Info(title = "Currículo API", version = "1.2.0", description = "API para informações sobre o meu currículo.", termsOfService = "https://www.henriquebarucco.com.br"),
    servers = {
        @Server(url = "https://curriculo-api.henriquebarucco.com.br", description = "Ambiente de produção"),
        @Server(url = "http://localhost:8080", description = "Ambiente de desenvolvimento")
    }
)
@EnableWebMvc
@SpringBootApplication
public class CurriculoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurriculoApiApplication.class, args);
    }

}
