package com.example.demo.application.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CotillaResource {
	@GetMapping("/servidor")
	public String getServer(@Value("${server.port}") String puerto) {
		return "Soy el " + puerto;		
	}

}
