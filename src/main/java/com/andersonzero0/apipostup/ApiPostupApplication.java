package com.andersonzero0.apipostup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiPostupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPostupApplication.class, args);
    }

}
