package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a BidList
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * BidList entity. Then is called/autowired in a controller layer.
 */
public interface IBidListService {

	public BidList getBid(Integer id) throws EntityNotFoundException;

	public BidList addNewBid(BidList bid);

	public BidList updateBide(Integer id, BidList bid) throws EntityNotFoundException;

	public List<BidList> bidList();

	public boolean deleteBid(Integer id) throws EntityNotFoundException;

}
