package com.cibertec.PROY_QUIROPRACTICO.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.PROY_QUIROPRACTICO.model.*;
import com.cibertec.PROY_QUIROPRACTICO.repository.*;

import net.sf.jasperreports.engine.*;

@Controller
@RequestMapping("/gestionventa")
public class VentaController {

    @Autowired
    private HojaPagoRepository hojaPagoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private VentasRepository ventasRepository;

    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        model.addAttribute("listaPacientes", pacienteRepository.findAll());
        model.addAttribute("listaTratamientos", tratamientoRepository.findAll());
        return "NuevaVenta";
    }

    @PostMapping("/registrar")
    public String registrarVenta(@RequestParam("dniPaciente") String dniPaciente, 
                                @RequestParam("totalSesiones") int totalSesiones, 
                                @RequestParam("costoTotal") double costoTotal,
                                @RequestParam("idTratamiento") Integer idTratamiento,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuarioLogueado");
            if (u == null) return "redirect:/login";

            HojaPago hp = new HojaPago();
            
            // Lógica de conteo para ID autogenerado
            long conteo = hojaPagoRepository.count() + 1;
            String nuevoId = "HP" + String.format("%03d", conteo);
            
            hp.setNroHp(nuevoId); 
            hp.setDniPaciente(dniPaciente);
            hp.setIdUsuario(u.getIdUsuario());
            hp.setTotalSesiones(totalSesiones);
            hp.setCostoTotal(costoTotal);
            hp.setIdTratamiento(idTratamiento); 

            hojaPagoRepository.save(hp);
            
            redirectAttributes.addAttribute("exito", "1");
            return "redirect:/gestionreporte?vista=historial"; 
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/gestionventa/nueva?error=1";
        }
    }

  
    
  
    @GetMapping("/pdf/{nro_hp}")
    public void exportarPDF(@PathVariable("nro_hp") String nroHp, HttpServletResponse response) {
        try {
            // 1. OBTENCIÓN DE DATOS 
            Venta v = ventasRepository.findById(nroHp).orElseThrow();
            Paciente p = pacienteRepository.findById(v.getDniPaciente()).orElseThrow();
            
            String nombreTerapia = "Tratamiento Quiropráctico";
            if (v.getIdTratamiento() != null) {
                Tratamiento t = tratamientoRepository.findById(v.getIdTratamiento()).orElse(null);
                if (t != null) nombreTerapia = t.getNombre();
            }

            // 2. PARÁMETROS
            Map<String, Object> params = new HashMap<>();
            params.put("p_nro_boleta", v.getNroHp());
            params.put("p_paciente", p.getApellidos() + ", " + p.getNombres());
            params.put("p_dni", p.getDni());
            params.put("p_tratamiento", nombreTerapia);
            params.put("p_total", v.getCostoTotal());

            // 3. CARGA DE RECURSOS
            InputStream reportStream = new ClassPathResource("reportes/BoletaVenta.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Carga del Logo
            InputStream logoStream = new ClassPathResource("static/img/Logo-McLellan.png").getInputStream();
            params.put("p_logo", logoStream);

            // 4. GENERACIÓN DEL REPORTE
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            
            // 5. SALIDA PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Boleta_" + nroHp + ".pdf");
            
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}