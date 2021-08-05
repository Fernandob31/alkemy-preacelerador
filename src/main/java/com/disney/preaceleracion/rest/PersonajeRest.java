package com.disney.preaceleracion.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	/*
	@GetMapping
	public ResponseEntity<List<PersonajeDto>> listarPersonajes(
			@RequestParam(value="name", required = false) Optional<String> nombre,
			@RequestParam(value="edad", required = false) Optional<String> edad,
			@RequestParam(value="idMovie", required = false) Optional<String> idMovie			
			){
		Map<String, String> queryParam = buildQuerymap(nombre,edad,idMovie);
		List<PersonajeDto> resp = personajeService.buscarPersonaje(queryParam);
		return ResponseEntity.ok().body(resp);
	}*/

	@PostMapping
	public ResponseEntity<PersonajeDto> crearPersonaje(@RequestBody PersonajeDto personaje){
		if(personajeService.crearPersonje(personaje)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
		}
		return ResponseEntity.status(404).body(personaje);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDto> actualizarPersonaje(@RequestParam("id") int id,
			@RequestBody PersonajeDto personaje){
		if(personajeService.actualizarPersonaje(personaje,id)) {
			return ResponseEntity.status(HttpStatus.OK).body(personaje);
		}
		return ResponseEntity.status(404).body(personaje);
	}
	
	@DeleteMapping
	public ResponseEntity<PersonajeDto> borrarPersonaje(@RequestParam("id") int id){
		if(personajeService.borrarPersonaje(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(404).build();
	}
}
