package com.practcias.microservicio.cuenta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_cuentas")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Tarjeta {
	@Id
	@Column(name = "categoria_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String pan;
	private String fechaExperacion;
	private int cvv;
	
	@Column(name = "nombreCliente")
	private String propietrio;
	private int pin;
	private double saldo;

}
