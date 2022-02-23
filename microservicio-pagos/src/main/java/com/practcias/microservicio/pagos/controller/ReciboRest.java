package com.practcias.microservicio.pagos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practcias.microservicio.pagos.entity.ReciboEntity;
import com.practcias.microservicio.pagos.service.ReciboService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/recibos")
public class ReciboRest {
	
	@Autowired
    ReciboService reciboService;

    // -------------------Trae todos los recibos--------------------------------------------
    @GetMapping
    public ResponseEntity<List<ReciboEntity>> listaTodosLosRecibos() {
        List<ReciboEntity> recibo = reciboService.encuentraTodosLosRecibos();
        if (recibo.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(recibo);
    }

    // -------------------Trae un solo recibo------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReciboEntity> obtenRecibo(@PathVariable("id") long id) {
        log.info("Trayendo recibo con id {}", id);
        ReciboEntity recibo  = reciboService.obtenRecibo(id);
        if (null == recibo) {
            log.error("Recibo con id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(recibo);
    }

    // -------------------Crea un Recibo-------------------------------------------
    @PostMapping
    public ResponseEntity<ReciboEntity> creaRecibo(@Valid @RequestBody ReciboEntity recibo, BindingResult result) {
        log.info("Creating Invoice : {}", recibo);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoDeMensaje(result));
        }
        ReciboEntity reciboDb = reciboService.creaRecibo (recibo);

        return  ResponseEntity.status( HttpStatus.CREATED).body(reciboDb);
    }

    // ------------------- Actualiza un recibo ------------------------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReciboEntity> actualizaRecibo(@PathVariable("id") long id, @RequestBody ReciboEntity recibo) {
        log.info("Updating Invoice with id {}", id);

        recibo.setId(id);
        ReciboEntity reciboActual=reciboService.actualizaRecibo(recibo);

        if (reciboActual == null) {
            log.error("No se puede actualizar. Recibo con id {} no encontrado.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(reciboActual);
    }

    // -------------------Elimina recibo-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReciboEntity> deleteInvoice(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Invoice with id {}", id);

        ReciboEntity recibo = reciboService.obtenRecibo(id);
        if (recibo == null) {
            log.error("Unable to delete. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        recibo = reciboService.eliminaRecibo(recibo);
        return ResponseEntity.ok(recibo);
    }

    
    // -------------------Mensajes de error-----------------------------------------

    private String formatoDeMensaje (BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        MensajesDeError mensajeDeError = MensajesDeError.builder()
                .codigo("01")
                .mensaje(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(mensajeDeError);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
