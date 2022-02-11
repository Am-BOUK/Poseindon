package com.nnk.springboot.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.GetUserInfoService;
import com.nnk.springboot.services.TradeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class TradeControllerTest {

	@Mock
	private TradeServiceImpl tradeService;

	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private TradeController tradeController;

	@Test
	public void homeTest() {
		when(tradeService.tradesList()).thenReturn(new ArrayList<Trade>());
		String actual = tradeController.home(model, user);
		assertTrue(actual.equals("trade/list"));
	}

	@Test
	public void addRatingFormTest() {
		String actual = tradeController.addTrade(new Trade());
		assertTrue(actual.equals("trade/add"));
	}

	@Test
	public void validateTest_shouldReturnTradeList() {
		when(bindingResult.hasErrors()).thenReturn(false);
		String actual = tradeController.validate(new Trade(), bindingResult, model);
		assertTrue(actual.equals("redirect:/trade/list"));
	}

	@Test
	public void validateTest_withError_shouldReturnAddPage() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String actual = tradeController.validate(new Trade(), bindingResult, model);
		assertTrue(actual.equals("trade/add"));
	}

	@Test
	public void showUpdateFormTest_ShouldReturnUpdate() throws EntityNotFoundException {
		when(tradeService.getTrade(1)).thenReturn(new Trade());
		String actual = tradeController.showUpdateForm(1, model);
		assertTrue(actual.equals("trade/update"));
	}

	@Test
	public void updateRatingTest() throws EntityNotFoundException {
		Trade testItem = new Trade();
		when(tradeService.updateTradee(1,testItem)).thenReturn(testItem);
		String actual = tradeController.updateTrade(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/trade/list"));
	}

	@Test
	public void deleteRatingTest() throws EntityNotFoundException {
		String actual = tradeController.deleteTrade(1, model);
		assertTrue(actual.equals("redirect:/trade/list"));

	}

}
