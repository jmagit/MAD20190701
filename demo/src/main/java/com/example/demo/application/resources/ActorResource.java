package com.example.demo.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.demo.domains.entities.Actor;
import com.example.demo.domains.services.ActorService;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/actores")
@Api(value = "Mantenimiento de Actores")
public class ActorResource {
	@Autowired
	private ActorService srv;
	
	@GetMapping
	@ApiOperation(value = "Recupera la lista completa de actores")
	public List<Actor> getAll() {
		return srv.getAll();
	}
	
	@GetMapping(path = "/{id}")
	public Actor getOne(@PathVariable int id) throws NotFoundException {
		return srv.get(id);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void add(@RequestBody Actor item) throws InvalidDataException {
		srv.add(item);
	}
	
	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Modifica los datos del actor", notes = "Debe coincidir los identificadores")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actor encontrado"),
		@ApiResponse(code = 400, message = "Datos invalidos"),
		@ApiResponse(code = 404, message = "Actor no encontrado")
	})
	public Actor change(@ApiParam(value = "Identificador de actor") @PathVariable int id, 
			@ApiParam(value = "Datos modificados del actor") @RequestBody Actor item) throws Exception {
		// item.setActorId(id);
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		return srv.change(item);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws Exception {
		srv.delete(id);
	}
	
}
