package com.api.calendario.api_calendario.core.services;
import java.util.Optional;

import com.api.calendario.api_calendario.domain.entities.Calendario;

import java.util.Date;
import java.util.List;


public interface ICalendarioService {
    public List<Calendario> listar();
    public Optional<Calendario> obtener(int id);
    public List<Calendario> buscar(String fecha);
}
