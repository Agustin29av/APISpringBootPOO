package com.uader.poo.service.tp4;

import com.uader.poo.entity.tp4.Continente;
import java.util.List;

public interface IContinenteService {
    Continente crearContinente(Continente continente) throws Exception;
    Continente obtenerContinentePorId(String id) throws Exception;
    Continente obtenerContinentePorNombre(String nombre) throws Exception;
    List<Continente> obtenerTodosLosContinentes();
    void eliminarContinente(String id) throws Exception;
    Continente agregarPaisAContinente(String nombreContinente, String nombrePais) throws Exception;
}