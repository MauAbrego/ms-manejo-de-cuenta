package com.practcias.microservicio.pagos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practcias.microservicio.pagos.cliente.ClienteServicio;
import com.practcias.microservicio.pagos.cliente.ClienteUsuario;
import com.practcias.microservicio.pagos.entity.ReciboEntity;
import com.practcias.microservicio.pagos.entity.ServiciosEnReciboEntity;
import com.practcias.microservicio.pagos.model.Cuenta;
import com.practcias.microservicio.pagos.model.Servicio;
import com.practcias.microservicio.pagos.repository.ReciboRepository;
import com.practcias.microservicio.pagos.repository.ServiciosEnReciboRepository;


@Service
public class ReciboServiceImpl implements ReciboService {

	@Autowired
	ReciboRepository reciboRepository;

	@Autowired
	ServiciosEnReciboRepository serviciosEnReciboRepository;
	
	@Autowired
	ClienteServicio clienteServicio;
	
	@Autowired
	ClienteUsuario clienteUsuario;
	
	@Override
	public ArrayList<ReciboEntity> encuentraTodosLosRecibos() {
		return (ArrayList<ReciboEntity>) reciboRepository.findAll();
	}

	@Override
	public ReciboEntity creaRecibo(ReciboEntity recibo) {
		ReciboEntity reciboDB = reciboRepository.findByNumberInvoice(recibo.getNoRecibo());
		if (reciboDB != null) {
			return reciboDB;
		}
		recibo.setEstado("Creado");
		
		return reciboRepository.save(recibo);
	}

	@Override
	public ReciboEntity actualizaRecibo(ReciboEntity recibo) {
		ReciboEntity reciboDB = obtenRecibo(recibo.getId());
		if (reciboDB == null) {
			return null;
		}
		reciboDB.setCuentaId(recibo.getCuentaId());
		reciboDB.setDescripcion(recibo.getDescripcion());
		reciboDB.setNoRecibo(recibo.getNoRecibo());
		reciboDB.getServicios().clear();
		reciboDB.setServicios(recibo.getServicios());
		return reciboRepository.save(reciboDB);
	}

	@Override
	public ReciboEntity eliminaRecibo(ReciboEntity recibo) {
		ReciboEntity reciboDB = obtenRecibo(recibo.getId());
		if (reciboDB == null) {
			return null;
		}
		reciboDB.setEstado("Eliminado");
		return reciboRepository.save(reciboDB);
	}

	@Override
	public ReciboEntity obtenRecibo(Long id) {
		ReciboEntity recibo = reciboRepository.findById(id).orElse(null);
		
		if(recibo!=null) {
			 Cuenta cuenta= clienteUsuario.obtenerCuenta(recibo.getCuentaId()).getBody();
	            recibo.setCuenta(cuenta);
	            List<ServiciosEnReciboEntity> listaDeServicios = recibo.getServicios().stream()
	            		.map(serviciosEnReciboEntity -> {
	                Servicio servicio = clienteServicio.obtenerUnServicio(serviciosEnReciboEntity.getServicioId());
	                serviciosEnReciboEntity.setServicio(servicio);
	                return serviciosEnReciboEntity;
	            }).collect(Collectors.toList());
	            recibo.setServicios(listaDeServicios);
	        }
	 return recibo ;
	}

}
