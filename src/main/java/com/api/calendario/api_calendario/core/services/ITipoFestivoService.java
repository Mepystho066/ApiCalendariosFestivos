package com.api.calendario.api_calendario.core.services;
import java.util.List;

import com.api.calendario.api_calendario.domain.entities.TipoFestivo;


public interface ITipoFestivoService {
    public List<TipoFestivo> listar();
    public TipoFestivo obtener(int id);
    public List<TipoFestivo> buscar(String tipo);
}
