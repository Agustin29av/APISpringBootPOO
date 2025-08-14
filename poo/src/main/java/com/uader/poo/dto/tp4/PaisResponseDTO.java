package com.uader.poo.dto.tp4;

import java.util.Set;

public class PaisResponseDTO {

    private String id;
    private String nombre;
    private String capital;
    private double superficie;
    private String continenteId;
    private Set<String> provincias;
    private Set<String> limitrofes;

    public PaisResponseDTO(String id, String nombre, String capital, double superficie, String continenteId, Set<String> provincias, Set<String> limitrofes) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.superficie = superficie;
        this.continenteId = continenteId;
        this.provincias = provincias;
        this.limitrofes = limitrofes;
    }

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

    public Set<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(Set<String> provincias) {
        this.provincias = provincias;
    }

    public Set<String> getLimitrofes() {
        return limitrofes;
    }

    public void setLimitrofes(Set<String> limitrofes) {
        this.limitrofes = limitrofes;
    }
}
