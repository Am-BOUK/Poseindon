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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.services.GetUserInfoService;
import com.nnk.springboot.services.RatingServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class RatingControllerTest {

	@Mock
	private RatingServiceImpl ratingService;

	@Mock
	private GetUserInfoService getUserInfoService;

	@Mock
	private Model model;
	
	@Mock
	private Principal user;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private RatingController ratingController;

	@Test
	public void homeTest() {
		when(ratingService.ratingList()).thenReturn(new ArrayList<Rating>());
		String actual = ratingController.home(model, user);
		assertTrue(actual.equals("rating/list"));
	}

	@Test
	public void addRatingFormTest() {
		String actual = ratingController.addRatingForm(new Rating());
		assertTrue(actual.equals("rating/add"));
	}

	@Test
	public void validateTest_ShouldReturnBidList() {
		when(bindingResult.hasErrors()).thenReturn(false);
		String actual = ratingController.validate(new Rating(), bindingResult, model);
		assertTrue(actual.equals("redirect:/rating/list"));
	}

	@Test
	public void validateTest_withError_shouldReturnAddPage() {
		when(bindingResult.hasErrors()).thenReturn(true);
		String actual = ratingController.validate(new Rating(), bindingResult, model);
		assertTrue(actual.equals("rating/add"));

	}

	@Test
	public void showUpdateFormTest_shouldReturnUpdate() throws EntityNotFoundException {
		when(ratingService.getRating(1)).thenReturn(new Rating());
		String actual = ratingController.showUpdateForm(2, model);
		assertTrue(actual.equals("rating/update"));
	}

	@Test
	public void updateRatingTest() throws EntityNotFoundException {
		Rating testItem = new Rating();
		when(ratingService.updateRating(1, testItem)).thenReturn(testItem);
		String actual = ratingController.updateRating(1, testItem, bindingResult, model);
		assertTrue(actual.equals("redirect:/rating/list"));
	}

	@Test
	public void deleteRatingTest() throws EntityNotFoundException {
		String actual = ratingController.deleteRating(1, model);
		assertTrue(actual.equals("redirect:/rating/list"));
	}

}
