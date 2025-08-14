package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Continente;
import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.exception.ResourceNotFoundException;
import com.uader.poo.exception.InvalidInputException;
import com.uader.poo.repository.tp4.ContinenteRepository;
import com.uader.poo.repository.tp4.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinenteServiceImpl implements IContinenteService {

    private final ContinenteRepository continenteRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public ContinenteServiceImpl(ContinenteRepository continenteRepository, PaisRepository paisRepository) {
        this.continenteRepository = continenteRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Continente crearContinente(Continente continente) throws Exception {
        if (continente == null || continente.getNombre() == null || continente.getNombre().isBlank()) {
            throw new InvalidInputException("El nombre del continente no puede estar vacío.");
        }
        if (continenteRepository.existsByNombreIgnoreCase(continente.getNombre())) {
            throw new IllegalStateException("Ya existe un continente con el nombre '" + continente.getNombre() + "'.");
        }
        return continenteRepository.save(continente);
    }

    @Override
    public Continente obtenerContinentePorId(String id) throws Exception {
        return continenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un continente con el ID: " + id));
    }

    @Override
    public Continente obtenerContinentePorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new InvalidInputException("El nombre no puede ser nulo o vacío.");
        }
        Continente continente = continenteRepository.findByNombreIgnoreCase(nombre);
        if (continente == null) {
            throw new ResourceNotFoundException("No se encontró un continente con el nombre: " + nombre);
        }
        return continente;
    }

    @Override
    public List<Continente> obtenerTodosLosContinentes() {
        return continenteRepository.findAll();
    }

    @Override
    public void eliminarContinente(String id) throws Exception {
        if (!continenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: No existe un continente con ID: " + id);
        }
        continenteRepository.deleteById(id);
    }

    @Override
    public Continente agregarPaisAContinente(String nombreContinente, String nombrePais) throws Exception {
        if (nombreContinente == null || nombreContinente.isBlank() ||
                nombrePais == null || nombrePais.isBlank()) {
            throw new InvalidInputException("Nombre del continente o país no puede ser vacío.");
        }

        Continente continente = continenteRepository.findByNombreIgnoreCase(nombreContinente);
        if (continente == null) {
            throw new ResourceNotFoundException("No se encontró el continente con nombre: " + nombreContinente);
        }

        Pais pais = paisRepository.findByNombreIgnoreCase(nombrePais);
        if (pais == null) {
            throw new ResourceNotFoundException("No se encontró el país con nombre: " + nombrePais);
        }

        String paisId = pais.getId();

        if (continente.getPaises().contains(paisId)) {
            throw new IllegalStateException("El país ya pertenece a este continente.");
        }

        continente.agregarPais(paisId);
        pais.setContinenteId(continente.getId());
        paisRepository.save(pais);

        return continenteRepository.save(continente);
    }
}
