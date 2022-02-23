package com.practcias.microservicio.servicio.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practcias.microservicio.servicio.entity.Categoria;
import com.practcias.microservicio.servicio.entity.Servicios;
import com.practcias.microservicio.servicio.repository.ServiciosRepository;

@Service
public class ServiciosService {

	@Autowired
	ServiciosRepository serviciosRepository;

	public ArrayList<Servicios> listaTodosLosServicios() {
		return (ArrayList<Servicios>) serviciosRepository.findAll();
	}

	public Servicios obtenUnServicio(Long id) {
		return serviciosRepository.findById(id).orElse(null);
	}

	public Servicios creaUnServicio(Servicios servicios) {
		servicios.setEstado("CREADO");
		servicios.setFechaDeCreacion(new Date());

		return serviciosRepository.save(servicios);
	}

	public Servicios actualizaUnServicio(Servicios servicios) {
		Servicios servicioDB = obtenUnServicio(servicios.getId());
		if (null == servicioDB) {
			return null;
		}
		servicioDB.setNombre(servicios.getNombre());
		servicioDB.setDescripcion(servicios.getDescripcion());
		servicioDB.setCategoria(servicios.getCategoria());
		servicioDB.setPrecio(servicios.getPrecio());
		return serviciosRepository.save(servicioDB);
	}

	public Servicios eliminaUnServicio(Long id) {
		Servicios servicioDB = obtenUnServicio(id);
		if (null == servicioDB) {
			return null;
		}
		servicioDB.setEstado("Eliminado");
		return serviciosRepository.save(servicioDB);
	}

	public ArrayList<Servicios> encuentraPorCategoria(Categoria categoria) {
		return serviciosRepository.encuentraPorCategoria(categoria);
	}

}
