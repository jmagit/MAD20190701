package com.example.demo.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {
	<T> List<T> findByFilmIdNotNull(Class<T> type);
}
