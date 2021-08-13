package com.disney.preaceleracion.service;

import org.springframework.stereotype.Service;

import com.disney.preaceleracion.dto.GeneroDto;

@Service
public class GeneroService {
	
	public boolean verificarDatosGenero (GeneroDto genero){
		if(genero.getNombre() != null && genero.getImagen() != null) {
			return true;
		}
		return false;
	}

}
