package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	private String nombres;
	private String apellidos;
	private String email;
	private String clave;
	private String cargo; // 'ASISTENTE' o 'MEDICO'

	public Usuario() {}

	// Getters y Setters
	public Integer getIdUsuario() { return idUsuario; }
	public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
	public String getNombres() { return nombres; }
	public void setNombres(String nombres) { this.nombres = nombres; }
	public String getApellidos() { return apellidos; }
	public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getClave() { return clave; }
	public void setClave(String clave) { this.clave = clave; }
	public String getCargo() { return cargo; }
	public void setCargo(String cargo) { this.cargo = cargo; }
}