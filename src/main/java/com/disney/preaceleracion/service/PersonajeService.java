package com.disney.preaceleracion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.PersonajeDto;
import com.disney.preaceleracion.model.Personaje;
import com.disney.preaceleracion.repository.PersonajeRepository;


@Service
public class PersonajeService {

	@Autowired
	PersonajeRepository repository;
	
	public List<PersonajeDto> buscarPersonaje(Map<String,String> query){
		List<PersonajeDto> resp = new ArrayList<>();
		return resp;
	}
	
	public Boolean crearPersonje(PersonajeDto personajeDto) {
		Personaje personaje = personajeDto.buildEntidad();
		repository.saveAndFlush(personaje);
		return true;
	}
	public Boolean actualizarPersonaje(PersonajeDto personajeDto, int id) {
		Personaje personaje = personajeDto.buildEntidadId(id);
		repository.saveAndFlush(personaje);
		return true;
	}
	public Boolean borrarPersonaje(int id) {
		repository.deleteById(id);
		return true;
	}
}
