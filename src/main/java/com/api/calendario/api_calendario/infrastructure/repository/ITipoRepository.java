package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.calendario.api_calendario.domain.entities.Tipo;
@Repository
public interface ITipoRepository extends JpaRepository<Tipo,Integer>{

    @Query("SELECT p FROM Tipo p WHERE p.tipo LIKE CONCAT('%', :tipo, '%')")
    List<Tipo> buscar(String tipo);


}
