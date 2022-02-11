package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class TradeServiceTest {

	@Mock
	private TradeRepository tradeRepository;

	@InjectMocks
	private TradeServiceImpl tradeService;

	@Test
	public void getTradeTest() throws EntityNotFoundException {
		Trade trade = new Trade("Trade Account", "Type", 1.0);
		trade.setTradeId(1);
		Optional<Trade> optionalTrade = Optional.of(trade);
		when(tradeRepository.findById(1)).thenReturn(optionalTrade);
		Trade result = tradeService.getTrade(1);

		assertEquals(result.getTradeId(), 1);
	}

	@Test
	public void getTradeTest_whenTradeDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			tradeService.getTrade(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The Trade id : 1, you want to get, does not exist!");
		}

	}

	@Test
	public void addNewTradeTest() {
		Trade trade = new Trade("Trade Account", "Type", 1.0);
		when(tradeRepository.save(trade)).thenReturn(trade);

		Trade result = tradeService.addNewTrade(trade);
		assertEquals(result.getAccount(), "Trade Account");
	}

	@Test
	public void updateRatingTest() throws EntityNotFoundException {
		Trade trade = new Trade("Trade Account", "Type", 1.0);
		trade.setTradeId(1);
		Optional<Trade> optionalTrade = Optional.of(trade);
		Trade tradeUpdated = new Trade();
		tradeUpdated.setAccount("Trade Updated");
		when(tradeRepository.findById(1)).thenReturn(optionalTrade);
		when(tradeRepository.saveAndFlush(trade)).thenReturn(trade);

		Trade result = tradeService.updateTradee(1, tradeUpdated);
		assertEquals(result.getAccount(), "Trade Updated");

	}

	@Test
	public void updateRatingTest_whenTradeDoesNotExist_shouldReturnEntityNotFoundException() {
		Trade tradeUpdated = new Trade();
		tradeUpdated.setAccount("Trade Updated");
		try {
			tradeService.updateTradee(1, tradeUpdated);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The trade id : null, you want to update, does not exist!");
		}

	}

	@Test
	public void tradeListTest() {
		Trade trade = new Trade("Trade Account", "Type", 1.0);
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(trade);
		when(tradeRepository.findAll()).thenReturn(tradeList);

		List<Trade> result = tradeService.tradesList();
		assertThat(result.contains(trade));
	}

	@Test
	public void deleteTradeTest() throws EntityNotFoundException {
		Trade trade = new Trade("Trade Account", "Type", 1.0);
		trade.setTradeId(1);
		Optional<Trade> optionalTrade = Optional.of(trade);
		when(tradeRepository.findById(1)).thenReturn(optionalTrade);

		boolean resultat = tradeService.deleteTrade(1);

		assertTrue(resultat);

	}

	@Test
	public void deleteTradeTest_whenTradeDoesNotExist_shouldReturnEntityNotFoundException() {
		try {
			tradeService.deleteTrade(1);
		} catch (EntityNotFoundException e) {
			assertTrue(e instanceof EntityNotFoundException);
			assertThat(e.getMessage()).contains("The trade id : 1, you want to delete, does not exist!");
		}

	}
}
