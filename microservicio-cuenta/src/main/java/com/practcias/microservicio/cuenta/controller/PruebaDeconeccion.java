package com.practcias.microservicio.cuenta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaDeconeccion {
	@RequestMapping("/")
	public String hola () {
		return "hola mundo";
	}

}
