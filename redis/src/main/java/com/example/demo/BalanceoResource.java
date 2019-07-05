package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BalanceoResource {
	@Autowired
	private RestTemplate rest;
		
	@GetMapping(path = "/peticion")
	public String get() {
		return rest.getForObject( "http://DEMO-SERVICE/servidor", String.class);
	}

}
