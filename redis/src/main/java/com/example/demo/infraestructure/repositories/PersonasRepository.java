package com.example.demo.infraestructure.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.domains.entities.Persona;

public interface PersonasRepository extends PagingAndSortingRepository<Persona, String> {

}
