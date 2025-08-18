package com.uader.poo.entity.tp4;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "continentes")
public class Continente {

    @Id
    private String id;

    @NotBlank(message = "El nombre del continente no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del continente debe tener entre 3 y 50 caracteres")
    @Field("nombre")
    private String nombre;

    @Field("paises")
    private Set<String> paises = new HashSet<>(); // IDs de los países

    // Antes tenia private el constructor pero lo cambie a public para que sea visible desde el controlador
    public Continente() { } 

    public Continente(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getId() {
        return id; 
    }
    public String getNombre() {
        return nombre; 
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getPaises() {
        return Collections.unmodifiableSet(paises);
    }

    public void agregarPais(String paisId) {
        if (paisId != null && !paisId.isBlank()) {
            paises.add(paisId);
        }
    }

    public void eliminarPais(String paisId) {
        paises.remove(paisId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Continente)) return false;
        Continente that = (Continente) o;
        return nombre != null && nombre.equalsIgnoreCase(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre != null ? nombre.toLowerCase() : null);
    }

    @Override
    public String toString() {
        return "Continente{id='" + id + "', nombre='" + nombre + "', paises=" + paises + '}';
    }
}
