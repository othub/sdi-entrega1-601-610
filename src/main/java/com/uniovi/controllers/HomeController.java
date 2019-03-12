package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * La pagina inicial cuando accedemos a localhost:8090
 * 
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "index"; // devuelve el index.html
	}

}