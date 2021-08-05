package com.disney.preaceleracion.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disney.preaceleracion.dto.PeliculaDto;
import com.disney.preaceleracion.service.PeliculaService;

@RestController
@RequestMapping("movies")
public class PeliculaRest {
	
	@Autowired
	PeliculaService peliculaService;
	

	@PostMapping
	public ResponseEntity<PeliculaDto> crearPelicula(@RequestBody PeliculaDto pelicula){
		if(peliculaService.crearPelicula(pelicula)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
		}
		return ResponseEntity.status(404).body(pelicula);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PeliculaDto> actualizarPelicula(@RequestParam("id") int id,
			@RequestBody PeliculaDto pelicula){
		if(peliculaService.actualizarPelicula(pelicula,id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping
	public ResponseEntity<PeliculaDto> borrarPelicula(@RequestParam("id") int id){
		if(peliculaService.borrarPelicula(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(404).build();
	}
}
