package com.example.demo.domains.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document("personas")
public class Persona {
	@Id
	String idPersona;
	String nombre;
	String apellidos;
	Integer edad;
	List<String> telefono;
	Direccion direccion;
}
