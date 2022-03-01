package com.practcias.microservicio.pagos.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Cuenta {
	
	private long id;
	
	private String numeroCuenta;
	
	private String nombreCliente;

	private boolean bloqueado;
	
	private Double limiteRetiro;
	
	
	private Date creado;
	
	private String tipoCuenta;
	
	private String estado;
	
	
	private Tarjeta tarjeta;
	
}
