package com.uader.poo.dto.tp4;

import java.util.Set;

public class PaisResponseDTO {

    private String id;
    private String nombre;
    private String capital;
    private double superficie;
    private String continenteId;
    private Set<String> provinciasIds;
    private Set<String> limitrofesIds;

    // Constructor para mapeo
    public PaisResponseDTO(String id, String nombre, String capital, double superficie, String continenteId, Set<String> provinciasIds, Set<String> limitrofesIds) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.superficie = superficie;
        this.continenteId = continenteId;
        this.provinciasIds = provinciasIds;
        this.limitrofesIds = limitrofesIds;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Set<String> getProvinciasIds() {
        return provinciasIds;
    }

    public void setProvinciasIds(Set<String> provinciasIds) {
        this.provinciasIds = provinciasIds;
    }

    public Set<String> getLimitrofesIds() {
        return limitrofesIds;
    }

    public void setLimitrofesIds(Set<String> limitrofesIds) {
        this.limitrofesIds = limitrofesIds;
    }
}