package com.disney.preaceleracion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.PeliculaDto;
import com.disney.preaceleracion.dto.PersonajeDto;
import com.disney.preaceleracion.model.Pelicula;
import com.disney.preaceleracion.model.Personaje;
import com.disney.preaceleracion.repository.PeliculaRepository;

@Service
public class PeliculaService {
	
	@Autowired
	PeliculaRepository repository;
	
	public boolean crearPelicula(PeliculaDto pelicula) {
		Pelicula movie = pelicula.buildEntidad();
		repository.saveAndFlush(movie);
		return false;
	}

	public Boolean actualizarPelicula(PeliculaDto peliculaDto, int id) {
		Pelicula movie = peliculaDto.buildEntidadbyId(id);
		repository.saveAndFlush(movie);
		return true;
	}
	public Boolean borrarPelicula(int id) {
		repository.deleteById(id);
		return true;
	}

}
