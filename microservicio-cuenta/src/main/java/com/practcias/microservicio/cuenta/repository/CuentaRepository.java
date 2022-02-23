package com.practcias.microservicio.cuenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practcias.microservicio.cuenta.entity.Cuenta;
import com.practcias.microservicio.cuenta.entity.Tarjeta;

import java.util.List;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
public List <Cuenta> encuentraPorTarjeta(Tarjeta tarjeta);
}
