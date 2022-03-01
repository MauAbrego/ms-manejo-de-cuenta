package com.practcias.microservicio.registrosservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroservicioRegistrosServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRegistrosServidorApplication.class, args);
	}

}
