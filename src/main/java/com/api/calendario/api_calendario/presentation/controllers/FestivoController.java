package com.api.calendario.api_calendario.presentation.controllers;
import com.api.calendario.api_calendario.core.services.IFestivoService;
import com.api.calendario.api_calendario.domain.entities.Festivo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/festivo")
public class FestivoController {
    
    public IFestivoService service;

    public FestivoController(IFestivoService service){
        this.service = service;
    }

    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public List<Festivo> listar(){
        return service.listar();
    }
    @RequestMapping(value = "/obtener/{id}", method=RequestMethod.GET)
    public ResponseEntity<Festivo> obtener(@PathVariable("id") int id) {
        Optional<Festivo> festivoSv = service.obtener(id);
        return festivoSv
        .map(festivo->ResponseEntity.ok(festivo))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/buscar/{nombre}", method=RequestMethod.GET)
    public List<Festivo> buscar (@PathVariable("nombre") String nombre) {
        return service.buscar(nombre);
    }
    
    //Falta el verificar 

}
