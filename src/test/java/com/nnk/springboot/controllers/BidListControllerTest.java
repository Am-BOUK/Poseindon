package com.nnk.springboot.controllers;

import static org.junit.Assert.assertTrue;
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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.BidListServiceImpl;
import com.nnk.springboot.services.GetUserInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
class BidListControllerTest {

	@Mock
	private BidListServiceImpl bidListService;
	
	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private BidListController bidListController;

	@Test
	public void homeTest() {
		when(bidListService.bidList()).thenReturn(new ArrayList<BidList>());
		String actual = bidListController.home(model, user);

		assertTrue(actual.equals("bidList/list"));
	}

	@Test
	public void addBidFormTest() {
		String actual = bidListController.addBidForm(new BidList());
		assertTrue(actual.equals("bidList/add"));
	}

	@Test
	public void validateTest_shouldReturnBidList() {
		BidList testItem = new BidList();
		when(bindingResult.hasErrors()).thenReturn(false);
		String actual = bidListController.validate(testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/bidList/list"));
	}

	@Test
	public void validateTest_WithError_shouldReturnAddPage() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String actual = bidListController.validate(new BidList(), bindingResult, model);
		assertTrue(actual.equals("bidList/add"));
	}

	@Test
	public void showUpdateFormTest_ShouldReturnUpdate() throws EntityNotFoundException {
		when(bidListService.getBid(1)).thenReturn(new BidList());
		String actual = bidListController.showUpdateForm(1, model);
		assertTrue(actual.equals("bidList/update"));
	}

	@Test
	public void updateBidListTest() throws EntityNotFoundException {
		BidList testItem = new BidList();
		when(bidListService.updateBide(1, testItem)).thenReturn(new BidList());
		String actual = bidListController.updateBid(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/bidList/list"));

	}

	@Test
	public void deleteBidListTest() throws EntityNotFoundException {
		String actual = bidListController.deleteBid(1, model);
		assertTrue(actual.equals("redirect:/bidList/list"));

	}
}
