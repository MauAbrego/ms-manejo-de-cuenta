package com.practcias.microservicio.pagos.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practcias.microservicio.pagos.model.Cuenta;


import lombok.Data;

@Data
@Entity
@Table(name = "tbl_recibos")
public class ReciboEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_de_recibo")
	private String noRecibo;

	private String descripcion;

	private Long cuentaId;

	@Column(name = "fecha_de_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Valid
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "recibo_id")
	private List<ServiciosEnReciboEntity> servicios;

	private String estado;
	
	
	@Transient
	private Cuenta cuenta;

	public ReciboEntity(){
	        servicios = new ArrayList<>();
	    }

	@PrePersist
	public void prePersist() {
		this.fechaCreacion = new Date();
	}

}
