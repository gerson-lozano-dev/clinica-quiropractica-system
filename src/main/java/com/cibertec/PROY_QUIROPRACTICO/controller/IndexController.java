package com.cibertec.PROY_QUIROPRACTICO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String mostrarDashboard(HttpSession session) {
	    
	    if (session.getAttribute("usuarioLogueado") == null) {
	        return "redirect:/login";
	    }
	    return "index"; // 
	}}