package com.cibertec.PROY_QUIROPRACTICO.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.PROY_QUIROPRACTICO.model.HojaPago;

public interface HojaPagoRepository extends JpaRepository<HojaPago, String> {

    @Query(value = "{call sp_reporte_ventas()}", nativeQuery = true)
    List<Map<String, Object>> obtenerReporteVentas();

    @Query(value = "{call sp_reporte_pacientes_distrito()}", nativeQuery = true)
    List<Map<String, Object>> obtenerPacientesPorDistrito();

    @Query(value = "{call sp_reporte_citas_mensuales()}", nativeQuery = true)
    List<Map<String, Object>> obtenerCitasMensuales();
 
    @Query(value = "{call sp_buscar_venta_x_id(:nro_hp)}", nativeQuery = true)
    Map<String, Object> buscarVentaPorId(@Param("nro_hp") String nro_hp);
}