package com.api.calendario.api_calendario.core.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.yaml.snakeyaml.events.Event.ID;

import com.api.calendario.api_calendario.domain.entities.Festivo;

public interface IFestivoService {
    public List<Festivo> listar();   
    public List<Festivo> festivopaises(int id);
    public Optional<Festivo> obtener(int id);
    public List<Festivo> buscar(String nombre);
    //public boolean verificar(Date fecha);
    // public Festivo agregar(Festivo festivo);
    // public Festivo modificar(Festivo festivo);
    // public boolean eliminar(int id);
    // public List logica(int año);
} 
