package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_distrito")
public class Distrito {
    @Id
    @Column(name = "cod_distrito")
    private String codDistrito; // 

    @Column(name = "nombre_distrito")
    private String nombreDistrito; // 

    // Getters y Setters
    public String getCodDistrito() { return codDistrito; }
    public void setCodDistrito(String codDistrito) { this.codDistrito = codDistrito; }

    public String getNombreDistrito() { return nombreDistrito; }
    public void setNombreDistrito(String nombreDistrito) { this.nombreDistrito = nombreDistrito; }
}