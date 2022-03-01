package com.practcias.microservicio.servicio.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_servicios")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Servicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre no debe estar vacío")
	private String nombre;
	private String descripcion;
	private boolean disponible;
	private Double precio;
	private String estado;

	@Column(name = "creado_en")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDeCreacion;

	@NotNull(message = "La categoria no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categoria categoria
	;

}
