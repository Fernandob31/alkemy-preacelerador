package com.disney.preaceleracion.dto;

import com.disney.preaceleracion.model.Genero;
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
public class GeneroDto {
	
	private String nombre;
	private String imagen;
	
	public Genero buildEntity() {
		return Genero.builder()
				.nombre(this.nombre)
				.imagen(this.imagen)
				.build();
	}
	public Genero buildEntitybyId(int id) {
		return Genero.builder()
				.id(id)
				.nombre(this.nombre)
				.imagen(this.imagen)
				.build();
	}
	
	public static GeneroDto getEntity(Genero genero) {
		return GeneroDto.builder()
				.imagen(genero.getImagen())
				.nombre(genero.getNombre())
				.build();
	}
}
