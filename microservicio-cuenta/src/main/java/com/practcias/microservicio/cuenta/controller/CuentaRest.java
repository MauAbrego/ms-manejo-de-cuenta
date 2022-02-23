package com.practcias.microservicio.cuenta.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practcias.microservicio.cuenta.entity.Cuenta;
import com.practcias.microservicio.cuenta.entity.Tarjeta;
import com.practcias.microservicio.cuenta.service.CuentaService;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentaRest {
	
	@Autowired
	private CuentaService cuentaService;
	
	
	// -------------------Trae todas las cuentas--------------------------------------------
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> listaDeCuentas(
			@RequestParam(name = "pan", required = false) String pan) {
		List<Cuenta> cuenta = new ArrayList<>();
		if (null == pan) {
			cuenta = cuentaService.ListaTodasLasCuentas();
			if (cuenta.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

		} else {
			cuenta = cuentaService.encuentraPorTarjeta(Tarjeta.builder().pan(pan).build());
			if (cuenta.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(cuenta);

	}
	
	
	// -------------------Trae una sola cuenta------------------------------------------
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable("id") Long id) {
		Cuenta cuenta = cuentaService.obtenCuenta(id);
		if (null == cuenta) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cuenta);
	}
	
	
	// -------------------Crea una cuenta-------------------------------------------
	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestParam Cuenta cuenta, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoDelMensaje(result));
		}
		Cuenta cuentaDB = cuentaService.creaCuenta(cuenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaDB);		
	}
	
	
	// ------------------- Actualiza una cuenta ------------------------------------------------
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta){
		cuenta.setId(id);
		Cuenta cuentaDB = cuentaService.actualizaCuenta(cuenta);
		if(cuentaDB == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cuentaDB);
	}
	
	
    // -------------------Elimina Una Cuenta-----------------------------------------
	@DeleteMapping (value = "/{id}")
	public  ResponseEntity<Cuenta>eliminarCuenta(@PathVariable("id")  Long id) {
		Cuenta eliminaCuenta = cuentaService.eliminaCuenta(id);
		if(eliminaCuenta == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(eliminaCuenta);
	}

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cuenta> atualizarSaldo (@PathVariable("id") Long id, @RequestParam(name = "quantity", required = true) Tarjeta tarjeta, Double quantity){
		Cuenta producto = cuentaService.actualizaSaldo(id, tarjeta, quantity);
		if (producto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(producto);
	}
	private String formatoDelMensaje(BindingResult result) {
		List<Map<String, String>> errores = result.getFieldErrors().stream()
				.map(err ->{
					Map<String, String> error = new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());
	MensajesDeError mensajesDeError = MensajesDeError.builder()
			.codigo("01")
			.mensaje(errores).build();
	//transforma el objeto a un jeson string 
	 ObjectMapper mapper = new ObjectMapper();
     String jsonString="";
     try {
         jsonString = mapper.writeValueAsString(mensajesDeError);
     } catch (JsonProcessingException e) {
         e.printStackTrace();
     }
     return jsonString;
 }
		
	}

