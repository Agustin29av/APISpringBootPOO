package com.uader.poo.controller.tp4;

import com.uader.poo.dto.tp4.PaisCreateDTO;
import com.uader.poo.dto.tp4.PaisResponseDTO;
import com.uader.poo.entity.tp4.Pais;
import com.uader.poo.service.tp4.IPaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    private final IPaisService paisService;

    @Autowired
    public PaisController(IPaisService paisService) {
        this.paisService = paisService;
    }

    // Crear país (ahora recibe un DTO y usa @Valid)
    @PostMapping
    public ResponseEntity<PaisResponseDTO> crearPais(@Valid @RequestBody PaisCreateDTO paisCreateDTO) throws Exception {
        // Mapear de DTO a entidad
        Pais nuevoPais = new Pais();
        nuevoPais.setNombre(paisCreateDTO.getNombre());
        nuevoPais.setCapital(paisCreateDTO.getCapital());
        nuevoPais.setSuperficie(paisCreateDTO.getSuperficie());
        nuevoPais.setContinenteId(paisCreateDTO.getContinenteId());

        nuevoPais = paisService.crearPais(nuevoPais);

        // Mapear de entidad a DTO de respuesta
        PaisResponseDTO responseDTO = new PaisResponseDTO(
                nuevoPais.getId(),
                nuevoPais.getNombre(),
                nuevoPais.getCapital(),
                nuevoPais.getSuperficie(),
                nuevoPais.getContinenteId(),
                nuevoPais.getProvincias(),
                nuevoPais.getLimitrofes()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Obtener país por ID (ahora devuelve un DTO)
    @GetMapping("/{id}")
    public ResponseEntity<PaisResponseDTO> obtenerPaisPorId(@PathVariable String id) throws Exception {
        Pais pais = paisService.obtenerPaisPorId(id);
        PaisResponseDTO responseDTO = new PaisResponseDTO(
                pais.getId(),
                pais.getNombre(),
                pais.getCapital(),
                pais.getSuperficie(),
                pais.getContinenteId(),
                pais.getProvincias(),
                pais.getLimitrofes()
        );
        return ResponseEntity.ok(responseDTO);
    }

    // Obtener país por nombre (ahora devuelve un DTO)
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PaisResponseDTO> obtenerPaisPorNombre(@PathVariable String nombre) throws Exception {
        Pais pais = paisService.obtenerPaisPorNombre(nombre);
        PaisResponseDTO responseDTO = new PaisResponseDTO(
                pais.getId(),
                pais.getNombre(),
                pais.getCapital(),
                pais.getSuperficie(),
                pais.getContinenteId(),
                pais.getProvincias(),
                pais.getLimitrofes()
        );
        return ResponseEntity.ok(responseDTO);
    }

    // Obtener todos los países (ahora devuelve una lista de DTOs)
    @GetMapping
    public ResponseEntity<List<PaisResponseDTO>> obtenerTodosLosPaises() {
        List<Pais> paises = paisService.obtenerTodosLosPaises();
        List<PaisResponseDTO> responseDTOs = paises.stream()
                .map(p -> new PaisResponseDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getCapital(),
                        p.getSuperficie(),
                        p.getContinenteId(),
                        p.getProvincias(),
                        p.getLimitrofes()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Eliminar país
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable String id) throws Exception {
        paisService.eliminarPais(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener países por ID de continente (ahora devuelve una lista de DTOs)
    @GetMapping("/continente/{continenteId}")
    public ResponseEntity<List<PaisResponseDTO>> obtenerPaisesPorContinenteId(@PathVariable String continenteId) {
        List<Pais> paises = paisService.obtenerPaisesPorContinenteId(continenteId);
        List<PaisResponseDTO> responseDTOs = paises.stream()
                .map(p -> new PaisResponseDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getCapital(),
                        p.getSuperficie(),
                        p.getContinenteId(),
                        p.getProvincias(),
                        p.getLimitrofes()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}
