package com.uader.poo.entity.tp4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Objects;

@Document(collection = "provincias")
public class Provincia {

    @Id
    private String id;
    private String nombre;
    private String paisId; // NUEVO: Referencia al ID del país

    public Provincia() {}

    // Constructor actualizado para incluir el paisId
    public Provincia(String nombre, String paisId) {
        this.nombre = nombre;
        this.paisId = paisId;
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

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String paisId) {
        this.paisId = paisId;
    }

    @Override
    public String toString() {
        return "Provincia{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", paisId='" + paisId + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Provincia p = (Provincia) obj;
        return nombre.equalsIgnoreCase(p.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}