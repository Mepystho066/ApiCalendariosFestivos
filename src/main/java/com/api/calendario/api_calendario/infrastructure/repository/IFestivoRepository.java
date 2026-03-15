package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.calendario.api_calendario.domain.entities.Festivo;

@Repository
public interface IFestivoRepository  extends JpaRepository<Festivo, Integer>{
    @Query("SELECT c FROM Festivo c WHERE c.nombre LIKE CONCAT('%',:nombre,'%') ")
    public List <Festivo> buscar(String nombre);
    List<Festivo> findByPaisId(Integer paisId);

}
