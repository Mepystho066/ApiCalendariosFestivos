package com.api.calendario.api_calendario.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.calendario.api_calendario.core.services.ICalendarioService;
import com.api.calendario.api_calendario.domain.entities.Calendario;

@RestController
@RequestMapping("/calendario")
public class CalendarioController {
    
    public ICalendarioService service;

    public CalendarioController(ICalendarioService service){
        this.service = service;
    }

    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public List<Calendario> listar(){
        return service.listar();
    }
    @RequestMapping(value = "/obtener/{id}", method=RequestMethod.GET)
    public ResponseEntity<Calendario> obtener(@PathVariable("id") int id) {
        Optional<Calendario> calendarioSv = service.obtener(id);
        return calendarioSv
        .map(calendario->ResponseEntity.ok(calendario))
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @RequestMapping(value = "/buscar/{descripcion}", method=RequestMethod.GET)
    public List<Calendario> buscar (@PathVariable("descripcion") String descripcion) {
        return service.buscar(descripcion);
    }


}
