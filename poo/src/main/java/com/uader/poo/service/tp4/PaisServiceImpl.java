package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Continente;
import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.entity.tp4.Provincia;
import com.uader.poo.exception.InvalidInputException;
import com.uader.poo.exception.ResourceNotFoundException;
import com.uader.poo.repository.tp4.ContinenteRepository;
import com.uader.poo.repository.tp4.PaisRepository;
import com.uader.poo.repository.tp4.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PaisServiceImpl implements IPaisService {

    private final PaisRepository paisRepository;
    private final ContinenteRepository continenteRepository;
    private final ProvinciaRepository provinciaRepository;

    @Autowired
    public PaisServiceImpl(PaisRepository paisRepository, ContinenteRepository continenteRepository, ProvinciaRepository provinciaRepository) {
        this.paisRepository = paisRepository;
        this.continenteRepository = continenteRepository;
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public Pais crearPais(Pais pais) throws Exception {
        if (pais == null || pais.getNombre() == null || pais.getNombre().isBlank()) {
            throw new InvalidInputException("El nombre del país no puede estar vacío.");
        }
        if (paisRepository.existsByNombreIgnoreCase(pais.getNombre())) {
            throw new IllegalStateException("Ya existe un país con el nombre '" + pais.getNombre() + "'.");
        }
        
        // Primero veo si existe el continente para meter el pais
        Continente continente = continenteRepository.findById(pais.getContinenteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el continente con el ID: " + pais.getContinenteId()));

        Pais nuevoPais = paisRepository.save(pais);

        // agrego la id del pais al continente y lo guardo
        continente.agregarPais(nuevoPais.getId());
        continenteRepository.save(continente);

        return nuevoPais;
    }

    @Override
    public Pais obtenerPaisPorId(String id) throws Exception {
        return paisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un país con el ID: " + id));
    }

    @Override
    public Pais obtenerPaisPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new InvalidInputException("El nombre del país no puede ser nulo o vacío.");
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
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar: No existe un país con ID: " + id));

        // Se eliminan todas las provincias asociadas a este país.
        if (pais.getProvincias() != null && !pais.getProvincias().isEmpty()) {
            provinciaRepository.deleteAllById(pais.getProvincias());
        }

        // Eliminar el país del continente al que pertenece, osea elimino argentina de america por ej
        Continente continente = continenteRepository.findById(pais.getContinenteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el continente con el ID: " + pais.getContinenteId()));
        continente.eliminarPais(id);
        continenteRepository.save(continente);
        
        // Se elimina el país
        paisRepository.deleteById(id);
    }

    @Override
    public List<Pais> obtenerPaisesPorContinenteId(String continenteId) {
        return paisRepository.findByContinenteId(continenteId);
    }

    @Override
    public Pais agregarProvinciaAPais(String nombrePais, String nombreProvincia) throws Exception {
        if (nombrePais == null || nombrePais.isBlank() || nombreProvincia == null || nombreProvincia.isBlank()) {
            throw new InvalidInputException("El nombre del país o provincia no puede ser nulo o vacío.");
        }

        Pais pais = paisRepository.findByNombreIgnoreCase(nombrePais);
        if (pais == null) {
            throw new ResourceNotFoundException("No se encontró el país con nombre: " + nombrePais);
        }

        Provincia provincia = provinciaRepository.findByNombreIgnoreCase(nombreProvincia);
        if (provincia == null) {
            throw new ResourceNotFoundException("No se encontró la provincia con nombre: " + nombreProvincia);
        }

        if (pais.getProvincias().contains(provincia.getId())) {
            throw new IllegalStateException("La provincia ya pertenece a este país.");
        }

        pais.agregarProvincia(provincia.getId());
        provincia.setPaisId(pais.getId());
        provinciaRepository.save(provincia);

        return paisRepository.save(pais);
    }

    @Override
    public Pais agregarLimitrofeAPais(String nombrePais, String nombreLimitrofe) throws Exception {
        if (nombrePais == null || nombrePais.isBlank() || nombreLimitrofe == null || nombreLimitrofe.isBlank()) {
            throw new InvalidInputException("El nombre del país o país limítrofe no puede ser nulo o vacío.");
        }
        
        Pais pais = paisRepository.findByNombreIgnoreCase(nombrePais);
        if (pais == null) {
            throw new ResourceNotFoundException("No se encontró el país con nombre: " + nombrePais);
        }

        Pais limitrofe = paisRepository.findByNombreIgnoreCase(nombreLimitrofe);
        if (limitrofe == null) {
            throw new ResourceNotFoundException("No se encontró el país limítrofe con nombre: " + nombreLimitrofe);
        }
        
        if(pais.getLimitrofes().contains(limitrofe.getId())){
            throw new IllegalStateException("El país limítrofe ya pertenece a este país.");
        }
        
        pais.agregarLimitrofe(limitrofe.getId());
        return paisRepository.save(pais);
    }
}
