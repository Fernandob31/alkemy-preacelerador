package com.disney.preaceleracion.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.PeliculaDto;
import com.disney.preaceleracion.model.Pelicula;
import com.disney.preaceleracion.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaService {

	@Autowired
	PeliculaRepository repository;

	@Autowired
	GeneroService generoService;
	
	@PersistenceContext
	EntityManager em;
	
	/*
	 * Listar Peliculas: Devuelve los datos de las peliculas que se encuentran en la base de datos
	 * 
	 */
	public List<PeliculaDto> listarPeliculas() {
		List<Pelicula> peliculasList = new ArrayList<>();
		repository.findAll().forEach(peliculasList::add);
		List<PeliculaDto> peliculasListDto = new ArrayList<>();
		peliculasList.stream().map(c -> peliculasListDto.add(PeliculaDto.getEntity(c))).toArray();
		return peliculasListDto;
	}

	public String crearPelicula(PeliculaDto pelicula) {
		if (pelicula.getTitulo() != null && pelicula.getFechaCreacion() != null && pelicula.getImagen() != null) {
			if (verificarCalificacion(pelicula)) {
				if (verificarGenero(pelicula)) {
					Pelicula movie = pelicula.buildEntidad();
					repository.saveAndFlush(movie);
					return "Pelicula Agregada con exito";
				} else {
					return "Error: Los datos del Genero no son correctos";
				}
			} else {
				return "Error: Los datos de la calificacion no son correctos. Deben ser mayor a 0 y menor a 5";
			}
		} else {
			return "Error: Datos invalidos o vacios";
		}
	}

	public Boolean actualizarPelicula(PeliculaDto peliculaDto, int id) {
		if (repository.getById(id) != null) {
			Pelicula movie = peliculaDto.buildEntidadbyId(id);
			repository.saveAndFlush(movie);
			return true;
		}else {
			return false;
		}
	}

	public Boolean borrarPelicula(int id) {
		if (repository.getById(id) != null) {
			repository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	
	/*
	 * Listar peliculas segun los parametros pedidos
	 * Utilizacion de Criteria
	 * 
	 */
	public List<PeliculaDto> listarPeliculasPorParams(String queryName, String queryGenre, String queryOrder){
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Pelicula> cq = qb.createQuery(Pelicula.class);
	    Root<Pelicula> customer = cq.from(Pelicula.class);

	    //Constructing list of parameters
	    List<Predicate> predicates = new ArrayList<Predicate>();

	    //Adding predicates in case of parameter not being null
	    if (queryName != null) {
	        predicates.add(
	                qb.equal(customer.get("titulo"), queryName));
	    }
	    if (queryGenre != null) {
	        predicates.add(
	                qb.equal(customer.get("genero"), queryGenre));
	    }
	    //Agrego los parametros
	    //Dato: Si no se ingresa ningun orden, enviara la lista desordenada segun la creacion de la pelicula (id_pelicula)
	    //ASC = Nombres en Ascendente ----- DESC = Nombres en Descendente
	    if ("DESC".equals(queryOrder)) {
		    cq.select(customer)
		            .where(predicates.toArray(new Predicate[]{}));
		    cq.orderBy(qb.desc(customer.get("titulo")));
	    }else if ("ASC".equals(queryOrder)){
		    cq.select(customer)
		            .where(predicates.toArray(new Predicate[]{}));
		    cq.orderBy(qb.asc(customer.get("titulo")));
	    }
	    cq.select(customer)
	            .where(predicates.toArray(new Predicate[]{}));
	    
	    //Ejecucion de Query y Convertir entidades a DTO. Retorna lista
		List<Pelicula> peliculasListEntity = new ArrayList<>();
		peliculasListEntity = em.createQuery(cq).getResultList();
		List<PeliculaDto> peliculasListDto = new ArrayList<>();
		peliculasListEntity.stream().map(c -> peliculasListDto.add(PeliculaDto.getEntity(c))).toArray();
		return peliculasListDto;
		
	}
	
	/*
	 * Verificacion si la Calificaion de la pelicula sea mayor a 0 y menor a 5
	 * 
	 */
	public boolean verificarCalificacion(PeliculaDto pelicula) {
		if (0 <= pelicula.getCalificacion() && pelicula.getCalificacion() <= 5) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Vericacion si se agrego un genero
	 * 
	 */
	public boolean verificarGenero(PeliculaDto pelicula){
			return generoService.verificarDatosGenero(pelicula.getGenero());
	}
}
