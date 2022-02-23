package com.practcias.microservicio.cuenta.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.practcias.microservicio.cuenta.entity.Cuenta;
import com.practcias.microservicio.cuenta.entity.Tarjeta;

@Service
public interface CuentaService {
	public List<Cuenta> ListaTodasLasCuentas();
	public Cuenta obtenCuenta(long id);
	public Cuenta creaCuenta( Cuenta cuenta);
	public Cuenta actualizaCuenta(Cuenta cuenta);
	public Cuenta eliminaCuenta(Long id);
	public List<Cuenta> encuentraPorTarjeta(Tarjeta tarjeta);
	public Cuenta actualizaSaldo(Long id, Tarjeta tarjeta, Double quantity);
	
}
