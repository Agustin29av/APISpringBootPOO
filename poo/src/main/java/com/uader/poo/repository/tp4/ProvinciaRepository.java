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
    Provincia findByNombreIgnoreCaseAndPaisId(String nombre, String paisId);
}
