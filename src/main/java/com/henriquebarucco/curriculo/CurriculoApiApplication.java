package com.henriquebarucco.curriculo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CurriculoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurriculoApiApplication.class, args);
    }

}
