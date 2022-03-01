package com.practcias.microservicio.pagos.model;

import java.util.Date;
import lombok.Data;


@Data
public class Servicio {
	
	private Long id;

	private String nombre;
	
	private String descripcion;
	
	private boolean disponible;
	
	private Double precio;
	
	private String estado;

	private Date fechaDeCreacion;

	private Categoria categoria;

}
