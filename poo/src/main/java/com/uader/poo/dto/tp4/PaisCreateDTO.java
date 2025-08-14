package com.uader.poo.dto.tp4;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PaisCreateDTO {

    @NotBlank(message = "El nombre del país no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre del país debe tener entre 3 y 50 caracteres")
    private String nombre;
    private String capital;
    @Min(value = 0, message = "La superficie no puede ser negativa")
    private double superficie;
    @NotBlank(message = "El ID del continente no puede estar vacío")
    private String continenteId;

    public PaisCreateDTO() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getContinenteId() {
        return continenteId;
    }

    public void setContinenteId(String continenteId) {
        this.continenteId = continenteId;
    }
}
