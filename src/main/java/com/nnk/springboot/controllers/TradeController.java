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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.IGetUserInfoService;
import com.nnk.springboot.services.ITradeService;

/**
 * Implementing the management of interactions between the application trade and
 * the application.
 *
 */
@Controller
public class TradeController {
	private static final Logger logger = LogManager.getLogger("TradeController");
	/**
	 * Inject trade service
	 */
	@Autowired
	private ITradeService tradeService;

	@Autowired
	private IGetUserInfoService getUserInfoService;
	
	

	/**
	 * Read-Get list of trades
	 * 
	 * @param model : Model defines a holder for trades list attributes and is
	 *              primarily designed for adding attributes to the model
	 * @return a list trades page web
	 */
	@RequestMapping("/trade/list")
	public String home(Model model, Principal user) {
		logger.info("Get all Trades to show to the view");
		model.addAttribute("trades", tradeService.tradesList());
		model.addAttribute("userInfo", getUserInfoService.getUserInfo(user));
		return "trade/list";
	}

	/**
	 * show Add trade Page ** this method allows to show a form which we fill the
	 * trade information
	 * 
	 * @param trade to add
	 * @return Add trade Page
	 */
	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		logger.info("Show Add trade Page");
		return "trade/add";
	}

	/**
	 * validate trade added ** this method allows to save a trade information which
	 * we have fill in the form
	 * 
	 * @param model  : Model defines a holder for trade attributes and is primarily
	 *               designed for adding attributes to the model
	 * @param user   : trade object filling in the form
	 * @param result : BindingResult holds the result of a validation and binding
	 *               and contains errors that may have occurred.
	 * 
	 * @return a list of trades page web
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		logger.info("Validate trade Add");
		if (!result.hasErrors()) {
			logger.info("Get trades List");
			tradeService.addNewTrade(trade);
			model.addAttribute("trades", tradeService.tradesList());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	/**
	 * show update trade Page ** this method allows to show a form which we fill the
	 * trade information to update
	 * 
	 * @param id    : id of the trade object to update
	 * @param model : Model defines a holder for trade attributes and is primarily
	 *              designed for adding attributes to the model
	 * @return update trade Page
	 * @throws EntityNotFoundException
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Show Update Form");
		model.addAttribute("trade", tradeService.getTrade(id));
		return "trade/update";
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
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model)
			throws EntityNotFoundException {
		logger.info("Validate Update trade");
		if (result.hasErrors()) {
			return "trade/update";
		}
		logger.info("Get trades List");
		tradeService.updateTradee(id, trade);
		model.addAttribute("trades", tradeService.tradesList());
		return "redirect:/trade/list";
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
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) throws EntityNotFoundException {
		logger.info("Delete trade");
		tradeService.deleteTrade(id);
		logger.info("Get trades List");
		model.addAttribute("trades", tradeService.tradesList());
		return "redirect:/trade/list";
	}
}
