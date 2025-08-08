package com.uader.poo.dto.tp4;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProvinciaCreateDTO {

    @NotBlank(message = "El nombre de la provincia no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "El ID del país no puede estar vacío.")
    private String paisId;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }
}