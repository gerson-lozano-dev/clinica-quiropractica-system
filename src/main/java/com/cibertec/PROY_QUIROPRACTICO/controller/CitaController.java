package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Cita;
import com.cibertec.PROY_QUIROPRACTICO.repository.CitaRepository;
import com.cibertec.PROY_QUIROPRACTICO.repository.PacienteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestioncita")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;

    // --- 1. RUTA PARA MOSTRAR EL FORMULARIO DE NUEVA CITA (Soluciona el 404) ---
    @GetMapping("/nueva")
    public String nuevaCita(Model model, HttpSession session) {
        // Validar que el usuario esté logueado
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        
        // Enviamos un objeto vacío para llenar en el formulario
        model.addAttribute("cita", new Cita());
        // Cargamos la lista de pacientes para que aparezcan en el combo box
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        
        return "NuevaConsulta"; // Debe coincidir con tu archivo NuevaConsulta.html
    }

    // --- 2. RUTA PARA LISTAR LAS CITAS ---
    @GetMapping("/listar")
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        
        List<Cita> lista = citaRepository.findAll();
        model.addAttribute("listaCitas", lista);
        
        return "AtencionCitas"; // Debe coincidir con tu archivo AtencionCitas.html
    }

    // --- 3. RUTA PARA GUARDAR LA CITA ---
    @PostMapping("/registrar")
    public String guardar(@ModelAttribute("cita") Cita c, RedirectAttributes redirectAttributes) {
        try {
            // Si el estado viene nulo, lo ponemos como PENDIENTE por defecto
            if (c.getEstado() == null || c.getEstado().isEmpty()) {
                c.setEstado("PENDIENTE");
            }
            
            citaRepository.save(c);
            redirectAttributes.addFlashAttribute("mensaje", "¡Cita reservada con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al reservar la cita.");
        }
        return "redirect:/gestioncita/listar";
    }

    // --- 4. RUTA PARA ELIMINAR (Aquí estaba el error rojo) ---
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            // CORREGIDO: Pasamos el 'id' (int) directamente, sin convertir a String
            citaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la cita.");
        }
        return "redirect:/gestioncita/listar";
    }
}