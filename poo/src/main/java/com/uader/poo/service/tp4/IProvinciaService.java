package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Provincia;
import java.util.List;

public interface IProvinciaService {
    Provincia crearProvincia(Provincia provincia) throws Exception;
    Provincia obtenerProvinciaPorId(String id) throws Exception;
    Provincia obtenerProvinciaPorNombre(String nombre) throws Exception;
    List<Provincia> obtenerTodasLasProvincias();
    void eliminarProvincia(String id) throws Exception;
    List<Provincia> obtenerProvinciasPorPaisId(String paisId);
}