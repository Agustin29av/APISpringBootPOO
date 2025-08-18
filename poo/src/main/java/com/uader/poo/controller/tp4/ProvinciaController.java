package com.uader.poo.controller.tp4;

import com.uader.poo.dto.tp4.ProvinciaCreateDTO;
import com.uader.poo.dto.tp4.ProvinciaResponseDTO;
import com.uader.poo.entity.tp4.Provincia;
import com.uader.poo.service.tp4.IProvinciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    private final IProvinciaService provinciaService;

    @Autowired
    public ProvinciaController(IProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    // Crear provincia (Recibe un DTO y usa @Valid)
    @PostMapping
    public ResponseEntity<ProvinciaResponseDTO> crearProvincia(@Valid @RequestBody ProvinciaCreateDTO provinciaCreateDTO) throws Exception {
        // Mapear de DTO a entidad
        Provincia nuevaProvincia = new Provincia(provinciaCreateDTO.getNombre(), provinciaCreateDTO.getPaisId());
        nuevaProvincia = provinciaService.crearProvincia(nuevaProvincia);

        // Mapear de entidad a DTO de respuesta
        ProvinciaResponseDTO responseDTO = new ProvinciaResponseDTO(
                nuevaProvincia.getId(),
                nuevaProvincia.getNombre(),
                nuevaProvincia.getPaisId()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Obtener por ID (Devuelve un DTO)
    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaResponseDTO> obtenerProvinciaPorId(@PathVariable String id) throws Exception {
        Provincia provincia = provinciaService.obtenerProvinciaPorId(id);
        ProvinciaResponseDTO responseDTO = new ProvinciaResponseDTO(
                provincia.getId(),
                provincia.getNombre(),
                provincia.getPaisId()
        );
        return ResponseEntity.ok(responseDTO);
    }

    // Obtener por nombre (Devuelve un DTO)
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProvinciaResponseDTO> obtenerProvinciaPorNombre(@PathVariable String nombre) throws Exception {
        Provincia provincia = provinciaService.obtenerProvinciaPorNombre(nombre);
        ProvinciaResponseDTO responseDTO = new ProvinciaResponseDTO(
                provincia.getId(),
                provincia.getNombre(),
                provincia.getPaisId()
        );
        return ResponseEntity.ok(responseDTO);
    }
    
    // Obtener todas las provincias (Devuelve una lista de DTOs)
    @GetMapping
    public ResponseEntity<List<ProvinciaResponseDTO>> obtenerTodasLasProvincias() {
        List<Provincia> provincias = provinciaService.obtenerTodasLasProvincias();
        List<ProvinciaResponseDTO> responseDTOs = provincias.stream()
                .map(p -> new ProvinciaResponseDTO(p.getId(), p.getNombre(), p.getPaisId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Eliminar provincia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProvincia(@PathVariable String id) throws Exception {
        provinciaService.eliminarProvincia(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener provincias por ID de país (Devuelve una lista de DTOs)
    @GetMapping("/pais/{paisId}")
    public ResponseEntity<List<ProvinciaResponseDTO>> obtenerProvinciasPorPaisId(@PathVariable String paisId) {
        List<Provincia> provincias = provinciaService.obtenerProvinciasPorPaisId(paisId);
        List<ProvinciaResponseDTO> responseDTOs = provincias.stream()
                .map(p -> new ProvinciaResponseDTO(p.getId(), p.getNombre(), p.getPaisId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}