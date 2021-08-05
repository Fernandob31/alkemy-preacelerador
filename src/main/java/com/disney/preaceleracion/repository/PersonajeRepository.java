package com.disney.preaceleracion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.preaceleracion.model.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer>{

}
