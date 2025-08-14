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

@Document(collection = "paises")
public class Pais {

    @Id
    private String id;

    @NotBlank(message = "El nombre del país no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del país debe tener entre 3 y 50 caracteres")
    @Field("nombre")
    private String nombre;

    @NotBlank(message = "El ID del continente no puede estar vacío")
    @Field("continente_id")
    private String continenteId;

    @Field("provincias")
    private Set<String> provincias = new HashSet<>();

    @Field("capital")
    private String capital;
    @Field("superficie")
    private double superficie;
    @Field("limitrofes")
    private Set<String> limitrofes = new HashSet<>();

    public Pais() { }

    public Pais(String nombre, String continenteId) {
        this.nombre = nombre;
        this.continenteId = continenteId;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContinenteId() { return continenteId; }
    public void setContinenteId(String continenteId) { this.continenteId = continenteId; }
    public String getCapital() {return capital;}
    public void setCapital(String capital) {this.capital = capital;}
    public double getSuperficie() {return superficie;}
    public void setSuperficie(double superficie) {this.superficie = superficie;}

    public Set<String> getProvincias() {
        return Collections.unmodifiableSet(provincias);
    }

    public void agregarProvincia(String idProvincia) {
        if (idProvincia != null && !idProvincia.isBlank()) {
            provincias.add(idProvincia);
        }
    }

    public void eliminarProvincia(String idProvincia) {
        provincias.remove(idProvincia);
    }

    public Set<String> getLimitrofes(){
        return Collections.unmodifiableSet(limitrofes);
    }
    public void agregarLimitrofe(String idLimitrofe) {
        if (idLimitrofe != null && !idLimitrofe.isBlank()) {
            limitrofes.add(idLimitrofe);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais that = (Pais) o;
        return nombre != null && nombre.equalsIgnoreCase(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre != null ? nombre.toLowerCase() : null);
    }

    @Override
    public String toString() {
        return "Pais{id='" + id + "', nombre='" + nombre + "', continenteId='" + continenteId + "', provincias=" + provincias + '}';
    }
}