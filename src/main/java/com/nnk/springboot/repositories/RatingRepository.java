package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface that will allow interaction with external data sources
 * 
 * Interface used to define JpaRepository operations with the Rating table. It
 * extends the JpaRepository interface delivered by Spring Data JPA.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
