package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_paciente")
public class Paciente {

    @Id
    @Column(name = "dni", length = 8)
    private String dni;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nac")
    private Date fechaNac;

    @Column(name = "sexo", length = 1)
    private String sexo;

    // CAMBIADO: Nombre con guion bajo para que funcione p.getEstado_civil()
    @Column(name = "estado_civil", length = 1)
    private String estado_civil;

    @Column(name = "ocupacion")
    private String ocupacion;
    
    // CAMBIADO: Nombre con guion bajo para que funcione p.getNum_hijos()
    @Column(name = "num_hijos")
    private Integer num_hijos;
    
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "direccion")
    private String direccion;

   
    @Column(name = "cod_distrito") 
    private String cod_distrito;

    @ManyToOne
    @JoinColumn(name = "cod_distrito", insertable = false, updatable = false) 
    private Distrito distrito;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

    public Paciente() {}

    // --- GETTERS Y SETTERS CORREGIDOS PARA EL CONTROLADOR ---

    public String getEstado_civil() { return estado_civil; }
    public void setEstado_civil(String estado_civil) { this.estado_civil = estado_civil; }

    public Integer getNum_hijos() { return num_hijos; }
    public void setNum_hijos(Integer num_hijos) { this.num_hijos = num_hijos; }

    public String getCod_distrito() { return cod_distrito; }
    public void setCod_distrito(String cod_distrito) { this.cod_distrito = cod_distrito; }

    // Otros getters y setters estándar...
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public Date getFechaNac() { return fechaNac; }
    public void setFechaNac(Date fechaNac) { this.fechaNac = fechaNac; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public Distrito getDistrito() { return distrito; }
    public void setDistrito(Distrito distrito) { this.distrito = distrito; }
    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica hc) { this.historiaClinica = hc; }
}