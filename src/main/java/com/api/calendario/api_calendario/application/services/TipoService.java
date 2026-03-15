package com.api.calendario.api_calendario.application.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.api.calendario.api_calendario.core.services.ITipoService;
import com.api.calendario.api_calendario.domain.entities.Tipo;
import com.api.calendario.api_calendario.infrastructure.repository.ITipoRepository;

@Service
public class TipoService implements ITipoService {
 private ITipoRepository repository;
    
    public TipoService( ITipoRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Tipo> listar() {
        return repository.findAll();
    }

    @Override
    public Tipo obtener(int id) {
        Optional <Tipo> tipo = repository.findById(id);
        return tipo.isEmpty() ? null : tipo.get();
    }

    @Override
    public List<Tipo> buscar(String tipo) {
        return repository.buscar(tipo);
    }   
}
