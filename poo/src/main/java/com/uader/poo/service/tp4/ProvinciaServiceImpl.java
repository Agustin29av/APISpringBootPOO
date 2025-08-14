package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.entity.tp4.Provincia;
import com.uader.poo.exception.InvalidInputException;
import com.uader.poo.exception.ResourceNotFoundException;
import com.uader.poo.repository.tp4.PaisRepository;
import com.uader.poo.repository.tp4.ProvinciaRepository;
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
            throw new InvalidInputException("El nombre de la provincia no puede estar vacío.");
        }
        if (provinciaRepository.existsByNombreIgnoreCase(provincia.getNombre())) {
            throw new IllegalStateException("Ya existe una provincia con el nombre '" + provincia.getNombre() + "'.");
        }
        
        // Obtener el país antes de guardar la provincia para asegurarnos de que existe
        Pais pais = paisRepository.findById(provincia.getPaisId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el país con el ID: " + provincia.getPaisId()));

        Provincia nuevaProvincia = provinciaRepository.save(provincia);

        // Se agrega el ID de la nueva provincia al país y se guarda el país
        pais.agregarProvincia(nuevaProvincia.getId());
        paisRepository.save(pais);

        return nuevaProvincia;
    }

    @Override
    public Provincia obtenerProvinciaPorId(String id) throws Exception {
        return provinciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una provincia con el ID: " + id));
    }

    @Override
    public Provincia obtenerProvinciaPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new InvalidInputException("El nombre de la provincia no puede ser nulo o vacío.");
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
        Provincia provincia = provinciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar: No existe una provincia con ID: " + id));

        // Eliminar la referencia de la provincia en el país al que pertenece
        Pais pais = paisRepository.findById(provincia.getPaisId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el país con el ID: " + provincia.getPaisId()));
        pais.eliminarProvincia(id);
        paisRepository.save(pais);

        // Eliminar la provincia
        provinciaRepository.deleteById(id);
    }

    @Override
    public List<Provincia> obtenerProvinciasPorPaisId(String paisId) {
        return provinciaRepository.findByPaisId(paisId);
    }
}
