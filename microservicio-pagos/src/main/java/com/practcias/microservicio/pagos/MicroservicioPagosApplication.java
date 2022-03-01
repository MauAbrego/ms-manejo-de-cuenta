package com.practcias.microservicio.pagos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

// para iniciar: mvnw.cmd spring-boot:run desde terminal
@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class MicroservicioPagosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPagosApplication.class, args);
	}

}
