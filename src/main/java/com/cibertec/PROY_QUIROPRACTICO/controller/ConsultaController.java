package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.Consulta;
import com.cibertec.PROY_QUIROPRACTICO.repository.ConsultaRepository;

@Controller
@RequestMapping("/gestionconsulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    // doGet -> accion=listar
    @GetMapping("/listar")
    public String listarConsultasPendientes(Model model) {
        List<Consulta> lista = consultaRepository.findByEstado("PENDIENTE"); // O el método que definas
        model.addAttribute("listaConsultas", lista);
        return "AtencionMedica"; // AtencionMedica.jsp
    }

    // doGet -> accion=nueva
    @GetMapping("/nueva")
    public String nuevaConsulta() {
        return "NuevaConsulta"; // NuevaConsulta.jsp
    }

    // doPost -> accion=registrar
    @PostMapping("/registrar")
    public String registrarConsulta(@RequestParam("dni") String dni,
                                   @RequestParam("fecha") String fechaStr,
                                   @RequestParam("hora") String horaStr,
                                   @RequestParam("motivo") String motivo,
                                   RedirectAttributes redirectAttributes) {
        try {
            Consulta c = new Consulta();
            c.setDniPaciente(dni);
            c.setFecha(Date.valueOf(fechaStr));
            c.setHora(Time.valueOf(horaStr + ":00"));
            c.setMotivo(motivo);
            c.setEstado("PENDIENTE"); // Valor por defecto

            consultaRepository.save(c);
            redirectAttributes.addAttribute("exito", "1");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("error", "2");
        }
        return "redirect:/gestionconsulta/nueva";
    }

    // doPost -> accion=finalizar
    @PostMapping("/finalizar")
    public String finalizarConsulta(@RequestParam("id_consulta") int idConsulta,
                                    @RequestParam("diagnostico") String diagnostico,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Buscamos la consulta, actualizamos y guardamos
            Consulta c = consultaRepository.findById(idConsulta).orElse(null);
            if (c != null) {
                c.setDiagnostico(diagnostico);
                c.setEstado("FINALIZADO");
                consultaRepository.save(c);
                redirectAttributes.addAttribute("mensaje", "atendido");
            } else {
                redirectAttributes.addAttribute("error", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("error", "2");
        }
        return "redirect:/gestionconsulta/listar";
    }
}