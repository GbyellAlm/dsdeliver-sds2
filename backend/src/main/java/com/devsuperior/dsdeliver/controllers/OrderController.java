package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

// CONTROLADOR REST
@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	// Dependencia pro Service pq o Controller depende do Service
	@Autowired
	private OrderService service;
	
	// Endpoint pro metodo get
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> list = service.findAll();
		// O "ok" eh o status 200 do http
		return ResponseEntity.ok().body(list);
	}
	
}
