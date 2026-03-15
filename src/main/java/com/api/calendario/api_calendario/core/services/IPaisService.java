package com.api.calendario.api_calendario.core.services;

import java.util.*;

import com.api.calendario.api_calendario.domain.entities.Pais;

public interface IPaisService {
    public List<Pais> listar();   
    public Optional<Pais> obtener(int id);
    public List<Pais> buscar(String nombre);
}
