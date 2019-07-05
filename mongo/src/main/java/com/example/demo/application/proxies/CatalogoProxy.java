package com.example.demo.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.application.dto.FilmShortDTO;

@FeignClient(name = "catalogo", url = "http://localhost:8004")
public interface CatalogoProxy {
	@GetMapping("/peliculas?mode=short")
	List<FilmShortDTO> getPeliculas();
	@GetMapping("/peliculas/{id}?mode=short")
	FilmShortDTO getPelicula(@PathVariable String id);

}
