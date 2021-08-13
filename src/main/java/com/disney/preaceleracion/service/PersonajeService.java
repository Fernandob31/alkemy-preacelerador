package com.disney.preaceleracion.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.PersonajeDto;
import com.disney.preaceleracion.model.Personaje;
import com.disney.preaceleracion.repository.PersonajeRepository;


@Service
public class PersonajeService {

	@Autowired
	PersonajeRepository repository;
	
	@PersistenceContext
	EntityManager em;
	
	public List<PersonajeDto> listarPersonajes() {
		List<Personaje> personajeListEntity = new ArrayList<>();
		repository.findAll().forEach(personajeListEntity::add);
		List<PersonajeDto> personajesListDto = new ArrayList<>();
		personajeListEntity.stream().map(c -> personajesListDto.add(PersonajeDto.getEntity(c))).toArray();
		return personajesListDto;
	}
	
	public String crearPersonje(PersonajeDto personajeDto) {
		if(verificarDatosPersonaje(personajeDto)) {
			Personaje personaje = personajeDto.buildEntidad();
			repository.saveAndFlush(personaje);
			return "Pelicula Agregada con exito";
		}else {
			return "Error: Datos Incompletos";
		}
	}

	public Boolean actualizarPersonaje(PersonajeDto personajeDto, int id) {
		if (repository.getById(id) != null) {
			Personaje personaje = personajeDto.buildEntidadId(id);
			repository.saveAndFlush(personaje);
			return true;
		}else {
			return false;
		}
	}
	public Boolean borrarPersonaje(int id) {
		if (repository.getById(id) != null) {
			repository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	public List<PersonajeDto> listarPersonajesPorParams(String nombre, String edad, String idmovie) {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Personaje> cq = qb.createQuery(Personaje.class);
	    Root<Personaje> customer = cq.from(Personaje.class);

	    //Constructing list of parameters
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    //Adding predicates in case of parameter not being null
	    if (nombre != null) {
	        predicates.add(
	                qb.equal(customer.get("nombre"), nombre));
	    }
	    if (edad != null) {
	        predicates.add(
	                qb.equal(customer.get("edad"), edad));
	    }
	    if (idmovie != null) {
	        predicates.add(
	                qb.equal(customer.get("pelicula_id"), idmovie));
	    }
	    cq.select(customer)
	            .where(predicates.toArray(new Predicate[]{}));
	    
	    List<Personaje> personajeListEntity = new ArrayList<>();
	    personajeListEntity = em.createQuery(cq).getResultList();
		List<PersonajeDto> personajesListDto = new ArrayList<>();
		personajeListEntity.stream().map(c -> personajesListDto.add(PersonajeDto.getEntity(c))).toArray();
		return personajesListDto;
	}
	

	private boolean verificarDatosPersonaje(PersonajeDto personajeDto) {
		if (personajeDto.getImagen() != null && personajeDto.getNombre() != null){
			return true;			
		}else {
			return false;
		}
	}
	
}
