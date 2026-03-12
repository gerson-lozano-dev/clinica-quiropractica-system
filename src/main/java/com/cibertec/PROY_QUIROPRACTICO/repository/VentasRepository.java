package com.cibertec.PROY_QUIROPRACTICO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cibertec.PROY_QUIROPRACTICO.model.Venta;


public interface VentasRepository extends JpaRepository<Venta, String> {
}