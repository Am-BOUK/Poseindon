package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a Trade
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * Trade entity. Then is called/autowired in a controller layer.
 */
public interface ITradeService {

	public Trade getTrade(Integer id) throws EntityNotFoundException;

	public Trade addNewTrade(Trade trade);

	public Trade updateTradee(Integer id, Trade trade) throws EntityNotFoundException;

	public List<Trade> tradesList();

	public boolean deleteTrade(Integer id) throws EntityNotFoundException;
}
