package com.cibertec.PROY_QUIROPRACTICO.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.PROY_QUIROPRACTICO.model.Usuario;
import com.cibertec.PROY_QUIROPRACTICO.repository.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // Mostrar login: 
    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario != null) {
            return redireccionarPorRol(usuario);
        }
        return "Login"; 
    }

    // Procesar el ingreso con lógica de roles
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, 
                        @RequestParam("clave") String clave, 
                        HttpSession session, 
                        Model model) {
        
        Usuario usuario = usuarioRepository.findByEmailAndClave(email, clave);

        if (usuario != null) {
            // Guardamos el objeto completo para los th:if del Navbar
            session.setAttribute("usuarioLogueado", usuario);
            
            // Redirección inteligente
            return redireccionarPorRol(usuario);
        } else {
            model.addAttribute("mensajeError", "Usuario o contraseña incorrectos");
            return "Login";
        }
    }

    // Método de soporte para centralizar la lógica de rutas
    private String redireccionarPorRol(Usuario u) {
        if ("MEDICO".equals(u.getCargo())) {
            return "redirect:/gestionatencion/listar"; // El médico va directo a atender
        }
        return "redirect:/index"; // Admin y Asistente van al Dashboard
    }

    // Logout: Limpieza total de sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/login?logout"; 
    }
}