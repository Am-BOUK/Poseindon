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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.IGetUserInfoService;

/**
 * Implementing the management of interactions between the application BidList
 * and the application.
 *
 */
@Controller
public class BidListController {
	private static final Logger logger = LogManager.getLogger("BidListController");

	/**
	 * Inject bidList service
	 */
	@Autowired
	private IBidListService bidListService;

	/**
	 * Inject getUserInfo service
	 */
	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * Read-Get list of bids
	 * 
	 * @param model : Model defines a holder for bids list and user info attributes
	 *              and is primarily designed for adding attributes to the model
	 * @param user  : Principal contains user information
	 * @return a list bids page web
	 */
	@RequestMapping("/bidList/list")
	public String home(Model model, Principal user) {
		logger.info("Get all bids to show to the view");
		model.addAttribute("bids", bidListService.bidList());
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "bidList/list";
	}

	/**
	 * show Add bid Page ** this method allows to show a form which we fill the bid
	 * information
	 * 
	 * @param bid
	 * @return Add bid Page
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		logger.info("Show Add bid Page");
		return "bidList/add";
	}

	/**
	 * validate bid added *** this method allows to save a bid information which we
	 * have fill in the form
	 * 
	 * @param model  : Model defines a holder for bids attributes and is primarily
	 *               designed for adding attributes to the model
	 * @param bid    : bid object filling in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * 
	 * @return a list of bids page web
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("Validate Bid Added");
		if (!result.hasErrors()) {
			logger.info("Get bids List");
			bidListService.addNewBid(bid);
			model.addAttribute("bids", bidListService.bidList());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	/**
	 * show update bid Form ** this method allows to show a form which we fill the
	 * bid information to update
	 * 
	 * @param id    : id of the bid object to update
	 * @param model : Model defines a holder for user attributes and is primarily
	 *              designed for adding attributes to the model
	 * @return update bid Page
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Show Upfate Form");
		BidList bidList = bidListService.getBid(id);
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	/**
	 * update bid ** this method allows to save a bid information updated which we
	 * have fill in the form
	 * 
	 * @param id     : id of the bid object to update
	 * @param bid    : bid object updated in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * @param model  : Model defines a holder for bids list attributes and is
	 *               primarily designed for adding attributes to the model
	 * @return a list of bids page web
	 * @throws EntityNotFoundException
	 */
	@PostMapping("/bidList/update/{bidListId}")
	public String updateBid(@PathVariable("bidListId") Integer bidListId, @Valid BidList bidList, BindingResult result,
			Model model) throws EntityNotFoundException {
		// list Bid
		logger.info("Validate Update bid");
		if (result.hasErrors()) {
			return "bidList/update";
		}
		logger.info("Get bidList/updates List");
		bidListService.updateBide(bidListId, bidList);
		model.addAttribute("bids", bidListService.bidList());
		return "redirect:/bidList/list";
	}

	/**
	 * Delete-Remove a user ** this method allows to delete the bid object
	 * 
	 * @param id    : id of the bid object to delete
	 * @param model : Model defines a holder for bids list attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return a list bids page web
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		logger.info("Delete bid");
		try {
			bidListService.deleteBid(id);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("exception", e);
		}
		logger.info("Get bids List");
		model.addAttribute("bids", bidListService.bidList());
		return "redirect:/bidList/list";
	}
}
