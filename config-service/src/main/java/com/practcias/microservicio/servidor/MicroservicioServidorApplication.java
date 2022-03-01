package com.practcias.microservicio.servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroservicioServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioServidorApplication.class, args);
	}

}
