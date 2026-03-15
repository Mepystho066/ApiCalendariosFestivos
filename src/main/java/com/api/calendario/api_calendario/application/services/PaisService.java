package com.api.calendario.api_calendario.application.services;
import com.api.calendario.api_calendario.infrastructure.repository.IPaisRepository;

import java.util.*;

import org.springframework.stereotype.Service;

import com.api.calendario.api_calendario.core.services.IPaisService;
import com.api.calendario.api_calendario.domain.entities.Pais;


@Service
public class PaisService implements IPaisService {
    IPaisRepository repository;
    
    public PaisService( IPaisRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Pais>listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Pais> obtener(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Pais> buscar(String descripcion) {
        return repository.buscar(descripcion);
    }



}
