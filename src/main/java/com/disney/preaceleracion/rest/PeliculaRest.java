package com.disney.preaceleracion.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	public ResponseEntity<List<PeliculaDto>> listarPeliculas(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "order", required = false) String order) {
		if (name == null && genre == null && order == null) {
			return ResponseEntity.ok(peliculaService.listarPeliculas());
		}else {
			return ResponseEntity.ok(peliculaService.listarPeliculasPorParams(name, genre, order));
		}
	}

	@PostMapping
	public ResponseEntity<?> crearPelicula(@RequestBody PeliculaDto pelicula) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaService.crearPelicula(pelicula));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPelicula(@RequestParam("id") int id,
			@RequestBody PeliculaDto pelicula) {
		if (peliculaService.actualizarPelicula(pelicula, id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Pelicula Actualizada\n" + pelicula);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID no encontrada o Error Interno");
	}

	@DeleteMapping
	public ResponseEntity<?> borrarPelicula(@RequestParam("id") int id) {
		if (peliculaService.borrarPelicula(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Pelicula Eliminada");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID no encontrada o Error Interno");
	}
}
