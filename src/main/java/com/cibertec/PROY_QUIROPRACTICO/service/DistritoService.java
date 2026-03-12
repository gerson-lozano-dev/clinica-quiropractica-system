package com.cibertec.PROY_QUIROPRACTICO.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.PROY_QUIROPRACTICO.model.Distrito;
import com.cibertec.PROY_QUIROPRACTICO.repository.DistritoRepository;

@Service
public class DistritoService {

	@Autowired
	private DistritoRepository repo;

	public List<Distrito> listarTodos() {
		return repo.findAll();
	}
}