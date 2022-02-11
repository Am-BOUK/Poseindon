package com.nnk.springboot.controllers;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.services.IGetUserInfoService;

/**
 * Implementing the management of interactions
 * 
 */
@Controller
public class HomeController {
	private static final Logger logger = LogManager.getLogger("HomeController");

	/**
	 * Inject getUserInfo service
	 */
	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * home
	 * 
	 * this method allows to return home page
	 * 
	 * @param model : Model defines a holder for user info attributes and is
	 *              primarily designed for adding attributes to the model
	 * @param user  : Principal contains user information
	 * @return home : home page
	 */
	@RequestMapping("/")
	public String home(Model model, Principal user) {
		logger.info("redirect to home page");
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "/home";
	}

	/**
	 * 
	 * adminHome ** this method allows to redirect to BidList list
	 * 
	 * @return BidList list page
	 */
	@RequestMapping("/admin/home")
	public String adminHome() {
		logger.info("redirect to list of bidlist page");
		return "redirect:/bidList/list";
	}

}
