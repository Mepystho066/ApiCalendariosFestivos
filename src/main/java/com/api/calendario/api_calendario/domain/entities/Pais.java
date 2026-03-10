package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator =  "Secuencia_de_pais")
    @SequenceGenerator(name = "Secuencia_de_pais", sequenceName = "pais_secuencia", allocationSize = 1)

    @Column(name = "id")
    private int id; 

    @Column(name = "nombre")
    private String nombre; 
    
    public Pais(){}

    public Pais(String nombre ){
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
