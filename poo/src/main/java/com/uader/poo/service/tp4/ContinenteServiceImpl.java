package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Continente;
import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.repository.tp4.ContinenteRepository;
import com.uader.poo.repository.tp4.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinenteServiceImpl implements IContinenteService {

    private final ContinenteRepository continenteRepository;
    private final PaisRepository paisRepository; // Necesitamos el repo de Pais para buscar por nombre

    @Autowired
    public ContinenteServiceImpl(ContinenteRepository continenteRepository, PaisRepository paisRepository) {
        this.continenteRepository = continenteRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Continente crearContinente(Continente continente) {
        if (continente == null || continente.getNombre() == null || continente.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del continente no puede estar vacío.");
        }
        if (continenteRepository.existsByNombreIgnoreCase(continente.getNombre())) {
            throw new IllegalStateException("Ya existe un continente con el nombre '" + continente.getNombre() + "'.");
        }
        return continenteRepository.save(continente);
    }

    @Override
    public Continente obtenerContinentePorId(String id) {
        return continenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un continente con el ID: " + id));
    }

    @Override
    public Continente obtenerContinentePorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        Continente continente = continenteRepository.findByNombreIgnoreCase(nombre);
        if (continente == null) {
            throw new IllegalArgumentException("No se encontró un continente con el nombre: " + nombre);
        }
        return continente;
    }

    @Override
    public List<Continente> obtenerTodosLosContinentes() {
        return continenteRepository.findAll();
    }

    @Override
    public void eliminarContinente(String id) {
        if (!continenteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: No existe un continente con ID: " + id);
        }
        // TODO: Lógica adicional para eliminar un continente:
        // Por ejemplo, aquí podrías validar si el continente tiene países asociados.
        // Si tiene países, podrías decidir si quieres eliminar los países también,
        // o lanzar una excepción para evitar que se elimine un continente con datos asociados.
        continenteRepository.deleteById(id);
    }

    @Override
    public Continente agregarPaisAContinente(String nombreContinente, String nombrePais) {
        if (nombreContinente == null || nombreContinente.isBlank() ||
            nombrePais == null || nombrePais.isBlank()) {
            throw new IllegalArgumentException("Nombre del continente o país no puede ser vacío.");
        }

        Continente continente = continenteRepository.findByNombreIgnoreCase(nombreContinente);
        if (continente == null) {
            throw new IllegalArgumentException("No se encontró el continente con nombre: " + nombreContinente);
        }

        // Buscamos el país por su nombre para obtener su ID
        Pais pais = paisRepository.findByNombreIgnoreCase(nombrePais);
        if (pais == null) {
            throw new IllegalArgumentException("No se encontró el país con nombre: " + nombrePais);
        }

        String paisId = pais.getId();

        // Validación para evitar duplicados en el Set de IDs
        if (continente.getPaisesIds().contains(paisId)) {
            throw new IllegalStateException("El país ya pertenece a este continente.");
        }

        // Agregamos el ID del país al continente
        continente.agregarPaisId(paisId);
        
        // También actualizamos el objeto país para que sepa a qué continente pertenece
        pais.setContinenteId(continente.getId());
        paisRepository.save(pais); // Guardamos el cambio en el país

        // Finalmente, guardamos el continente actualizado en la base de datos
        return continenteRepository.save(continente);
    }
}