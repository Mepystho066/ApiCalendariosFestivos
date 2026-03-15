package com.api.calendario.api_calendario.core.services;

import java.util.List;

import com.api.calendario.api_calendario.domain.entities.Tipo;

public interface ITipoService {
    public List<Tipo> listar();
    public Tipo obtener(int id);
    public List<Tipo> buscar(String tipo);
}
