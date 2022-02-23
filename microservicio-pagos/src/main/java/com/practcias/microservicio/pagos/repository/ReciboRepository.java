package com.practcias.microservicio.pagos.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.practcias.microservicio.pagos.entity.ReciboEntity;

public interface ReciboRepository extends CrudRepository<ReciboEntity, Long>{
	 public ArrayList<ReciboEntity> findByCustomerId(Long cuentaId );
	    public ReciboEntity findByNumberInvoice(String noRecibo);

}
