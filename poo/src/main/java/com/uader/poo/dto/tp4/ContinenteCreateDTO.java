package com.uader.poo.dto.tp4;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContinenteCreateDTO {

    @NotBlank(message = "El nombre del continente no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre del continente debe tener entre 3 y 50 caracteres")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
