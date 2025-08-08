package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Pais;
import java.util.List;

public interface IPaisService {
    Pais crearPais(Pais pais) throws Exception;
    Pais obtenerPaisPorId(String id) throws Exception;
    Pais obtenerPaisPorNombre(String nombre) throws Exception;
    List<Pais> obtenerTodosLosPaises();
    void eliminarPais(String id) throws Exception;
    List<Pais> obtenerPaisesPorContinenteId(String continenteId);
}