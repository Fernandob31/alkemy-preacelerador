package com.disney.preaceleracion.dto;


import com.disney.preaceleracion.model.Pelicula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDto {

		private String titulo;
		
		private String fechaCreacion;
		
		private int calificacion;
		
		public Pelicula buildEntidad() {
			return Pelicula.builder()
					.titulo(this.titulo)
					.fechaCreacion(this.fechaCreacion)
					.calificacion(this.calificacion)
					.build();
		}
		public Pelicula buildEntidadbyId(int id) {
			return Pelicula.builder()
					.id(id)
					.titulo(this.titulo)
					.fechaCreacion(this.fechaCreacion)
					.calificacion(this.calificacion)
					.build();
		}
		
}
