package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_hoja_pago")
public class HojaPago {
    
    @Id
  
    @Column(name = "nro_hp")
    private String nroHp; 

    @Column(name = "dni_paciente")
    private String dniPaciente;

    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "total_sesiones")
    private int totalSesiones;

    @Column(name = "costo_total")
    private double costoTotal;

   
    @Column(name = "id_tratamiento")
    private Integer idTratamiento;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDate fechaCreacion;

    public HojaPago() {}

    // --- GETTERS Y SETTERS CORREGIDOS ---

    public String getNroHp() { return nroHp; }
    public void setNroHp(String nroHp) { this.nroHp = nroHp; }

    public String getDniPaciente() { return dniPaciente; }
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getTotalSesiones() { return totalSesiones; }
    public void setTotalSesiones(int totalSesiones) { this.totalSesiones = totalSesiones; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

    public Integer getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(Integer idTratamiento) { this.idTratamiento = idTratamiento; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}