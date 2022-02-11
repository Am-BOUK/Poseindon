package com.nnk.springboot.controllers;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.services.IGetUserInfoService;
import com.nnk.springboot.services.IUserService;

/**
 * Implementing the management of interactions between the application login and
 * the application.
 *
 */
@Controller
public class LoginController {
	Logger logger = LogManager.getLogger("LoginController");
	/**
	 * Inject userService service
	 */
	@Autowired
	private IUserService userService;
	/**
	 * Inject getUserInfoService service
	 */
	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * ModelAndView *** this method allows to see a login page
	 * 
	 * @return mav : the view login page
	 */
	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * getAllUserArticles *** this method allows to see a user list page
	 * 
	 * @param model : Model defines a holder for user info and is primarily designed
	 *              for adding attributes to the model
	 * @param user  : Principal contains user information
	 * @return mav : the view of user list page
	 */
	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles(Model model, Principal user) {
		logger.info("redirect from secure/article-details to user list");
		ModelAndView mav = new ModelAndView();
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		mav.addObject("users", userService.usersList());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * error *** this method allows to see an error page
	 * 
	 * 
	 * @param model : Model defines a holder user info and is primarily designed for
	 *              adding attributes to the model
	 * @param user  : Principal contains user information
	 * @return mav : the view of 403 page
	 */
	@GetMapping({ "error", "403" })
	public ModelAndView error(Model model, Principal user) {
		logger.info("redirect to error 403");
		ModelAndView mav = new ModelAndView();
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}
}
