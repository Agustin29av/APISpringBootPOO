package com.uader.poo.dto.tp4;

import java.util.Set;

public class ContinenteResponseDTO {

    private String id;
    private String nombre;
    private Set<String> paises;

    public ContinenteResponseDTO(String id, String nombre, Set<String> paises) {
        this.id = id;
        this.nombre = nombre;
        this.paises = paises;
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

    public Set<String> getPaises() {
        return paises;
    }

    public void setPaises(Set<String> paises) {
        this.paises = paises;
    }
}
