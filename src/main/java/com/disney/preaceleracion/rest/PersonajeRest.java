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


import com.disney.preaceleracion.dto.PersonajeDto;
import com.disney.preaceleracion.service.PersonajeService;

@RestController
@RequestMapping("characters")
public class PersonajeRest {
	
	@Autowired
	PersonajeService personajeService;
	
	@GetMapping
	public ResponseEntity<List<PersonajeDto>> listarPersonajes(
			@RequestParam(value = "name", required = false) String nombre,
			@RequestParam(value = "age", required = false) String edad,
			@RequestParam(value = "movies", required = false) String idmovie) {
		if (nombre == null && edad == null && idmovie == null) {
			return ResponseEntity.ok(personajeService.listarPersonajes());
		}else {
			return ResponseEntity.ok(personajeService.listarPersonajesPorParams(nombre, edad, idmovie));
		}
	}

	@PostMapping
	public ResponseEntity<?> crearPersonaje(@RequestBody PersonajeDto personaje){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeService.crearPersonje(personaje));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarPersonaje(@RequestParam("id") int id,
			@RequestBody PersonajeDto personaje){
		if(personajeService.actualizarPersonaje(personaje,id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Personaje Actualizado: \n" + personaje);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID no encontrada o Error Interno");
	}
	
	@DeleteMapping
	public ResponseEntity<?> borrarPersonaje(@RequestParam("id") int id){
		if(personajeService.borrarPersonaje(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Personaje Eliminado");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID no encontrada o Error Interno");
	}
}
