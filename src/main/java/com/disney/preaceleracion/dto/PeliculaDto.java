package com.disney.preaceleracion.dto;

import com.disney.preaceleracion.model.Pelicula;
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

public class PeliculaDto {
		
		private String imagen;
		
		private String titulo;
		
		private String fechaCreacion;
		
		private int calificacion;
		
		private GeneroDto genero;
		
		public Pelicula buildEntidad() {
			return Pelicula.builder()
					.imagen(this.imagen)
					.titulo(this.titulo)
					.fechaCreacion(this.fechaCreacion)
					.calificacion(this.calificacion)
					.genero(genero.buildEntity())
					.build();
		}
		public Pelicula buildEntidadbyId(int id) {
			return Pelicula.builder()
					.id(id)
					.imagen(this.imagen)
					.titulo(this.titulo)
					.fechaCreacion(this.fechaCreacion)
					.calificacion(this.calificacion)
					.build();
		}
		
		public static PeliculaDto getEntity(Pelicula pelicula) {
			GeneroDto genero = GeneroDto.getEntity(pelicula.getGenero());
			return PeliculaDto.builder()
					.imagen(pelicula.getImagen())
					.titulo(pelicula.getTitulo())
					.fechaCreacion(pelicula.getFechaCreacion())
					.calificacion(pelicula.getCalificacion())
					.genero(genero)
					.build();
		}
		
}
