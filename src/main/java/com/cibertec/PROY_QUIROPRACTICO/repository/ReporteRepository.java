package com.cibertec.PROY_QUIROPRACTICO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cibertec.PROY_QUIROPRACTICO.model.Paciente;
import com.cibertec.PROY_QUIROPRACTICO.model.ReporteDistrito;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Paciente, String> {

    @Query("SELECT new com.cibertec.PROY_QUIROPRACTICO.model.ReporteDistrito(p.distrito.nombreDistrito, COUNT(p)) " +
           "FROM Paciente p GROUP BY p.distrito.nombreDistrito")
    List<ReporteDistrito> listarEstadisticaDistrito();
}