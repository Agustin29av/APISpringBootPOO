package com.uader.poo.controller.tp4;

import com.uader.poo.entity.tp4.Provincia;
import com.uader.poo.service.tp4.IProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    private final IProvinciaService provinciaService;

    @Autowired
    public ProvinciaController(IProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    @PostMapping
    public ResponseEntity<Provincia> crearProvincia(@RequestBody Provincia provincia) {
        Provincia nuevaProvincia = provinciaService.crearProvincia(provincia);
        return new ResponseEntity<>(nuevaProvincia, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provincia> obtenerProvinciaPorId(@PathVariable String id) {
        return ResponseEntity.ok(provinciaService.obtenerProvinciaPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Provincia> obtenerProvinciaPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(provinciaService.obtenerProvinciaPorNombre(nombre));
    }
    
    @GetMapping
    public ResponseEntity<List<Provincia>> obtenerTodasLasProvincias() {
        return ResponseEntity.ok(provinciaService.obtenerTodasLasProvincias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProvincia(@PathVariable String id) {
        provinciaService.eliminarProvincia(id);
        return ResponseEntity.noContent().build();
    }

    // NUEVO: Obtener provincias por ID de país
    @GetMapping("/pais/{paisId}")
    public ResponseEntity<List<Provincia>> obtenerProvinciasPorPaisId(@PathVariable String paisId) {
        List<Provincia> provincias = provinciaService.obtenerProvinciasPorPaisId(paisId);
        return ResponseEntity.ok(provincias);
    }
}