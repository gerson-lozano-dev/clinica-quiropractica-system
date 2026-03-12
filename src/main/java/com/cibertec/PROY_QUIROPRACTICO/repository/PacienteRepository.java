package com.cibertec.PROY_QUIROPRACTICO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; // AGREGADO
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional; // AGREGADO
import com.cibertec.PROY_QUIROPRACTICO.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, String> {

    // 1. REGISTRAR
    @Transactional
    @Modifying
    @Query(value = "{call sp_registrar_paciente(:dni, :nom, :ape, :fec, :sex, :est, :ocu, :hijos, :tel, :mail, :dir, :dist, :mot, :zon, :antT, :antC, :esc, :asma, :diab, :aler, :obs)}", nativeQuery = true)
    void registrarPaciente(
        @Param("dni") String dni, @Param("nom") String nom, @Param("ape") String ape,
        @Param("fec") String fec, @Param("sex") String sex, @Param("est") String est,
        @Param("ocu") String ocu, @Param("hijos") int hijos, @Param("tel") String tel,
        @Param("mail") String mail, @Param("dir") String dir, @Param("dist") String dist,
        @Param("mot") String mot, @Param("zon") String zon, @Param("antT") String antT,
        @Param("antC") String antC, @Param("esc") int esc, @Param("asma") boolean asma,
        @Param("diab") boolean diab, @Param("aler") boolean aler, @Param("obs") String obs
    );

    // 2. ACTUALIZAR
    @Transactional
    @Modifying
    @Query(value = "{call sp_actualizar_paciente(:dni, :nom, :ape, :fec, :sex, :est, :ocu, :hijos, :tel, :mail, :dir, :dist, :mot, :zon, :antT, :antC, :esc, :asma, :diab, :aler, :obs)}", nativeQuery = true)
    void actualizarPaciente(
        @Param("dni") String dni, @Param("nom") String nom, @Param("ape") String ape,
        @Param("fec") String fec, @Param("sex") String sex, @Param("est") String est,
        @Param("ocu") String ocu, @Param("hijos") int hijos, @Param("tel") String tel,
        @Param("mail") String mail, @Param("dir") String dir, @Param("dist") String dist,
        @Param("mot") String mot, @Param("zon") String zon, @Param("antT") String antT,
        @Param("antC") String antC, @Param("esc") int esc, @Param("asma") boolean asma,
        @Param("diab") boolean diab, @Param("aler") boolean aler, @Param("obs") String obs
    );

    // 3. ELIMINAR
    @Transactional
    @Modifying
    @Query(value = "{call sp_eliminar_paciente(:dni)}", nativeQuery = true)
    void eliminarPaciente(@Param("dni") String dni);
}