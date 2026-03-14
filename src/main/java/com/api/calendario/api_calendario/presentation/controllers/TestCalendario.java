package com.api.calendario.api_calendario.presentation.controllers;

import java.time.LocalDate;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.calendario.api_calendario.application.services.TestCalendioService;
import com.api.calendario.api_calendario.domain.entities.Festivo;


@RestController
@RequestMapping("/api/calendario2")  // ← / al inicio
public class TestCalendario {
    public TestCalendioService testService;

    public TestCalendario(TestCalendioService testService) {
        this.testService = testService;
    }

    @GetMapping("/test/{año}")  // ← @GetMapping más claro
    public Map<String,LocalDate> requestMethodName(@PathVariable int año) {  // ← @PathVariable
        return testService.domingoRamosYPascua(año); // ¿No deberías retornar 'dia'?
    }
    @GetMapping("/test2/{año}")  // ← @GetMapping más claro
    public  List<Festivo> validarLunes(@PathVariable int año) {
        return testService.Logica(año);
    }
}


