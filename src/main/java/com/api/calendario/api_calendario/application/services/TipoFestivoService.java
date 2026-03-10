package com.api.calendario.api_calendario.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.calendario.api_calendario.core.services.ITipoFestivoService;
import com.api.calendario.api_calendario.domain.entities.TipoFestivo;
import com.api.calendario.api_calendario.infrastructure.repository.ITipoFestivoRepository;

@Service
public class TipoFestivoService implements ITipoFestivoService {

    private ITipoFestivoRepository repository;
    
    public TipoFestivoService( ITipoFestivoRepository repository){
        this.repository = repository;
    }

    @Override
    public List<TipoFestivo> listar() {
        return repository.findAll();
    }

    @Override
    public TipoFestivo obtener(Long id) {
        Optional <TipoFestivo> tipo = repository.findById(id);
        return tipo.isEmpty() ? null : tipo.get();
    }

    @Override
    public List<TipoFestivo> buscar(String tipo) {
        return repository.buscar(tipo);
    }   

}
