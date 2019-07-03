package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domains.entities.Direccion;
import com.example.demo.domains.entities.Persona;
import com.example.demo.infraestructure.repositories.PersonasRepository;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}
	@Autowired
	private PersonasRepository dao;
	@Autowired
	private MeGustaService srv;
	
	@Override
	public void run(String... args) throws Exception {
//		List<String> tel = new ArrayList<String>();
//		tel.add("1234");
//		tel.add("5678");
//		Persona persona = new Persona("1", "Pepito", "Grillo", 55, tel, 
//				new Direccion("Pez Volador 15", "28080", "Madrid", "Madrid", "España"));
//		dao.save(persona);
		dao.findAll().forEach(item -> System.out.println(item));
		System.out.println(srv.add());
		System.out.println(srv.add());
		System.out.println(srv.add());
//		System.out.println(srv.add(10));
	}
		

}
