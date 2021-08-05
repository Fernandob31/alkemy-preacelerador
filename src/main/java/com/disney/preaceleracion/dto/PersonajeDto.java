package com.disney.preaceleracion.dto;

import com.disney.preaceleracion.model.Personaje;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonajeDto {
	
	private String imagen;
	
	private String nombre;
	
	private int edad;

	private int peso;

	private String historia;

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
}
