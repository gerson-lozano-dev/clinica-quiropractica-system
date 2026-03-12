package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tratamiento") // Verifica que este nombre coincida con tu BD
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamiento")
    private int idTratamiento;

    private String nombre;
    private double precio;
    private String descripcion;

    public Tratamiento() {}

    // GETTERS Y SETTERS (Indispensables para que @ModelAttribute funcione)
    public int getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(int idTratamiento) { this.idTratamiento = idTratamiento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
