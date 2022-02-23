package com.practcias.microservicio.cuenta.entity;

import java.util.Date;
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
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = ("tbl_cuentas"))
@AllArgsConstructor @NoArgsConstructor @Builder
@Data
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String numeroCuenta;
	
	@NotEmpty(message="el nombre del campo no debe ser vacio")
	private String nombreCliente;

	private boolean bloqueado;
	
	@Positive(message="El limite debe ser mayor que cero")
	private Double limiteRetiro;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creado;
	
	private String tipoCuenta;
	
	private String estado;
	
	@NotNull(message="la tarjeta no debe estra vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Tarjeta tarjeta;
	


}
