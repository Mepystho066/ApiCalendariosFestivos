package com.api.calendario.api_calendario.infrastructure.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.calendario.api_calendario.domain.entities.TipoFestivo;

@Repository
public interface  ITipoFestivoRepository extends JpaRepository<TipoFestivo,Integer>{
    

    @Query("SELECT f FROM TipoFestivo f WHERE f.tipo LIKE CONCAT('%',:tipo,'%')")
    public List <TipoFestivo> buscar(String tipo);    
    // @Query("SELECT f.tipo FROM TipoFestivo f")
    // public List <TipoFestivo> listar(String tipo);
}
