package com.api.calendario.api_calendario.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "festivo")
public class Festivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator =  "Secuencia_de_festivo")
    @SequenceGenerator(name = "Secuencia_de_festivo", sequenceName = "festivo_secuencia", allocationSize = 1)

    @Column(name = "id")
    private int id; 

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dias")
    private int dias;

    @Column(name = "mes")
    private int mes; 

    @Column(name = "diaspascua") 
    private int diaspascua; 

    @ManyToOne
    @JoinColumn(name = "idtipo",referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private TipoFestivo tipo;

    @ManyToOne
    @JoinColumn(name = "idpais",referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Pais pais;

    public Festivo(){}
    public Festivo(Pais pais , String nombre , int dias , int mes , int diaspascua , TipoFestivo tipo ){
        this.pais =pais; 
        this.nombre = nombre; 
        this.dias = dias; 
        this.mes = mes; 
        this.diaspascua = diaspascua; 
        this.tipo = tipo ; 
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
    public int getDias() {
        return dias;
    }
    public void setDias(int dias) {
        this.dias = dias;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getDiaspascua() {
        return diaspascua;
    }
    public void setDiaspascua(int diaspascua) {
        this.diaspascua = diaspascua;
    }
    public TipoFestivo getTipo() {
        return tipo;
    }
    public void setTipo(TipoFestivo tipo) {
        this.tipo = tipo;
    }
       public Pais getIdpais() {
        return pais;
    }
    public void setIdpais(Pais pais) {
        this.pais = pais;
    }
}
