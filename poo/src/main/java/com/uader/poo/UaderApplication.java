package com.uader.poo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class UaderApplication {

    public static void main(String[] args) {
        // Cargar variables desde .env
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );

        // Arrancar Spring Boot
        SpringApplication.run(UaderApplication.class, args);
    }
}