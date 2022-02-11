package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository Interface that will allow interaction with external data sources
 * 
 * Interface used to define JpaRepository operations with the BidList table. It
 * extends the JpaRepository interface delivered by Spring Data JPA.
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
