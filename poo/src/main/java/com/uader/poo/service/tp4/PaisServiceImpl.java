package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.repository.tp4.PaisRepository;
import com.uader.poo.repository.tp4.ContinenteRepository;
import com.uader.poo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements IPaisService {

    private final PaisRepository paisRepository;
    private final ContinenteRepository continenteRepository;

    @Autowired
    public PaisServiceImpl(PaisRepository paisRepository, ContinenteRepository continenteRepository) {
        this.paisRepository = paisRepository;
        this.continenteRepository = continenteRepository;
    }

    @Override
    public Pais crearPais(Pais pais) throws Exception {
        if (pais == null || pais.getNombre() == null || pais.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío.");
        }
        if (paisRepository.existsByNombreIgnoreCase(pais.getNombre())) {
            throw new IllegalStateException("Ya existe un país con el nombre '" + pais.getNombre() + "'.");
        }
        if (pais.getContinenteId() != null && !pais.getContinenteId().isBlank()) {
            if (!continenteRepository.existsById(pais.getContinenteId())) {
                throw new IllegalArgumentException("No se puede crear el país: el ID de continente no es válido.");
            }
        }
        return paisRepository.save(pais);
    }

    @Override
    public Pais obtenerPaisPorId(String id) throws Exception {
        return paisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un país con el ID: " + id));
    }

    @Override
    public Pais obtenerPaisPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        Pais pais = paisRepository.findByNombreIgnoreCase(nombre);
        if (pais == null) {
            throw new ResourceNotFoundException("No se encontró un país con el nombre: " + nombre);
        }
        return pais;
    }

    @Override
    public List<Pais> obtenerTodosLosPaises() {
        return paisRepository.findAll();
    }

    @Override
    public void eliminarPais(String id) throws Exception {
        if (!paisRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: No existe un país con ID: " + id);
        }
        paisRepository.deleteById(id);
    }

    @Override
    public List<Pais> obtenerPaisesPorContinenteId(String continenteId) {
        return paisRepository.findByContinenteId(continenteId);
    }
}