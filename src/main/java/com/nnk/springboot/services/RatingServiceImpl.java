package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;

/**
 * implementation of Rating business processing
 * 
 */
@Service
public class RatingServiceImpl implements IRatingService {

	private static final Logger logger = LogManager.getLogger("RatingServiceImpl");

	/**
	 * Calling operations of the bidListRepository
	 */
	@Autowired
	private RatingRepository ratingRepository;

	/**
	 * Get One Rating object ** This operation allows to check if the id of the
	 * Rating we want to get its information already exist in the database, then
	 * allows to use its id to get him
	 * 
	 * @param id : id of the rating object which we want to get
	 * @return rating object if it exists
	 * @throws EntityNotFoundException
	 */
	@Override
	public Rating getRating(Integer id) throws EntityNotFoundException {
		logger.info("Getting Rating : " + id);
		Optional<Rating> ratingFound = ratingRepository.findById(id);
		if (ratingFound.isEmpty()) {
			logger.error("The rating id : " + id + ", you want to get, does not exist!");
			throw new EntityNotFoundException("The rating id : " + id + ", you want to get, does not exist!");
		}
		logger.info("the rating id : " + id + ", is found !");
		return ratingFound.get();
	}

	/**
	 * 
	 * Add a new rating ** This operation allows to check if the id of the rating we
	 * want to add already exists in the database, then allows to add it
	 * 
	 *
	 * @param rating : rating object to add
	 * @return rating object added
	 */
	@Override
	public Rating addNewRating(Rating rating) {
		logger.info("add rating id : " + rating.toString());
		return ratingRepository.save(rating);
	}

	/**
	 * Update information of the rating ** This operation allows to check if the id
	 * of the rating we want to update already exists in the database,
	 * 
	 * @param id     : the id of the rating we want to update
	 * @param rating : the rating Object updated
	 * @return rating object updated
	 * @throws EntityNotFoundException
	 */
	@Override
	public Rating updateRating(Integer id, Rating rating) throws EntityNotFoundException {
		logger.info("updating a Rating");
		Optional<Rating> ratingFound = ratingRepository.findById(id);
		if (ratingFound.isEmpty()) {
			logger.error("The rating id : " + id + ", you want to update, does not exist!");
			throw new EntityNotFoundException("The rating id : " + id + ", is already exists!");
		}
		logger.info("update rating id : " + id);
		ratingFound.get().setFitchRating(
				rating.getFitchRating() != null ? rating.getFitchRating() : ratingFound.get().getFitchRating());
		ratingFound.get().setMoodysRating(
				rating.getMoodysRating() != null ? rating.getMoodysRating() : ratingFound.get().getMoodysRating());
		ratingFound.get().setSandPRating(
				rating.getSandPRating() != null ? rating.getSandPRating() : ratingFound.get().getSandPRating());
		ratingFound.get().setOrderNumber(
				rating.getOrderNumber() != null ? rating.getOrderNumber() : ratingFound.get().getOrderNumber());
		return ratingRepository.saveAndFlush(ratingFound.get());

	}

	/**
	 * get all ratings
	 * 
	 * @return list of all ratings existing in the database
	 */
	@Override
	public List<Rating> ratingList() {
		logger.info("get list of Bids");
		return ratingRepository.findAll();

	}

	/**
	 * Delete the rating ** This operation allows to check if the id of the rating
	 * we want to delete already exist in the database, then allows to use its id to
	 * delete it
	 * 
	 * @param id : id of the rating we want to delete
	 * @throws EntityNotFoundException
	 */
	@Override
	public boolean deleteRating(Integer id) throws EntityNotFoundException {
		logger.info("deleting a rating");
		Optional<Rating> ratingFound = ratingRepository.findById(id);
		if (ratingFound.isEmpty()) {
			logger.error("The rating id : " + id + ", you want to delete, does not exist!");
			throw new EntityNotFoundException("The rating id : " + id + ", you want to delete, does not exist!");
		}

		logger.info("delete the rating id " + id);
		ratingRepository.deleteById(id);
		return true;
	}

}
