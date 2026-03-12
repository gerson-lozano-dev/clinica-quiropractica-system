package com.cibertec.PROY_QUIROPRACTICO.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.PROY_QUIROPRACTICO.model.Paciente;
import com.cibertec.PROY_QUIROPRACTICO.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public List<Paciente> listarTodos() {
		return repo.findAll();
	}

	public void guardar(Paciente p) {
		repo.save(p);
	}

	// OJO: Ahora recibimos un String dni
	public Paciente buscarPorDni(String dni) {
		return repo.findById(dni).orElse(null);
	}

	// OJO: Ahora eliminamos por String dni
	public void eliminar(String dni) {
		repo.deleteById(dni);
	}
}