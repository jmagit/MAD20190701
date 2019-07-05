package com.example.demo.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domains.entities.Pelicula;

@RepositoryRestResource(exported = false)
public interface PeliculasRepository extends MongoRepository<Pelicula, String> {

}
