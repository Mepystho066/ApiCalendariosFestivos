package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.calendario.api_calendario.domain.entities.Pais;

@Repository
public interface IPaisRepository extends JpaRepository<Pais,Integer>{

    @Query("SELECT p FROM Pais p WHERE p.nombre LIKE CONCAT('%', :nombre, '%')")
    List<Pais> buscar(String nombre);


}
