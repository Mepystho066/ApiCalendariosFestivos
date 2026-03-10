package com.api.calendario.api_calendario.domain.entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendario")
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Secuencia_de_calendario")
    @SequenceGenerator(name="Secuencia_de_calendario", sequenceName = "calendario_sencuencia", allocationSize = 1)

    @Column(name = "id")
    private int id;

    @Column(name = "fecha")
    private Date fecha; 

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idtipo",referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "idpais",referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Pais pais;


    public Calendario(){}
    
    public Calendario( Date fecha, Tipo tipo, String descripcion, Pais pais){
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
