package com.cibertec.PROY_QUIROPRACTICO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Usuario;
import com.cibertec.PROY_QUIROPRACTICO.repository.CitaRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestionatencion")
public class AtencionController {

    @Autowired
    private CitaRepository citaRepository;

    // 1. LISTAR: 
    @GetMapping("/listar")
    public String listar(Model model, HttpSession session) {
        
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("listaCitas", citaRepository.findByEstado("PENDIENTE")); 
        return "AtencionCitas"; 
    }

  
    @GetMapping("/buscar")
    public String buscar(@RequestParam("dni") String dni, Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("dniBusqueda", dni);
        model.addAttribute("listaCitas", citaRepository.findByDniPacienteAndEstado(dni, "PENDIENTE"));
        return "AtencionCitas";
    }

   
    @PostMapping("/marcar")
    public String marcarAsistencia(@RequestParam("id_cita") int idCita, 
                                   @RequestParam(value="dni_actual", required=false) String dni,
                                   @RequestParam(value="diagnostico", required=false) String notas,
                                   HttpSession session, 
                                   RedirectAttributes redirectAttributes) {
        

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        try {
          
            citaRepository.marcarAsistencia(idCita, notas != null && !notas.isEmpty() ? notas : "Atención completada");
            redirectAttributes.addFlashAttribute("mensaje", "Atención registrada con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "No se pudo registrar la atención.");
        }
        
        if (dni != null && !dni.isEmpty()) {
            return "redirect:/gestionatencion/buscar?dni=" + dni;
        }
        return "redirect:/gestionatencion/listar";
    }

   
    @GetMapping("/historial")
    public String mostrarHistorial(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("listaAtendidos", citaRepository.findByEstado("ATENDIDO"));
        return "HistorialAtencion"; 
    }
}