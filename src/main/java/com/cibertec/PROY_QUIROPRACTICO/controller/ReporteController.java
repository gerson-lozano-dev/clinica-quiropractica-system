package com.cibertec.PROY_QUIROPRACTICO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cibertec.PROY_QUIROPRACTICO.repository.HojaPagoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gestionreporte") 
public class ReporteController {

    @Autowired
    private HojaPagoRepository hojaPagoRepo;

    @GetMapping 
    public String mostrarReportes(Model model, HttpSession session) {
       
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        
      
        model.addAttribute("listaVentas", hojaPagoRepo.obtenerReporteVentas());

     
        
        model.addAttribute("listaEstadistica", hojaPagoRepo.obtenerPacientesPorDistrito());

      
        model.addAttribute("reporteCitas", hojaPagoRepo.obtenerCitasMensuales());

        return "Reportes"; 
    }
}