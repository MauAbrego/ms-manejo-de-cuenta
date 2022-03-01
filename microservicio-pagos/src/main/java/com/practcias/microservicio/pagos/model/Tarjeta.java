package com.practcias.microservicio.pagos.model;
import lombok.Data;

@Data
public class Tarjeta {
	private long id;
	private String pan;
	private String fechaExperacion;
	private int cvv;
	private String propietrio;
	private int pin;
	private double saldo;
}
