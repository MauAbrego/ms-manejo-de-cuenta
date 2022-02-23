package com.practcias.microservicio.cuenta.service;
//arreglar la fecha... el erro fue importar la paqueteria sql en lugar de java.util al momento de declarar la variable

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practcias.microservicio.cuenta.entity.Cuenta;
import com.practcias.microservicio.cuenta.entity.Tarjeta;
import com.practcias.microservicio.cuenta.repository.CuentaRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CuentaServiceImp implements CuentaService{

	@Autowired
	private final  CuentaRepository cuentaRepository;
	
	
	@Override
	public List<Cuenta> ListaTodasLasCuentas() {		
		return cuentaRepository.findAll();
	}

	@Override
	public Cuenta obtenCuenta(long id) {
		return cuentaRepository.findById(id).orElse(null);
	}

	@Override
	public Cuenta creaCuenta(Cuenta cuenta) {
		cuenta.setEstado("creado");
		cuenta.setCreado(new Date());
		cuenta.setBloqueado(false);
		cuenta.setLimiteRetiro(5000.00);
		cuenta.setTipoCuenta("N2");
		return  cuentaRepository.save(cuenta);
	}

	@Override
	public Cuenta actualizaCuenta(Cuenta cuenta) {
		Cuenta cuentaDB = obtenCuenta(cuenta.getId());
		if (null == cuentaDB) {
			return null;
		}
		
		cuentaDB.setNombreCliente(cuenta.getNombreCliente());
		cuentaDB.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaDB.setTarjeta(cuenta.getTarjeta());
		cuentaDB.setTipoCuenta(cuenta.getTipoCuenta());
		cuentaDB.setLimiteRetiro(cuenta.getLimiteRetiro());
		return cuentaRepository.save(cuentaDB);
	}

	@Override
	public Cuenta eliminaCuenta(Long id) {
		Cuenta cuentaDB = obtenCuenta(id);
		if (null == cuentaDB) {
			return null;
		}
		cuentaDB.setEstado("borrado");
		return cuentaRepository.save(cuentaDB);
	}

	@Override
	public List<Cuenta> encuentraPorTarjeta(Tarjeta tarjeta) {
		return cuentaRepository.encuentraPorTarjeta(tarjeta);
	}

	@Override
	public Cuenta actualizaSaldo(Long id, Tarjeta tarjeta, Double quantity) {
		Cuenta cuentaDB = obtenCuenta(id);
		if (null == cuentaDB) {
			return null;
		}
		Double nuevoSaldo = tarjeta.getSaldo() + quantity;
		tarjeta.setSaldo(nuevoSaldo);
		return cuentaRepository.save(cuentaDB);
	}
	

}
