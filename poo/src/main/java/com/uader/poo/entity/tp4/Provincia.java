package com.uader.poo.entity.tp4;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "provincias")
public class Provincia {

    @Id
    private String id;

    @NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de la provincia debe tener entre 3 y 50 caracteres")
    @Field("nombre")
    private String nombre;

    @NotBlank(message = "El ID del país no puede estar vacío")
    @Field("pais_id")
    private String paisId;

    // acordarse que el constructor vacio por defecto es necesario para que el framework pueda crear instancias de esta clase

    public Provincia() { }

    public Provincia(String nombre, String paisId) {
        this.nombre = nombre;
        this.paisId = paisId;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPaisId() { return paisId; }
    public void setPaisId(String paisId) { this.paisId = paisId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincia)) return false;
        Provincia that = (Provincia) o;
        return nombre != null && nombre.equalsIgnoreCase(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre != null ? nombre.toLowerCase() : null);
    }

    @Override
    public String toString() {
        return "Provincia{id='" + id + "', nombre='" + nombre + "', paisId='" + paisId + "'}";
    }
}
