package com.practcias.microservicio.servicio.repository;


import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practcias.microservicio.servicio.entity.Categoria;
import com.practcias.microservicio.servicio.entity.Servicios;

@Repository
public interface ServiciosRepository extends CrudRepository<Servicios, Long> {

	ArrayList<Servicios> encuentraPorCategoria(Categoria categoria);
}
