package com.example.demo.application.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.amqp.EventMessage;
import com.example.demo.application.dto.FilmShortDTO;
import com.example.demo.application.proxies.CatalogoProxy;
import com.example.demo.domains.entities.Pelicula;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.infraestructure.repositories.PeliculasRepository;

@RestController
@RequestMapping("/peliculas")
public class PeliculasResources {
	private static final Logger log = LoggerFactory.getLogger(PeliculasResources.class);
	
	@Autowired
	private PeliculasRepository dao;

	@GetMapping
	public List<Pelicula> get() {
		return dao.findAll();
	}

	@GetMapping("/{id}")
	public Pelicula get(@PathVariable String id) throws NotFoundException {
		Optional<Pelicula> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get();
	}

	@Autowired
	private CatalogoProxy proxy;
	@RabbitListener(queues = "${my.config.mq.catalogo}")
	public void recive(EventMessage inMsg) {
		log.warn("RECIBIDO: {}", inMsg);
		if (!"PELICULA".equals(inMsg.getEntity()))
			return;
		if (dao.count() == 0) {
			List<FilmShortDTO> rslt = proxy.getPeliculas();
			dao.insert(rslt.stream().map(FilmShortDTO::toEntity).collect(Collectors.toList()));
		} else {
			switch (inMsg.getAction()) {
			case EventMessage.ACTION_ADD:
				dao.insert(proxy.getPelicula(inMsg.getIdentity()).toEntity());
				break;
			case EventMessage.ACTION_MODIFY:
				dao.save(proxy.getPelicula(inMsg.getIdentity()).toEntity());
				break;
			case EventMessage.ACTION_REMOVE:
				dao.deleteById(inMsg.getIdentity());
				break;
			}
		}
	}

}
