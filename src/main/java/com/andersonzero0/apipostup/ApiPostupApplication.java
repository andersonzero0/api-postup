package com.andersonzero0.apipostup;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "API PostUp", version = "0.0.1", description = "API for PostUp application"))
public class ApiPostupApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPostupApplication.class, args);
    }

}
