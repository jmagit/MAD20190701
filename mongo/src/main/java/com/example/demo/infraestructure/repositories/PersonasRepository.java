package com.example.demo.infraestructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domains.entities.Persona;

@RepositoryRestResource(path="personas", itemResourceRel="persona", collectionResourceRel="personas")
public interface PersonasRepository extends MongoRepository<Persona, String> {

}
