package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_hoja_pago") 
public class Venta {
    @Id
    @Column(name = "nro_hp")
    private String nroHp; // 

    @Column(name = "dni_paciente")
    private String dniPaciente;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_tratamiento")
    private Integer idTratamiento;

    @Column(name = "total_sesiones")
    private Integer totalSesiones;

    @Column(name = "costo_total")
    private Double costoTotal;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    // --- GETTERS Y SETTERS ---
    public String getNroHp() { return nroHp; }
    public void setNroHp(String nroHp) { this.nroHp = nroHp; }
    public String getDniPaciente() { return dniPaciente; }
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }
    public Integer getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(Integer idTratamiento) { this.idTratamiento = idTratamiento; }
    public Double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(Double costoTotal) { this.costoTotal = costoTotal; }
}