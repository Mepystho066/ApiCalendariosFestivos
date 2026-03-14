package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "tipofestivo")
public class TipoFestivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Secuencia_de_tipofestivo")
    @SequenceGenerator(name = "Secuencia_de_tipofestivo", sequenceName = "tipofestivo_secuencia", allocationSize = 1)

    @Column(name = "id")
    private int id;

    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    public TipoFestivo(){

    }

    public TipoFestivo(String tipo){
        this.tipo = tipo; 
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id; 
    }

     public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo; 
    }

    public Object map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
