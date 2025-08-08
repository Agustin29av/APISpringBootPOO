package com.uader.poo.dto.tp4;

import jakarta.validation.constraints.NotBlank;

public class PaisCreateDTO {

    @NotBlank(message = "El nombre del país no puede estar vacío.")
    private String nombre;
    private String capital;
    private double superficie;
    private String continenteId;

    // Getters y Setters
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