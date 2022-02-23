package com.practcias.microservicio.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// para iniciar: mvnw.cmd spring-boot:run desde terminal
@SpringBootApplication
@ComponentScan
public class MicroservicioCuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCuentaApplication.class, args);
	}

}
