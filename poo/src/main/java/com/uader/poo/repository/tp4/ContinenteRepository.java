package com.uader.poo.repository.tp4;

import com.uader.poo.entity.tp4.Continente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinenteRepository extends MongoRepository<Continente, String> {
    boolean existsByNombreIgnoreCase(String nombre);
    Continente findByNombreIgnoreCase(String nombre);
}
