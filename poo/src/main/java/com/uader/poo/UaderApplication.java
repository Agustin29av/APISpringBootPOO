package com.uader.poo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UaderApplication.class, args);
	}

}

// Explicación en guacho argentino 
// Argentino: Es la "llave maestra" que enciende toda la fábrica. 
// Cuando ejecutás este main, Spring Boot levanta todo: los mostradores (Controllers), los capataces (Services),
// los oficinistas (Repositorys), y conecta todo con el depósito (MongoDB).