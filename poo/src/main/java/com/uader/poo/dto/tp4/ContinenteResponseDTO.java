package com.uader.poo.dto.tp4;

import java.util.Set;

public class ContinenteResponseDTO {

    private String id;
    private String nombre;
    private Set<String> paisesIds; // También podríamos devolver los objetos completos de los países

    // Constructor para mapeo
    public ContinenteResponseDTO(String id, String nombre, Set<String> paisesIds) {
        this.id = id;
        this.nombre = nombre;
        this.paisesIds = paisesIds;
    }

    // Getters and Setters
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

    public Set<String> getPaisesIds() {
        return paisesIds;
    }

    public void setPaisesIds(Set<String> paisesIds) {
        this.paisesIds = paisesIds;
    }
}