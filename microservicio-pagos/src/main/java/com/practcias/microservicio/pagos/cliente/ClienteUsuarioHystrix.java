package com.practcias.microservicio.pagos.cliente;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.practcias.microservicio.pagos.model.Cuenta;

@Component
public class ClienteUsuarioHystrix implements ClienteUsuario {
	
	
	@Override
	public ResponseEntity<Cuenta> obtenerCuenta (Long id){
		
		Cuenta cuenta = Cuenta.builder()
				.nombreCliente("none")
				.numeroCuenta("none")
				.bloqueado(false)
				.limiteRetiro(0.0)
				.tipoCuenta("none")
				.estado("none").build();
		
		return ResponseEntity.ok(cuenta);
	}

}
