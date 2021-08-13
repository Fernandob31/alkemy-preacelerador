package com.disney.preaceleracion.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.preaceleracion.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{

	List<Pelicula> findByTituloOrGenero_Id(String queryName, String queryGenre, Sort by);

}
