package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * implementation of Trade business processing
 * 
 */
@Service
public class TradeServiceImpl implements ITradeService {
	private static final Logger logger = LogManager.getLogger("TradeServiceImpl");

	/**
	 * Calling operations of the TradeRepository
	 */
	@Autowired
	private TradeRepository tradeRepository;

	/**
	 * Get One trade object ** This operation allows to check if the id of the trade
	 * we want to get his information already exist in the database, then allows to
	 * use its id to get him
	 * 
	 * @param id : id of the trade object which we want to get
	 * @return trade object if it exists
	 * @throws EntityNotFoundException
	 */
	@Override
	public Trade getTrade(Integer id) throws EntityNotFoundException {
		logger.info("Getting Trade id : " + id);
		Optional<Trade> tradeFound = tradeRepository.findById(id);
		if (tradeFound.isEmpty()) {
			logger.error("The Trade id : " + id + ", you want to get, does not exist!");
			throw new EntityNotFoundException("The Trade id : " + id + ", you want to get, does not exist!");
		}
		logger.info("Trade id : " + id + ", found");
		return tradeFound.get();
	}

	/**
	 * 
	 * Add a new Trade ** This operation allows to check if the id of the Trade we
	 * want to add already exists in the database, then allows to add it
	 * 
	 * @param Trade : Trade object to add
	 * @return Trade object added
	 */
	@Override
	public Trade addNewTrade(Trade trade) {
		logger.info("add Trade id : " + trade.getTradeId());
		Date currentDate = new Date();
		Timestamp creationBidTimestamp = new Timestamp(currentDate.getTime());
		trade.setCreationDate(creationBidTimestamp);
		return tradeRepository.save(trade);
	}

	/**
	 * Update information of the Trade ** This operation allows to check if the id
	 * of the Trade we want to update already exists in the database,
	 * 
	 * @param id    : the id of the Trade we want to update
	 * @param Trade : the Trade Object updated
	 * @return Trade object updated
	 * @throws EntityNotFoundException
	 */
	@Override
	public Trade updateTradee(Integer id, Trade trade) throws EntityNotFoundException {
		logger.info("updating a Trade");
		Optional<Trade> tradeFound = tradeRepository.findById(id);
		if (tradeFound.isEmpty()) {
			logger.error("The trade id : " + trade.getTradeId() + ", you want to update, does not exist!");
			throw new EntityNotFoundException(
					"The trade id : " + trade.getTradeId() + ", you want to update, does not exist!\"");
		}

		logger.info("update Trade id : " + id);
		tradeFound.get().setAccount(trade.getAccount() != null ? trade.getAccount() : tradeFound.get().getAccount());
		tradeFound.get().setType(trade.getType() != null ? trade.getType() : tradeFound.get().getType());
		tradeFound.get().setBuyQuantity(
				trade.getBuyQuantity() != null ? trade.getBuyQuantity() : tradeFound.get().getBuyQuantity());
		Date currentDate = new Date();
		Timestamp revisionTimestamp = new Timestamp(currentDate.getTime());
		tradeFound.get().setRevisionDate(revisionTimestamp);

		return tradeRepository.saveAndFlush(tradeFound.get());

	}

	/**
	 * get all Trades
	 * 
	 * @return list of all Trades existing in the database
	 */
	@Override
	public List<Trade> tradesList() {
		logger.info("get list of Trades");
		return tradeRepository.findAll();

	}

	/**
	 * Delete the Trade ** This operation allows to check if the id of the Trade we
	 * want to delete already exist in the database, then allows to use its id to
	 * delete it
	 * 
	 * @param id : id of the Trade we want to delete
	 * @return
	 * @throws EntityNotFoundException
	 */
	@Override
	public boolean deleteTrade(Integer id) throws EntityNotFoundException {
		logger.info("deleting a Trade");
		Optional<Trade> tradeFound = tradeRepository.findById(id);
		if (tradeFound.isEmpty()) {
			logger.error("The trade id : " + id + ", you want to delete, does not exist!");
			throw new EntityNotFoundException("The trade id : " + id + ", you want to delete, does not exist!");
		}

		logger.info("delete the Trade id " + id);
		tradeRepository.deleteById(id);
		return true;

	}
}
