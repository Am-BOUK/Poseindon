package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a Rating
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * Rating entity. Then is called/autowired in a controller layer.
 */
public interface IRatingService {
	public Rating getRating(Integer id) throws EntityNotFoundException;

	public Rating addNewRating(Rating rating);

	public Rating updateRating(Integer id, Rating rating) throws EntityNotFoundException;

	public List<Rating> ratingList();

	public boolean deleteRating(Integer id) throws EntityNotFoundException;

}
