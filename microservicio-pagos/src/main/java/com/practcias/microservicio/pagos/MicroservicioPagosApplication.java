package com.practcias.microservicio.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// para iniciar: mvnw.cmd spring-boot:run desde terminal
@SpringBootApplication
@ComponentScan
public class MicroservicioPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPagosApplication.class, args);
	}

}
