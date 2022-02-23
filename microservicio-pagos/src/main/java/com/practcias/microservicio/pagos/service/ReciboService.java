package com.practcias.microservicio.pagos.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.practcias.microservicio.pagos.entity.ReciboEntity;

@Service
public interface ReciboService {
	
	    public ArrayList<ReciboEntity> encuentraTodosLosRecibos();
	    public ReciboEntity creaRecibo(ReciboEntity recibo);
	    public ReciboEntity actualizaRecibo(ReciboEntity recibo);
	    public ReciboEntity eliminaRecibo(ReciboEntity recibo);
	    public ReciboEntity obtenRecibo(Long id);

}
