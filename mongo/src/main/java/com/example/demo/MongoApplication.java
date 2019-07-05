package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.demo.domains.entities.Direccion;
import com.example.demo.domains.entities.Persona;
import com.example.demo.infraestructure.repositories.PersonasRepository;

@EnableFeignClients("com.example.demo.application.proxies")
@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Autowired
	private PersonasRepository dao;
		
	@Override
	public void run(String... args) throws Exception {
//		List<String> tel = new ArrayList<String>();
//		tel.add("1234");
//		tel.add("5678");
//		Persona persona = new Persona(null, "Pepito", "Grillo", 55, tel, 
//				new Direccion("Pez Volador 15", "28080", "Madrid", "Madrid", "España"));
//		dao.insert(persona);
//		dao.findAll().forEach(item -> System.out.println(item));
	}

}
