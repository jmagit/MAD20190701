package com.example.demo.application.dto;

import com.example.demo.domains.entities.Pelicula;

import lombok.Value;

@Value
public class FilmShortDTO {
	private int filmId;
	private String title;
	
	public Pelicula toEntity() {
		return new Pelicula(Integer.toString(getFilmId()), getTitle());
	}
}
