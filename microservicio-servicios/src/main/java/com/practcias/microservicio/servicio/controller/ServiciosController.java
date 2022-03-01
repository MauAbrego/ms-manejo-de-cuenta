package com.practcias.microservicio.servicio.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.practcias.microservicio.servicio.entity.Servicios;
import com.practcias.microservicio.servicio.service.ServiciosService;

@RestController
@RequestMapping("/servicios")
public class ServiciosController {
	@Autowired
	ServiciosService serviciosService;
	
    // -------------------Trae todos los servicios--------------------------------------------
	@GetMapping
	public ArrayList<Servicios> obtenerTodosLosServicios() {
		return serviciosService.listaTodosLosServicios();
	}
    // -------------------Trae un servicios--------------------------------------------
	@GetMapping(path = "/{id}")
	public Servicios obtenerUnServicio(@PathVariable Long id) {
		return this.serviciosService.obtenUnServicio(id);
	}
    // -------------------Crea un servicios--------------------------------------------
	@PostMapping
	public Servicios crearUnservicio(@RequestBody Servicios servicios) {
		return this.serviciosService.creaUnServicio(servicios);
	}
	
    // -------------------Modifica los servicios--------------------------------------------
	@PutMapping("/{id}")
	public ResponseEntity<Servicios> actualizaUnServicio(@PathVariable("id") Long id,
			@RequestBody Servicios servicios) {
		servicios.setId(id);
		Servicios serviciosDB = serviciosService.actualizaUnServicio(servicios);
		if (serviciosDB == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(serviciosDB);
	}
	
    // -------------------Elimina los servicios--------------------------------------------
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Servicios> deleteProduct(@PathVariable("id") Long id) {
		Servicios eliminaServicio = serviciosService.eliminaUnServicio(id);
		if (eliminaServicio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(eliminaServicio);
	}

}
