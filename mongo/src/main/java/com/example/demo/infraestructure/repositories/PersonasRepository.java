package com.example.demo.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domains.entities.Persona;

public interface PersonasRepository extends MongoRepository<Persona, String> {

}
