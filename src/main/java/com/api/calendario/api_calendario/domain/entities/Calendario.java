package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "calendario")
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Secuencia_de_calendario")
    @SequenceGenerator(name="Secuencia_de_calendario", sequenceName = "calendario_secuencia", allocationSize = 1)

    @Column(name = "id")
    private int id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha; 

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idtipo",referencedColumnName = "id", nullable = false)
    @PrimaryKeyJoinColumn
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "idpais",referencedColumnName = "id", nullable = false)
    @PrimaryKeyJoinColumn
    private Pais pais;


    public Calendario(){}
    
    public Calendario( LocalDate fecha, Tipo tipo, String descripcion, Pais pais){
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pais getPais() {
        return pais;
    }

    public void setIdpais(Pais pais) {
        this.pais = pais;
    }

}
