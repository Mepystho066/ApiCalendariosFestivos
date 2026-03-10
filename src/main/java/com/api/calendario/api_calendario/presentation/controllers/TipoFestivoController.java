package com.api.calendario.api_calendario.presentation.controllers;
import com.api.calendario.api_calendario.core.services.ITipoFestivoService;
import com.api.calendario.api_calendario.domain.entities.TipoFestivo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/tipofestivo")
public class TipoFestivoController {

    public ITipoFestivoService service;

    public TipoFestivoController(ITipoFestivoService service){
        this.service = service;
    }

    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public List<TipoFestivo> listar(){
        return service.listar();

    }
    @RequestMapping(value = "/obtener/{id}", method=RequestMethod.GET)
    public TipoFestivo obtener(@PathVariable("id") Long id) {
       return service.obtener(id);
    }

    @RequestMapping(value = "/buscar/{tipo}", method=RequestMethod.GET)
    public List<TipoFestivo> buscar (@PathVariable("tipo") String tipo) {
        return service.buscar(tipo);
    }
    
    
}
