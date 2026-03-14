package com.api.calendario.api_calendario.application.services;
import com.api.calendario.api_calendario.core.services.ICalendarioService;
import com.api.calendario.api_calendario.domain.entities.Calendario;
import com.api.calendario.api_calendario.infrastructure.repository.ICalendarioRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
public class CalendarioService implements ICalendarioService {

    public ICalendarioRepository repository;
    
    public CalendarioService( ICalendarioRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Calendario> listar() {
        return repository.findAll();
    }

    @Override
    public Optional obtener(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Calendario> buscar(String descripcion) {
        return repository.buscar(descripcion);
    }



}
