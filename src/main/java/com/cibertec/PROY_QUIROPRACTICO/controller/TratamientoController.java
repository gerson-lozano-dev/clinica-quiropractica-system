package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.util.List; // ESTE IMPORT TE FALTA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Tratamiento; // ESTE IMPORT TE FALTA
import com.cibertec.PROY_QUIROPRACTICO.repository.TratamientoRepository;

@Controller
@RequestMapping("/gestiontratamiento")
public class TratamientoController {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        // Asegúrate que la variable abajo sea tratamientoRepository (con t minúscula)
        List<Tratamiento> lista = tratamientoRepository.findAll();
        model.addAttribute("listaTratamientos", lista);
        return "MantTratamiento"; 
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("tratamiento") Tratamiento t, RedirectAttributes redirectAttributes) {
        try {
            tratamientoRepository.save(t);
            redirectAttributes.addFlashAttribute("mensaje", "¡Tratamiento guardado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar el tratamiento.");
        }
        return "redirect:/gestiontratamiento/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            tratamientoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Tratamiento eliminado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el tratamiento.");
        }
        return "redirect:/gestiontratamiento/listar";
    }
}