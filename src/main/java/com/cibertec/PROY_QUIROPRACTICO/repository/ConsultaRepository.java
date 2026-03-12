package com.cibertec.PROY_QUIROPRACTICO.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.PROY_QUIROPRACTICO.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    // Método para filtrar por estado
    List<Consulta> findByEstado(String estado);
}