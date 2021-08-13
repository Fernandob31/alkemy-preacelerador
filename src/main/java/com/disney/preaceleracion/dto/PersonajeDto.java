package com.disney.preaceleracion.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.disney.preaceleracion.model.Pelicula;
import com.disney.preaceleracion.model.Personaje;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PersonajeDto {
	
	private String imagen;
	
	private String nombre;
	
	private int edad;

	private int peso;

	private String historia;
	
	private List<PeliculaDto> peliculas;

	public Personaje buildEntidad() {
		return Personaje.builder()
				.imagen(this.imagen)
				.nombre(this.nombre)
				.edad(this.edad)
				.peso(this.peso)
				.historia(this.historia)
				.build();
	}
	
	public Personaje buildEntidadId(int id) {
		return Personaje.builder()
				.id(id)
				.imagen(this.imagen)
				.nombre(this.nombre)
				.edad(this.edad)
				.peso(this.peso)
				.historia(this.historia)
				.build();
	}
	
	public List<Pelicula> buildPeliculaList(List<PeliculaDto> peliculaDto){
		return peliculaDto.stream().map(pelicula -> pelicula.buildEntidad()).collect(Collectors.toList());	//== .toList (Java 16)
		
	}

	public static PersonajeDto getEntity(Personaje c) {
		return PersonajeDto.builder()
				.imagen(c.getImagen())
				.nombre(c.getNombre())
				.edad(c.getEdad())
				.peso(c.getPeso())
				.historia(c.getHistoria())
				.build();
	}
	public static PersonajeDto getEntityWithMovie(Personaje c) {
		PersonajeDto personaje = getEntity(c);
		personaje.setPeliculas(getPeliculas(c.getPeliculas()));
		return personaje;
	}
	
	public static List<PeliculaDto> getPeliculas(List<Pelicula> entity){
		return entity.stream().map(pelicula -> PeliculaDto.getEntity(pelicula)).collect(Collectors.toList());	//== .toList (Java 16)
		
	}
	
}
