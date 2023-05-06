package com.henriquebarucco.curriculo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@Configuration
public class SwaggerConfig {

    @Value("${swagger.openapi.dev-url}")
    private String devUrl;

    @Value("${swagger.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Ambiente de desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Ambiente de produção");


        Contact contact = new Contact();
        contact.setEmail("contato@henriquebarucco.com.br");
        contact.setName("Henrique Barucco");
        contact.setUrl("https://www.henriquebarucco.com.br");

        Info info = new Info()
                .title("Curriculo API")
                .version("1.0")
                .contact(contact)
                .description("API de portfolio para o meu curriculo.").termsOfService("https://www.henriquebarucco.com.br");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}

