package com.disney.preaceleracion.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String titulo;
	@Column
	private String fechaCreacion;
	@Column
	private int calificacion;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "pelicula_personaje",
	joinColumns = @JoinColumn(name="pelicula_id"),
	inverseJoinColumns = @JoinColumn(name="personaje_id"))
	private List<Personaje> personajes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="genero_id", nullable=false)
	private Genero genero;

}
