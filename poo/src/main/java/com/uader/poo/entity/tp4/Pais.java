package com.uader.poo.entity.tp4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "paises")
public class Pais {

    @Id
    private String id;
    private String nombre;
    private String capital;
    private double superficie;
    private String continenteId; // NUEVO: Referencia al ID del continente
    private Set<String> provinciasIds = new HashSet<>();
    private Set<String> limitrofesIds = new HashSet<>();

    public Pais() {}

    // Constructor actualizado para incluir el continenteId
    public Pais(String nombre, String capital, double superficie, String continenteId) {
        this.nombre = nombre;
        this.capital = capital;
        this.superficie = superficie;
        this.continenteId = continenteId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    // ... otros getters y setters

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
    public void agregarProvinciaId(String provinciaId) {
        if (this.provinciasIds == null) {
            this.provinciasIds = new HashSet<>();
        }
        this.provinciasIds.add(provinciaId);
    }

    public Set<String> getLimitrofesIds() {
        return limitrofesIds;
    }
    public void setLimitrofesIds(Set<String> limitrofesIds) {
        this.limitrofesIds = limitrofesIds;
    }
    public void agregarLimitrofeId(String paisId) {
        if (paisId != null && !paisId.equals(this.id)) {
            if (this.limitrofesIds == null) {
                this.limitrofesIds = new HashSet<>();
            }
            this.limitrofesIds.add(paisId);
        }
    }

    @Override
    public String toString() {
        return "Pais{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", capital='" + capital + '\'' +
               ", superficie=" + superficie +
               ", continenteId='" + continenteId + '\'' +
               ", provinciasIds=" + provinciasIds +
               ", limitrofesIds=" + limitrofesIds +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pais pais = (Pais) obj;
        return nombre.equalsIgnoreCase(pais.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }
}