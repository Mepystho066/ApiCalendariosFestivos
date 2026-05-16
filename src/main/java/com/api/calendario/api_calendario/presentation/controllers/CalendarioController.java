package com.api.calendario.api_calendario.presentation.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.api.calendario.api_calendario.application.services.TestCalendioService;
import com.api.calendario.api_calendario.core.services.ICalendarioService;
import com.api.calendario.api_calendario.core.services.IFestivoService;
import com.api.calendario.api_calendario.domain.entities.Calendario;
import com.api.calendario.api_calendario.domain.entities.Festivo;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/calendario")
public class CalendarioController {
    
    public ICalendarioService service;
    public TestCalendioService testService;
    public IFestivoService festivoService;
    public CalendarioController(ICalendarioService service, TestCalendioService testService, IFestivoService festivoService){
        this.service = service;
        this.testService = testService;
        this.festivoService = festivoService;
    }
  
    @GetMapping("/") 
        public  String home() {
        return "Inicio Correcto";
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
  

    @GetMapping("/festivos/{pais}/{año}") 
        public  List logica(@PathVariable int pais, @PathVariable int año) {
        return testService.logica(pais,año);
    }

    @GetMapping("/verificar/{pais}/{año}/{mes}/{dias}") 
        public  String verificarFestivo(@PathVariable int pais, @PathVariable int año , @PathVariable int mes , @PathVariable int dias) {
        String mensaje = testService.validar(pais,año, mes, dias);
        return mensaje;
    }

    @GetMapping("/generar/{pais}/{año}") 
        public  boolean generarCalendario(@PathVariable int pais, @PathVariable int año) {
        return testService.listarCalendarioPrincipal(pais,año);
    }
    @GetMapping("/listar/{pais}/{año}") 
        public  List<Calendario> Listar(@PathVariable int pais, @PathVariable int año) {
        return service.listar(pais,año);
    }

    
}
