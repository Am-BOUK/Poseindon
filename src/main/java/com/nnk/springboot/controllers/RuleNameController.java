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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.IGetUserInfoService;
import com.nnk.springboot.services.IRuleNameService;

/**
 * Implementing the management of interactions between the application user and
 * the application.
 *
 */
@Controller
public class RuleNameController {
	private static final Logger logger = LogManager.getLogger("RuleNameController");

	/**
	 * Inject user service
	 */
	@Autowired
	private IRuleNameService ruleNameService;

	/**
	 * Inject userInfo service
	 */
	@Autowired
	private IGetUserInfoService getUserInfoService;

	/**
	 * Read-Get list of ruleNames
	 * 
	 * @param model : Model defines a holder for ruleNames list attributes and user
	 *              info and is primarily designed for adding attributes to the
	 *              model
	 * @param user  : Principal contains user information
	 * @return a list ruleNames page web
	 */
	@RequestMapping("/ruleName/list")
	public String home(Model model, Principal user) {
		logger.info("Get all Users to show to the view");
		model.addAttribute("ruleNames", ruleNameService.ruleNameList());
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "ruleName/list";
	}

	/**
	 * show Add ruleName Page ** this method allows to show a form which we fill the
	 * ruleName information
	 * 
	 * @param ruleName
	 * @return Add ruleName Page
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		logger.info("Show Add ruleName Page");
		return "ruleName/add";
	}

	/**
	 * validate ruleName added ** this method allows to save a ruleName information
	 * which we have fill in the form
	 * 
	 * @param model    : Model defines a holder for ruleName attributes and is
	 *                 primarily designed for adding attributes to the model
	 * @param ruleName : ruleName object filling in the form
	 * @param result   : BindingResult holds the result of a validation and binding
	 *                 and contains errors that may have occurred.
	 * 
	 * @return a list of ruleNames page web
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		logger.info("Validate ruleName Add");
		if (!result.hasErrors()) {
			logger.info("Get ruleNames List");
			ruleNameService.addNewRuleName(ruleName);
			model.addAttribute("ruleNames", ruleNameService.ruleNameList());
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	/**
	 * show update ruleName Page ** this method allows to show a form which we fill
	 * the ruleName information to update
	 * 
	 * @param id    : id of the ruleName object to update
	 * @param model : Model defines a holder for ruleName attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return update ruleName Page
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Show ruleName Form");
		RuleName ruleName = ruleNameService.getRuleName(id);
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	/**
	 * update ruleName ** this method allows to save a ruleName information updated
	 * which we have fill in the form
	 * 
	 * @param id       : id of the ruleName object to update
	 * @param ruleName : ruleName object updated in the form
	 * @param result   : BindingResult holds the result of a validation and binding
	 *                 and contains errors that may have occurred.
	 * @param model    : Model defines a holder for ruleNames list attributes and is
	 *                 primarily designed for adding attributes to the model
	 * @return a list of ruleNames page web
	 * @throws EntityNotFoundException
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) throws EntityNotFoundException {
		logger.info("Validate Update ruleName");
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		logger.info("Get Users List");
		ruleNameService.updateRuleName(id, ruleName);
		model.addAttribute("ruleNames", ruleNameService.ruleNameList());
		return "redirect:/ruleName/list";
	}

	/**
	 * Delete-Remove a ruleName ** this method allows to delete the ruleName object
	 * 
	 * @param id    : id of the ruleName object to delete
	 * @param model : Model defines a holder for ruleNames list attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return a list ruleNames page web
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Delete ruleName");
		ruleNameService.deleteRuleName(id);
		logger.info("Get ruleNames List");
		model.addAttribute("ruleNames", ruleNameService.ruleNameList());
		return "redirect:/ruleName/list";
	}
}
