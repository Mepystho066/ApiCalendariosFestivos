package com.api.calendario.api_calendario.application.services;
import com.api.calendario.api_calendario.core.services.IFestivoService;
import com.api.calendario.api_calendario.domain.entities.Festivo;
import com.api.calendario.api_calendario.infrastructure.repository.IFestivoRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class FestivoService implements IFestivoService{

    public IFestivoRepository repository;
    
    public FestivoService( IFestivoRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Festivo> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Festivo> obtener(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return repository.buscar(nombre);
    }

}
