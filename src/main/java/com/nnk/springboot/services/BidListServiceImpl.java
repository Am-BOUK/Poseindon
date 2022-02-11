package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * implementation of BidList business processing
 * 
 */
@Service
public class BidListServiceImpl implements IBidListService {

	private static final Logger logger = LogManager.getLogger("BidListServiceImpl");

	/**
	 * Calling operations of the bidListRepository
	 */
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Get One bid object ** This operation allows to check if the id of the bid we
	 * want to get its information already exist in the database, then allows to use
	 * its id to get him
	 * 
	 * @param id : id of the bid object which we want to get
	 * @return person object if it exists
	 * @throws EntityNotFoundException
	 */
	@Override
	public BidList getBid(Integer id) throws EntityNotFoundException {
		logger.info("Getting bid : " + id);
		Optional<BidList> bidFound = bidListRepository.findById(id);
		if (bidFound.isEmpty()) {
			logger.error("The bid : " + id + ", you want to get, does not exist!");
			throw new EntityNotFoundException("The bid : " + id + ", you want to get, does not exist!");
		}
		logger.info("Bid : " + id + ", found");
		return bidFound.get();
	}

	/**
	 * 
	 * Add a new bid ** This operation allows to check if the id of the bide we want
	 * to add already exists in the database, then allows to add it
	 * 
	 *
	 * @param bid : BidList object to add
	 * @return BidList object added
	 */
	@Override
	public BidList addNewBid(BidList bid) {
		logger.info("add bid " + bid.toString());
		Date currentDate = new Date();
		Timestamp creationBidTimestamp = new Timestamp(currentDate.getTime());
		bid.setCreationDate(creationBidTimestamp);
		return bidListRepository.save(bid);
	}

	/**
	 * Update information of the bid ** This operation allows to check if the id of
	 * the bid we want to update already exists in the database,
	 * 
	 * @param id  : the id of the bid we want to update
	 * @param bid : the Bid Object updated
	 * @return BidList object updated
	 * @throws EntityAlreadyExistException
	 */
	@Override
	public BidList updateBide(Integer id, BidList bid) throws EntityNotFoundException {
		logger.info("updating a Bid");
		Optional<BidList> bidFound = bidListRepository.findById(id);
		if (bidFound == null) {
			logger.error("The dib id : " + id + ", you want to update, does not exist!");
			throw new EntityNotFoundException("The dib id : " + id + ", is already exists!");
		}
		logger.info("update bid id : " + id);
		bidFound.get().setAccount(bid.getAccount() != null ? bid.getAccount() : bidFound.get().getAccount());
		bidFound.get().setType(bid.getType() != null ? bid.getType() : bidFound.get().getType());
		bidFound.get()
				.setBidQuantity(bid.getBidQuantity() != null ? bid.getBidQuantity() : bidFound.get().getBidQuantity());
		Date currentDate = new Date();
		Timestamp revisionBidTimestamp = new Timestamp(currentDate.getTime());
		bidFound.get().setRevisionDate(revisionBidTimestamp);
		return bidListRepository.saveAndFlush(bidFound.get());

	}

	/**
	 * get all Bids
	 * 
	 * @return list of all bids existing in the database
	 */
	@Override
	public List<BidList> bidList() {
		logger.info("get list of Bids");
		return bidListRepository.findAll();

	}

	/**
	 * Delete the bid ** This operation allows to check if the id of te bid we want
	 * to delete already exist in the database, then allows to use its id to delete
	 * it
	 * 
	 * @param id : id of the bid we want to delete
	 * @throws Exception
	 */
	@Override
	public boolean deleteBid(Integer id) throws EntityNotFoundException {
		logger.info("deleting a Bid");
		Optional<BidList> bidFound = bidListRepository.findById(id);
		if (bidFound.isEmpty()) {
			logger.error("The dib id : " + id + ", you want to delete, does not exist!");
			throw new EntityNotFoundException("The dib id : " + id + ", you want to delete, does not exist!");
		}

		logger.info("delete the Bid id " + id);
		bidListRepository.deleteById(id);
		return true;
	}

}
