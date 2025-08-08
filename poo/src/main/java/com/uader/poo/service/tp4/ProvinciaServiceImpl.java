package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Provincia;
import com.uader.poo.repository.tp4.ProvinciaRepository;
import com.uader.poo.repository.tp4.PaisRepository;
import com.uader.poo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {

    private final ProvinciaRepository provinciaRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository, PaisRepository paisRepository) {
        this.provinciaRepository = provinciaRepository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Provincia crearProvincia(Provincia provincia) throws Exception {
        if (provincia == null || provincia.getNombre() == null || provincia.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la provincia no puede estar vacío.");
        }
        if (provincia.getPaisId() != null && !provincia.getPaisId().isBlank()) {
            if (!paisRepository.existsById(provincia.getPaisId())) {
                throw new IllegalArgumentException("No se puede crear la provincia: el ID de país no es válido.");
            }
            Provincia provinciaExistente = provinciaRepository.findByNombreIgnoreCaseAndPaisId(provincia.getNombre(), provincia.getPaisId());
            if (provinciaExistente != null) {
                throw new IllegalStateException("Ya existe una provincia con este nombre en el país especificado.");
            }
        }
        return provinciaRepository.save(provincia);
    }

    @Override
    public Provincia obtenerProvinciaPorId(String id) throws Exception {
        return provinciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una provincia con el ID: " + id));
    }

    @Override
    public Provincia obtenerProvinciaPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        Provincia provincia = provinciaRepository.findByNombreIgnoreCase(nombre);
        if (provincia == null) {
            throw new ResourceNotFoundException("No se encontró una provincia con el nombre: " + nombre);
        }
        return provincia;
    }

    @Override
    public List<Provincia> obtenerTodasLasProvincias() {
        return provinciaRepository.findAll();
    }

    @Override
    public void eliminarProvincia(String id) throws Exception {
        if (!provinciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: No existe una provincia con ID: " + id);
        }
        provinciaRepository.deleteById(id);
    }

    @Override
    public List<Provincia> obtenerProvinciasPorPaisId(String paisId) {
        return provinciaRepository.findByPaisId(paisId);
    }
}