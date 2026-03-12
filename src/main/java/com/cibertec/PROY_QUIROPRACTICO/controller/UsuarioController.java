package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Usuario;
import com.cibertec.PROY_QUIROPRACTICO.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession; // Indispensable para roles

@Controller
@RequestMapping("/gestionusuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. LISTAR: ¡SOLO ADMIN!
    @GetMapping("/listar")
    public String listar(Model model, HttpSession session) {
        Usuario uLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        // Verificamos si es ADMIN
        if (uLogueado == null || !uLogueado.getCargo().equals("ADMIN")) {
            return "redirect:/index?error=acceso_denegado";
        }

        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "MantUsuario";
    }

    // 2. GUARDAR: ¡SOLO ADMIN!
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") Usuario u, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario uLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (uLogueado == null || !uLogueado.getCargo().equals("ADMIN")) {
            return "redirect:/index?error=acceso_denegado";
        }

        try {
            usuarioRepository.save(u);
            redirectAttributes.addFlashAttribute("mensaje", "¡Usuario gestionado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error al procesar el usuario.");
        }
        return "redirect:/gestionusuario/listar";
    }

    // 3. ELIMINAR: ¡SOLO ADMIN!
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario uLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (uLogueado == null || !uLogueado.getCargo().equals("ADMIN")) {
            return "redirect:/index?error=acceso_denegado";
        }

        try {
            usuarioRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el usuario.");
        }
        return "redirect:/gestionusuario/listar";
    }
}