package com.example.demo.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Direccion {
	String calle;
	String cp;
	String localidad;
	String provincia;
	String pais;
}
