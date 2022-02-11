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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;

@ExtendWith(MockitoExtension.class)
class BidListServiceTest {

	@Mock
	private BidListRepository bidListRepository;

	@InjectMocks
	private BidListServiceImpl bidListService;

	@Test
	public void getBidTest() throws EntityNotFoundException {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid.setBidListId(1);
		Optional<BidList> optionalBidList = Optional.of(bid);
		when(bidListRepository.findById(1)).thenReturn(optionalBidList);
		BidList result = bidListService.getBid(1);

		assertEquals(result.getBidListId(), 1);
	}

	@Test
	public void getBidTest_whenBidDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			bidListService.getBid(2);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The bid : 2, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewBidTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		when(bidListRepository.save(bid)).thenReturn(bid);

		BidList result = bidListService.addNewBid(bid);
		assertEquals(result.getBidQuantity(), 10d);

	}

	@Test
	public void updateBidTest() throws EntityNotFoundException {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid.setBidListId(1);
		Optional<BidList> optionalBidList = Optional.of(bid);
		BidList bidUpdated = new BidList();
		bidUpdated.setBidQuantity(30d);
		when(bidListRepository.findById(1)).thenReturn(optionalBidList);
		when(bidListRepository.saveAndFlush(bid)).thenReturn(bid);

		BidList result = bidListService.updateBide(1, bidUpdated);
		assertEquals(result.getBidQuantity(), 30d);

	}

	@Test
	public void updateBidTest_whenBidDoesNotExist_shouldReturnEntityNotFoundException() {
		BidList bidUpdated = new BidList();
		bidUpdated.setBidQuantity(30d);
		when(bidListRepository.findById(2)).thenReturn(null);
		try {
			bidListService.updateBide(2, bidUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The dib id : 2, is already exists!");
		}

	}

	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		List<BidList> bidList = new ArrayList<>();
		bidList.add(bid);
		when(bidListRepository.findAll()).thenReturn(bidList);

		List<BidList> result = bidListService.bidList();
		assertThat(result.contains(bid));
	}

	@Test
	public void deleteBidListTest() throws EntityNotFoundException {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid.setBidListId(1);
		Optional<BidList> optionalBidList = Optional.of(bid);
		when(bidListRepository.findById(1)).thenReturn(optionalBidList);

		boolean resultat = bidListService.deleteBid(1);

		assertTrue(resultat);

	}

	@Test
	public void deleteBidListTest_whenBidDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			bidListService.deleteBid(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The dib id : 1, you want to delete, does not exist!");
		}

	}

}
