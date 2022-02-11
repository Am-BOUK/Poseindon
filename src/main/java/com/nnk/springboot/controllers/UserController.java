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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.IGetUserInfoService;
import com.nnk.springboot.services.UserServiceImpl;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@Controller
public class UserController {
	private static final Logger logger = LogManager.getLogger("UserController");

	/**
	 * Inject user service
	 */
	@Autowired
	private UserServiceImpl userService;

	/**
	 * Inject userInfo service
	 */
	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * Read-Get list of users
	 * 
	 * @param model : Model defines a holder for users list attributes and user info
	 *              and is primarily designed for adding attributes to the model
	 * @param user  : Principal contains user information
	 * @return a list users page web
	 */
	@RequestMapping("/user/list")
	public String home(Model model, Principal user) {
		logger.info("Get all Users to show to the view");
		model.addAttribute("users", userService.usersList());
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "user/list";
	}

	/**
	 * show Add user Page ** this method allows to show a form which we fill the
	 * user information
	 * 
	 * @param user
	 * @return Add user Page
	 */
	@GetMapping("/user/add")
	public String addUser(User user) {
		logger.info("Show Add User Page");
		return "user/add";
	}

	/**
	 * validate user added ** this method allows to save a user information which we
	 * have fill in the form
	 * 
	 * @param model  : Model defines a holder for user attributes and is primarily
	 *               designed for adding attributes to the model
	 * @param user   : user object filling in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * 
	 * @return a list of users page web
	 * @throws EntityAlreadyExistException
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		logger.info("Validate User Add");
		if (!result.hasErrors()) {
			try {
				logger.info("Get Users List");
				userService.addNewUser(user);
				model.addAttribute("users", userService.usersList());
				return "redirect:/user/list";
			} catch (EntityAlreadyExistException e) {
				e.getMessage();
				model.addAttribute("exception", e);
			}
		}
		return "user/add";
	}

	/**
	 * show update user Page ** this method allows to show a form which we fill the
	 * user information to update
	 * 
	 * @param id    : id of the user object to update
	 * @param model : Model defines a holder for user attributes and is primarily
	 *              designed for adding attributes to the model
	 * @return update user Page
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Show Update Form");
		User user = userService.getUser(id);
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	/**
	 * update user ** this method allows to save a user information updated which we
	 * have fill in the form
	 * 
	 * @param id     : id of the user object to update
	 * @param user   : user object updated in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * @param model  : Model defines a holder for users list attributes and is
	 *               primarily designed for adding attributes to the model
	 * @return a list of users page web
	 * @throws EntityNotFoundException
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model)
			throws EntityNotFoundException {
		logger.info("Validate Update User");
		if (result.hasErrors()) {
			return "user/update";
		}
		logger.info("Get Users List");
		userService.updateUser(id, user);
		model.addAttribute("users", userService.usersList());
		return "redirect:/user/list";
	}

	/**
	 * Delete-Remove a user ** this method allows to delete the user object
	 * 
	 * @param id    : id of the user object to delete
	 * @param model : Model defines a holder for users list attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return a list users page web
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Delete User");
		userService.deleteUser(id);
		logger.info("Get Users List");
		model.addAttribute("users", userService.usersList());
		return "redirect:/user/list";
	}
}
