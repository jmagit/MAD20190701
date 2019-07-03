package com.example.demo.application.dto;

import org.springframework.beans.factory.annotation.Value;

public interface ActoresShortDTO {
	int getActorId();
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getNombreCompleto();

}
