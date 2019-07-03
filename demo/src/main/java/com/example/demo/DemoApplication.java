package com.example.demo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.application.dto.ActoresShortDTO;
import com.example.demo.application.dto.PeliculasShortDTO;
import com.example.demo.domains.entities.Actor;
import com.example.demo.domains.services.ActorService;
import com.example.demo.infraestructure.repositories.ActorRepository;
import com.example.demo.infraestructure.repositories.FilmRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Autowired
	private ActorRepository dao;
	@Autowired
	private FilmRepository pelis;
	
	@Autowired
	private ActorService srv;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Hola mundo");	
//		Optional<Actor> rslt = dao.findById(999);
//		if(rslt.isPresent())
//			System.out.println(rslt);
//		else {
//			System.out.println("No encontrado");
//		}
//		dao.save(new Actor(0, "Pepito", "Grillo"));
//		dao.save(new Actor(201, "Pepitooooo", "Grillooooo"));
//		dao.deleteById(201);
//		dao.findAll().stream()
//			.forEach(item -> System.out.println(item));
//		dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("a")
//		.stream()
//		.forEach(item -> System.out.println(item));
		
//		try {
//			srv.delete(new Actor(202, "555", "Grillo"));
//			srv.getAll().forEach(item -> System.out.println(item));
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	
//		Actor rslt = srv.get(200);
//		rslt.getFilmActors()
//			.forEach(item -> System.out.println(item.getFilm().getTitle()));
//		pelis.findByFilmIdNotNull(PeliculasShortDTO.class)
//			.forEach(item -> System.out.println(item.getTitle()));
		dao.findByActorIdNotNull(ActoresShortDTO.class)
		.forEach(item -> System.out.println(item.getNombreCompleto()));
	}

}
