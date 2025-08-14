package com.uader.poo.repository.tp4;

import com.uader.poo.entity.tp4.Pais;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {
    // Revisa si existe un país con el nombre especificado (ignorando mayúsculas y minúsculas)
    boolean existsByNombreIgnoreCase(String nombre);
    
    // Busca un país por su nombre (ignorando mayúsculas y minúsculas)
    Pais findByNombreIgnoreCase(String nombre);
    
    // Busca una lista de países por el ID de su continente
    List<Pais> findByContinenteId(String continenteId);
    
    // NOTA: Se eliminaron los métodos findByIdiomaIgnoreCase y findBySuperficieGreaterThan
    // ya que el campo 'idioma' no existe en la entidad Pais y la lógica de búsqueda por
    // superficie es mejor gestionarla en el servicio para mayor control.
}
