package com.practcias.microservicio.pagos.controller;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class MensajesDeError {
	private String codigo ;
    private List<Map<String, String >> mensaje ;
}
