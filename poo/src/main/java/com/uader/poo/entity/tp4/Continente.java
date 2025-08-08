package com.uader.poo.entity.tp4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "continentes")
public class Continente {

    @Id
    private String id;
    private String nombre;
    private Set<String> paisesIds = new HashSet<>(); // Referencia a los IDs de los países

    public Continente() {}

    public Continente(String nombre) {
        this.nombre = nombre;
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

    public Set<String> getPaisesIds() {
        return paisesIds;
    }

    public void setPaisesIds(Set<String> paisesIds) {
        this.paisesIds = paisesIds;
    }

    public void agregarPaisId(String paisId) {
        if (this.paisesIds == null) {
            this.paisesIds = new HashSet<>();
        }
        this.paisesIds.add(paisId);
    }

    @Override
    public String toString() {
        return "Continente{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", paisesIds=" + paisesIds +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Continente c = (Continente) obj;
        return nombre.equalsIgnoreCase(c.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}