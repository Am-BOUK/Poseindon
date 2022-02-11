package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;

/**
 * Repository Interface that will allow interaction with external data sources
 * 
 * Interface used to define JpaRepository operations with the User table. It
 * extends the JpaRepository interface delivered by Spring Data JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
	public Optional<User> findByUsername(String username);

}
