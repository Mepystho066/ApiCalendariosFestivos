package com.api.calendario.api_calendario.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.calendario.api_calendario.domain.entities.TipoFestivo;
import com.api.calendario.api_calendario.infrastructure.repository.ITipoFestivoRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class DatabaseTestController {

    private final ITipoFestivoRepository repository;  // final!

    @Autowired
    public DatabaseTestController(ITipoFestivoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/repo")
    public String testRepo() {
        return "Repository: " + repository + 
               "\nClass: " + (repository != null ? repository.getClass().getName() : "NULL");
    }

    @GetMapping("/db/tipofestivo")
    public List<TipoFestivo> testListar() {
        List<TipoFestivo> result = repository.findAll();
        System.out.println("RESULTADO findAll: " + result);
        return result;
    }
}

