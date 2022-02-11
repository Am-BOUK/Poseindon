package com.nnk.springboot.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.IGetUserInfoService;
import com.nnk.springboot.services.IRatingService;

/**
 * Implementing the management of interactions between the application rating
 * and the application.
 *
 */
@Controller
public class RatingController {
	private static final Logger logger = LogManager.getLogger("RatingController");
	/**
	 * Inject rating service
	 */
	@Autowired
	private IRatingService ratingService;

	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * Read-Get list of all rating
	 * 
	 * @param model : Model defines a holder for all rating list attributes and user
	 *              info and is primarily designed for adding attributes to the
	 *              model
	 * @param user  : Principal contains user information
	 * @return a list of all rating page web
	 */
	@RequestMapping("/rating/list")
	public String home(Model model, Principal user) {
		logger.info("Get all rating list to show to the view");
		model.addAttribute("ratings", ratingService.ratingList());
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "rating/list";
	}

	/**
	 * show Add rating Page ** this method allows to show a form which we fill the
	 * rating information
	 * 
	 * @param rating
	 * @return Add rating Page
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		logger.info("Show Add rating Page");
		return "rating/add";
	}

	/**
	 * validate rating added ** this method allows to save a rating information
	 * which we have fill in the form
	 * 
	 * @param model  : Model defines a holder for rating attributes and is primarily
	 *               designed for adding attributes to the model
	 * @param rating : rating object filling in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * 
	 * @return a list of all rating page web
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		logger.info("Validate rating Add");
		if (!result.hasErrors()) {
			logger.info("Get all rating List");
			ratingService.addNewRating(rating);
			model.addAttribute("ratings", ratingService.ratingList());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	/**
	 * show update rating Page ** this method allows to show a form which we fill
	 * the rating information to update
	 * 
	 * @param id    : id of the rating object to update
	 * @param model : Model defines a holder for user attributes and is primarily
	 *              designed for adding attributes to the model
	 * @return update rating Page
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Show Update Form");
		Rating rating = ratingService.getRating(id);
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	/**
	 * update rating ** this method allows to save a user information updated which
	 * we have fill in the form
	 * 
	 * @param id     : id of the rating object to update
	 * @param rating : rating object updated in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * @param model  : Model defines a holder for all rating list attributes and is
	 *               primarily designed for adding attributes to the model
	 * @return a list of all rating page web
	 * @throws EntityNotFoundException
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model)
			throws EntityNotFoundException {
		logger.info("Validate Update rating");
		if (result.hasErrors()) {
			return "rating/update";
		}
		logger.info("Get all rating List");
		ratingService.updateRating(id, rating);
		model.addAttribute("ratings", ratingService.ratingList());
		return "redirect:/rating/list";
	}

	/**
	 * Delete-Remove a rating ** this method allows to delete the rating object
	 * 
	 * @param id    : id of the rating object to delete
	 * @param model : Model defines a holder for all rating list attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return a list of all rating page web
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Delete rating");
		ratingService.deleteRating(id);
		logger.info("Get all rating List");
		model.addAttribute("ratings", ratingService.ratingList());
		return "redirect:/rating/list";
	}
}
