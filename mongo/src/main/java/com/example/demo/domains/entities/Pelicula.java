package com.example.demo.domains.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document("peliculas")
public class Pelicula {
	@Id
	@JsonProperty("filmId")
	private String idPelicula;
	@JsonProperty("title")
	private String titulo;
}
