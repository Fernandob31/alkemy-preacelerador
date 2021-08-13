package com.disney.preaceleracion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.disney.preaceleracion.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String userName);

    
}
