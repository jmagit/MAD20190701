package com.example.demo.domains.entities.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domains.entities.Persona;

@Projection(name = "personasAcortado", types = { Persona.class }) 
public interface PersonaShortDTO {
	String getIdPersona();
	@Value("#{target.nombre + ' ' + target.apellidos}")
	String getNombreCompleto();
}
