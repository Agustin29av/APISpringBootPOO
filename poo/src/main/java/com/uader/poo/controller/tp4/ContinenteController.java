package com.uader.poo.controller.tp4;

import com.uader.poo.dto.tp4.ContinenteCreateDTO;
import com.uader.poo.dto.tp4.ContinenteResponseDTO;
import com.uader.poo.entity.tp4.Continente;
import com.uader.poo.service.tp4.IContinenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/continentes")
public class ContinenteController {

    private final IContinenteService continenteService;

    @Autowired
    public ContinenteController(IContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    // Crear continente (recibe un DTO)
    @PostMapping
    public ResponseEntity<ContinenteResponseDTO> crearContinente(@Valid @RequestBody ContinenteCreateDTO continenteCreateDTO) throws Exception {
        Continente nuevoContinente = new Continente(continenteCreateDTO.getNombre());
        nuevoContinente = continenteService.crearContinente(nuevoContinente);
        ContinenteResponseDTO responseDTO = new ContinenteResponseDTO(
                nuevoContinente.getId(),
                nuevoContinente.getNombre(),
                nuevoContinente.getPaises()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<ContinenteResponseDTO>> obtenerTodos() {
        List<Continente> continentes = continenteService.obtenerTodosLosContinentes();
        List<ContinenteResponseDTO> responseDTOs = continentes.stream()
                .map(c -> new ContinenteResponseDTO(c.getId(), c.getNombre(), c.getPaises()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContinenteResponseDTO> obtenerPorId(@PathVariable String id) throws Exception {
        Continente continente = continenteService.obtenerContinentePorId(id);
        ContinenteResponseDTO responseDTO = new ContinenteResponseDTO(
                continente.getId(),
                continente.getNombre(),
                continente.getPaises()
        );
        return ResponseEntity.ok(responseDTO);
    }

    // Obtener por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ContinenteResponseDTO> obtenerPorNombre(@PathVariable String nombre) throws Exception {
        Continente continente = continenteService.obtenerContinentePorNombre(nombre);
        ContinenteResponseDTO responseDTO = new ContinenteResponseDTO(
                continente.getId(),
                continente.getNombre(),
                continente.getPaises()
        );
        return ResponseEntity.ok(responseDTO);
    }

    // Eliminar continente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) throws Exception {
        continenteService.eliminarContinente(id);
        return ResponseEntity.noContent().build();
    }

    // Agregar país a continente
    @PostMapping("/{nombreContinente}/agregar-pais")
    public ResponseEntity<ContinenteResponseDTO> agregarPais(
            @PathVariable String nombreContinente,
            @RequestBody String nombrePais) throws Exception {
        Continente actualizado = continenteService.agregarPaisAContinente(nombreContinente, nombrePais);
        ContinenteResponseDTO responseDTO = new ContinenteResponseDTO(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getPaises()
        );
        return ResponseEntity.ok(responseDTO);
    }
}