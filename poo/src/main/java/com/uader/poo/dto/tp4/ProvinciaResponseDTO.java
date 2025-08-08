package com.uader.poo.dto.tp4;

public class ProvinciaResponseDTO {

    private String id;
    private String nombre;
    private String paisId;

    // Constructor para mapeo
    public ProvinciaResponseDTO(String id, String nombre, String paisId) {
        this.id = id;
        this.nombre = nombre;
        this.paisId = paisId;
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

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }
}