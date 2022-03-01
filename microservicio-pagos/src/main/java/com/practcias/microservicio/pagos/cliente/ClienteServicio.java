package com.practcias.microservicio.pagos.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practcias.microservicio.pagos.model.Servicio;


@FeignClient("servicio-servicios")
@RequestMapping("/servicios")
public interface ClienteServicio {
	
	@GetMapping(path = "/{id}")
	public Servicio obtenerUnServicio(@PathVariable Long id);

}
