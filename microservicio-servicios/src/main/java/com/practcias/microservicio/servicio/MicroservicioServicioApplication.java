package com.practcias.microservicio.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// para iniciar: mvnw.cmd spring-boot:run desde terminal
@SpringBootApplication
@ComponentScan
public class MicroservicioServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioServicioApplication.class, args);
	}

}
