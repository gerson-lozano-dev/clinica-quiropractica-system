package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.util.List;
import java.util.Map; // AGREGADO: Importación necesaria
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Paciente;
import com.cibertec.PROY_QUIROPRACTICO.model.Distrito;
import com.cibertec.PROY_QUIROPRACTICO.repository.PacienteRepository;
import com.cibertec.PROY_QUIROPRACTICO.repository.DistritoRepository;

@Controller
@RequestMapping("/gestionpaciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository; 

    @Autowired
    private DistritoRepository distritoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        model.addAttribute("listaDistritos", distritoRepository.findAll());
       
        if (!model.containsAttribute("pacienteEditar")) {
            model.addAttribute("pacienteEditar", new Paciente());
        }
        return "MantPaciente";
    }

    @GetMapping("/editar/{dni}")
    public String mostrarEditar(@PathVariable("dni") String dni, Model model) {
        
        Paciente p = pacienteRepository.findById(dni).orElseThrow();
        model.addAttribute("paciente", p);
        model.addAttribute("distritos", distritoRepository.findAll());
        return "MantPaciente"; 
    } 

    @PostMapping("/actualizar")
    public String actualizarPaciente(@ModelAttribute Paciente p, @RequestParam Map<String, String> allParams) {
        try {
            
            pacienteRepository.actualizarPaciente(
                p.getDni(), 
                p.getNombres(), 
                p.getApellidos(),
                allParams.get("fecha_nac"), 
                p.getSexo(), 
                p.getEstado_civil(),
                p.getOcupacion(), 
                p.getNum_hijos(), 
                p.getTelefono(), 
                p.getEmail(),
                p.getDireccion(), 
                p.getCod_distrito(),
                allParams.get("motivo"), 
                allParams.get("zonas"), 
                allParams.get("trauma"), 
                allParams.get("cirugia"),
                Integer.parseInt(allParams.get("escala")),
                Boolean.parseBoolean(allParams.get("asma")),
                Boolean.parseBoolean(allParams.get("diabetes")),
                Boolean.parseBoolean(allParams.get("alergias")),
                allParams.get("observaciones")
            );
            return "redirect:/gestionpaciente/listar?editExito=1";
        } catch (Exception e) {
            e.printStackTrace(); 
            return "redirect:/gestionpaciente/listar?editError=1";
        }
    }
    @PostMapping("/guardar")
    public String guardarPaciente(@ModelAttribute Paciente p, @RequestParam Map<String, String> allParams) {
        
        System.out.println(">>> DNI RECIBIDO: " + p.getDni());
        System.out.println(">>> MOTIVO RECIBIDO: " + allParams.get("motivo"));
        System.out.println(">>> FECHA RECIBIDA: " + allParams.get("fecha_nac"));

        try {
        	pacienteRepository.registrarPaciente(
        		    p.getDni(), p.getNombres(), p.getApellidos(),
        		    allParams.get("fecha_nac"), p.getSexo(), p.getEstado_civil(),
        		    p.getOcupacion(), p.getNum_hijos(), p.getTelefono(), p.getEmail(),
        		    p.getDireccion(), p.getCod_distrito(),
        		    allParams.get("motivo"), allParams.get("zonas"), 
        		    allParams.get("trauma"), allParams.get("cirugia"),
        		    Integer.parseInt(allParams.get("escala")),
        		    Boolean.parseBoolean(allParams.get("asma")),
        		    Boolean.parseBoolean(allParams.get("diabetes")),
        		    Boolean.parseBoolean(allParams.get("alergias")),
        		    allParams.get("observaciones")
        		);
            return "redirect:/gestionpaciente/listar?mensaje=Paciente+registrado+con+exito";
        } catch (Exception e) {
            System.err.println("ERROR AL GUARDAR: " + e.getMessage());
            e.printStackTrace(); 
            return "redirect:/gestionpaciente/listar?error=1";
        }
    }
 // AGREGAR AL FINAL DE PacienteController.java
    @GetMapping("/eliminar/{dni}")
    public String eliminarPaciente(@PathVariable("dni") String dni) {
        try {
            pacienteRepository.eliminarPaciente(dni);
            return "redirect:/gestionpaciente/listar?mensaje=Paciente+eliminado+correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/gestionpaciente/listar?error=1";
        }
    }
}