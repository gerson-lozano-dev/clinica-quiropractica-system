package com.cibertec.PROY_QUIROPRACTICO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cibertec.PROY_QUIROPRACTICO.model.Cita;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

   
    List<Cita> findByEstado(String estado);

    
    List<Cita> findByDniPacienteAndEstado(String dni, String estado);

    @Query("SELECT c FROM Cita c WHERE c.dniPaciente = :dni")
    List<Cita> listarCitasPorDni(@Param("dni") String dni);

    @Transactional
    @Modifying
    @Query("UPDATE Cita c SET c.diagnostico = :texto, c.estado = 'ATENDIDO' WHERE c.idCita = :idCita")
    void marcarAsistencia(@Param("idCita") int idCita, @Param("texto") String texto);
}