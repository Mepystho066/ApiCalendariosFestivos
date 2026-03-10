package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Secuencia_de_tipo")
    @SequenceGenerator(name = "Secuencia_de_tipo", sequenceName = "tipo_id_sequencia", allocationSize = 1)
   
    @Column(name="id")
    private int id;
    
    @Column(name="tipo")
    private String tipo;
    
    public Tipo(){} 

    public Tipo(String tipo){
        this.tipo = tipo;
    }

    public int getId(){
        return this.id;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
