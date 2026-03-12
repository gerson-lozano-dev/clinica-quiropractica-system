package com.cibertec.PROY_QUIROPRACTICO.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_historia_clinica")
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historia")
    private int idHistoria;

    @Column(name = "motivo_consulta")
    private String motivoConsulta; 

    @Column(name = "zonas_dolor")
    private String zonasDolor;

    @Column(name = "antecedente_trauma")
    private String antecedenteTrauma;

    @Column(name = "antecedente_cirugia")
    private String antecedenteCirugia;

    @Column(name = "escala_dolor_inicial")
    private int escalaDolorInicial;

    @Column(name = "tiene_asma")
    private boolean tieneAsma;

    @Column(name = "tiene_diabetes")
    private boolean tieneDiabetes;

    @Column(name = "tiene_alergias")
    private boolean tieneAlergias;

    private String observaciones;

    @OneToOne
    @JoinColumn(name = "dni_paciente")
    private Paciente paciente;

    // --- GETTERS Y SETTERS 
    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getZonasDolor() { return zonasDolor; }
    public void setZonasDolor(String zonasDolor) { this.zonasDolor = zonasDolor; }

    public String getAntecedenteTrauma() { return antecedenteTrauma; }
    public void setAntecedenteTrauma(String antecedenteTrauma) { this.antecedenteTrauma = antecedenteTrauma; }

    public String getAntecedenteCirugia() { return antecedenteCirugia; }
    public void setAntecedenteCirugia(String antecedenteCirugia) { this.antecedenteCirugia = antecedenteCirugia; }

    public int getEscalaDolorInicial() { return escalaDolorInicial; }
    public void setEscalaDolorInicial(int escalaDolorInicial) { this.escalaDolorInicial = escalaDolorInicial; }

    public boolean isTieneAsma() { return tieneAsma; }
    public void setTieneAsma(boolean tieneAsma) { this.tieneAsma = tieneAsma; }

    public boolean isTieneDiabetes() { return tieneDiabetes; }
    public void setTieneDiabetes(boolean tieneDiabetes) { this.tieneDiabetes = tieneDiabetes; }

    public boolean isTieneAlergias() { return tieneAlergias; }
    public void setTieneAlergias(boolean tieneAlergias) { this.tieneAlergias = tieneAlergias; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
}