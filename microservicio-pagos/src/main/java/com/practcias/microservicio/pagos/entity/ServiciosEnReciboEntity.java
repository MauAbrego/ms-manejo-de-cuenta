package com.practcias.microservicio.pagos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import com.practcias.microservicio.pagos.model.Servicio;

import lombok.Data;

@Entity
@Data
@Table(name = "servicios_en_recibos")
public class ServiciosEnReciboEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive(message = "La cantidad debe ser mayor que cero")
	private Double cantidad;
	private Double precio;
	private Long servicioId;

	@Transient
	private Double subTotal;
	
	@Transient
	private Servicio servicio;

	public Double obtenerSubtotal() {
		if (this.precio > 0 && this.cantidad > 0) {
			return this.cantidad * this.precio;
		} else {
			return (double) 0;
		}
	}

	public ServiciosEnReciboEntity() {
		this.cantidad = (double) 0;
		this.precio = (double) 0;

	}
}