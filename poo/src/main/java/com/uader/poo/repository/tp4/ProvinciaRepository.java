package com.uader.poo.repository.tp4;

import com.uader.poo.entity.tp4.Provincia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProvinciaRepository extends MongoRepository<Provincia, String> {
    boolean existsByNombreIgnoreCase(String nombre);
    Provincia findByNombreIgnoreCase(String nombre);
    List<Provincia> findByPaisId(String paisId);
    List<Provincia> findBySuperficieGreaterThan(double superficie);
    // NUEVO: Método para validar la unicidad de la provincia dentro de un país
    Provincia findByNombreIgnoreCaseAndPaisId(String nombre, String paisId);
}