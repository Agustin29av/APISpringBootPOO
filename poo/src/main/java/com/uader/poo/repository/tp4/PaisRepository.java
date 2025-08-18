package com.uader.poo.repository.tp4;

import com.uader.poo.entity.tp4.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {
    boolean existsByNombreIgnoreCase(String nombre);
    Pais findByNombreIgnoreCase(String nombre);
    List<Pais> findByContinenteId(String continenteId);
}
