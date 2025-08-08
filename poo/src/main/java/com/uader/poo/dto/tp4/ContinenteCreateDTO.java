package com.uader.poo.dto.tp4;

import jakarta.validation.constraints.NotBlank;

public class ContinenteCreateDTO {

    @NotBlank(message = "El nombre del continente no puede estar vacío.")
    private String nombre;

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}