package com.practcias.microservicio.pagos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practcias.microservicio.pagos.entity.ReciboEntity;
import com.practcias.microservicio.pagos.repository.ReciboRepository;
import com.practcias.microservicio.pagos.repository.ServiciosEnReciboRepository;


@Service
public class ReciboServiceImpl implements ReciboService {

	@Autowired
	ReciboRepository reciboRepository;

	@Autowired
	ServiciosEnReciboRepository serviciosEnReciboRepository;

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
		return reciboRepository.findById(id).orElse(null);
	}

}
