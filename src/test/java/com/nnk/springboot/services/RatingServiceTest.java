package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	private RatingServiceImpl ratingService;

	@Test
	public void getRatingTest() throws EntityNotFoundException {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating.setId(1);
		Optional<Rating> optionalRating = Optional.of(rating);
		when(ratingRepository.findById(1)).thenReturn(optionalRating);
		Rating result = ratingService.getRating(1);

		assertEquals(result.getId(), 1);
	}

	@Test
	public void getRatingTest_whenRatingDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			ratingService.getRating(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The rating id : 1, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewRatingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		when(ratingRepository.save(rating)).thenReturn(rating);

		Rating result = ratingService.addNewRating(rating);
		assertEquals(result.getOrderNumber(), 10);

	}

	@Test
	public void updateRatingTest() throws EntityNotFoundException {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating.setId(1);
		Optional<Rating> optionalRating = Optional.of(rating);
		Rating ratingUpdated = new Rating();
		ratingUpdated.setOrderNumber(30);
		when(ratingRepository.findById(1)).thenReturn(optionalRating);
		when(ratingRepository.saveAndFlush(rating)).thenReturn(rating);

		Rating result = ratingService.updateRating(1, ratingUpdated);
		assertEquals(result.getOrderNumber(), 30);

	}

	@Test
	public void updateRatingTest_whenRatingDoesNotExist_shouldReturnEntityNotFoundException() {
		Rating ratingUpdated = new Rating();
		ratingUpdated.setOrderNumber(30);
		try {
			ratingService.updateRating(1, ratingUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The rating id : 1, is already exists!");
		}

	}

	@Test
	public void ratingListTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		List<Rating> ratingList = new ArrayList<>();
		ratingList.add(rating);
		when(ratingRepository.findAll()).thenReturn(ratingList);

		List<Rating> result = ratingService.ratingList();
		assertThat(result.contains(rating));
	}

	@Test
	public void deleteRatingTest() throws EntityNotFoundException {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating.setId(1);
		Optional<Rating> optionalRating = Optional.of(rating);
		when(ratingRepository.findById(1)).thenReturn(optionalRating);

		boolean resultat = ratingService.deleteRating(1);

		assertTrue(resultat);

	}

	@Test
	public void deleteRatingTest_whenRatingDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			ratingService.deleteRating(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The rating id : 1, you want to delete, does not exist!");
		}

	}

}
