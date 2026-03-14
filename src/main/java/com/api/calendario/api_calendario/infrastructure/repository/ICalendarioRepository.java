package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.api.calendario.api_calendario.domain.entities.Calendario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICalendarioRepository extends JpaRepository<Calendario, Integer> {

    @Query("SELECT c FROM Calendario c WHERE c.descripcion LIKE CONCAT('%',:descripcion,'%')")
    public List <Calendario> buscar(String descripcion);
  

}
