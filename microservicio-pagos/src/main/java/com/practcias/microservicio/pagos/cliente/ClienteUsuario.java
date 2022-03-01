package com.practcias.microservicio.pagos.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practcias.microservicio.pagos.model.Cuenta;

@FeignClient(name = "servicio-clienta", fallback = ClienteUsuarioHystrix.class)
@RequestMapping(value = "/cuentas")
public interface ClienteUsuario {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable("id") Long id);
}
