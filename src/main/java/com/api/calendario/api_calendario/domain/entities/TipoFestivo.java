package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "tipofestivo")
public class TipoFestivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Secuencia_de_tipofestivo")
    @SequenceGenerator(name = "Secuencia_de_tipofestivo", sequenceName = "tipofestivo_secuencia", allocationSize = 1)

    @Column(name = "id")
    private long id;

    @Column(name = "tipo")
    private String tipo;
    
    public TipoFestivo(){

    }

    public TipoFestivo(Long id, String tipo){
        this.tipo = tipo; 
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id; 
    }

     public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo; 
    }
}
