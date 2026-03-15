package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.calendario.api_calendario.domain.entities.Calendario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ICalendarioRepository extends JpaRepository<Calendario, Integer> {

    @Query("SELECT c FROM Calendario c WHERE c.descripcion LIKE CONCAT('%',:descripcion,'%')")
    public List <Calendario> buscar(String descripcion);

    @Query("SELECT c FROM Calendario c WHERE c.pais.id = :idpais AND YEAR(c.fecha) = :año")
    List<Calendario> listar(@Param("idpais") Integer paisId, @Param("año") int año);
}
