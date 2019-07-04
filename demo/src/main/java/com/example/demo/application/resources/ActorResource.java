package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.application.dto.ActorEditDTO;
import com.example.demo.application.dto.ActoresShortDTO;
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
	public List<ActorEditDTO> getAll() {
		return srv.getAll().stream()
				.map(item -> ActorEditDTO.from(item))
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/{id}")
	public ActorEditDTO getOne(@PathVariable int id) throws NotFoundException {
		return ActorEditDTO.from(srv.get(id));
	}
	
	@PostMapping
	public ResponseEntity<Object> add(@Valid @RequestBody ActorEditDTO dto) throws InvalidDataException {
		Actor item = ActorEditDTO.from(dto);
		if(srv.isNotValid(item))
			throw new InvalidDataException();
		Actor newItem = srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Modifica los datos del actor", notes = "Debe coincidir los identificadores")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Actor encontrado"),
		@ApiResponse(code = 400, message = "Datos invalidos"),
		@ApiResponse(code = 404, message = "Actor no encontrado")
	})
	public ActorEditDTO change(@ApiParam(value = "Identificador de actor") @PathVariable int id, 
			@ApiParam(value = "Datos modificados del actor") @RequestBody ActorEditDTO dto) throws Exception {
		// item.setActorId(id);
		Actor item = ActorEditDTO.from(dto);
		if(srv.isNotValid(item))
			throw new InvalidDataException();
		if(id != item.getActorId())
			throw new BadRequestException("No coinciden los identificadores");
		return ActorEditDTO.from(srv.change(item));
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws Exception {
		srv.delete(id);
	}
	
}
