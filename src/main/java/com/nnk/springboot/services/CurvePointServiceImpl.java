package com.nnk.springboot.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;

/**
 * implementation of CurvePoint business processing
 * 
 */
@Service
public class CurvePointServiceImpl implements ICurvePointService {
	private static final Logger logger = LogManager.getLogger("CurvePointServiceImpl");

	/**
	 * Calling operations of the curvePointRepository
	 */
	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * Get One user Curve point ** This operation allows to check if the curveId of
	 * the curve we want to get its information already exist in the database, then
	 * allows to use its curveId to get it
	 * 
	 * @param curveId : curveId of the curvePoint object whose we want to get
	 * @return curvePoint object if it exists
	 * @throws EntityNotFoundException
	 */
	@Override
	public CurvePoint getCurvePoint(Integer id) throws EntityNotFoundException {
		logger.info("Getting curvePoint for curveID : " + id);
		Optional<CurvePoint> curveFound = curvePointRepository.findById(id);
		if (curveFound.isEmpty()) {
			logger.error("The curvePoint id : " + id + ", you want to get, does not exist!");
			throw new EntityNotFoundException("The curvePoint id : " + id + ", you want to get, does not exist!");
		}
		logger.info("The curvePoint id : " + id + ",is found!");
		return curveFound.get();
	}

	/**
	 * 
	 * Add a new curvePoint ** This operation allows to check if the id of the
	 * curvePoint we want to add already exists in the database, then allows to add
	 * it
	 * 
	 *
	 * @param curvePoint : curvePoint object to add
	 * @return curvePoint object added
	 * @throws EntityAlreadyExistException
	 */
	@Override
	public CurvePoint addCurvePoint(CurvePoint curvePoint) throws EntityAlreadyExistException {
		logger.info("adding a new curve point");
		Optional<CurvePoint> curvePointFound = curvePointRepository.findByCurveId(curvePoint.getCurveId());
		if (curvePointFound.isPresent()) {
			logger.error("The curve id : " + curvePoint.getCurveId() + ", you want to add, is already exists!");
			throw new EntityAlreadyExistException(
					"The curve id : " + curvePoint.getCurveId() + ", you want to add, is already exists!");
		}
		logger.info("add curve point id : " + curvePoint.getId());
		Date currentDate = new Date();
		Timestamp creationCurveTimestamp = new Timestamp(currentDate.getTime());
		curvePoint.setCreationDate(creationCurveTimestamp);
		return curvePointRepository.save(curvePoint);
	}

	/**
	 * Update information of the curvePoint ** This operation allows to check if the
	 * id of the curvePoint we want to update already exists in the database,
	 * 
	 * @param id         : the id of the bid we want to update
	 * @param curvePoint : the curvePoint Object updated
	 * @return curvePoint object updated
	 * @throws EntityNotFoundException
	 */
	@Override
	public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) throws EntityNotFoundException {
		logger.info("updating the curve point");
		Optional<CurvePoint> curvePointFound = curvePointRepository.findById(id);
		if (curvePointFound.isEmpty()) {
			logger.error("The curve point id : " + id + ", you want to update, does not exist!");
			throw new EntityNotFoundException("The curve point id : " + id + ", you want to update, does not exist!");
		}
		logger.info("update curve point id : " + id);
		curvePointFound.get().setCurveId(curvePointFound.get().getCurveId());
		curvePointFound.get()
				.setTerm(curvePoint.getTerm() != null ? curvePoint.getTerm() : curvePointFound.get().getTerm());
		curvePointFound.get()
				.setValue(curvePoint.getValue() != null ? curvePoint.getValue() : curvePointFound.get().getValue());

		return curvePointRepository.saveAndFlush(curvePointFound.get());
	}

	/**
	 * get all curvePoints
	 * 
	 * @return list of all CurvePoints existing in the database
	 */
	@Override
	public List<CurvePoint> curvePointList() {
		logger.info("get list of Bids");
		return curvePointRepository.findAll();

	}

	/**
	 * Delete the CurvePoint ** This operation allows to check if the id of te
	 * CurvePoint we want to delete already exist in the database, then allows to
	 * use its id to delete it
	 * 
	 * @param id : id of the CurvePoint we want to delete
	 * @throws EntityNotFoundException
	 */
	@Override
	public boolean deleteCurvePoint(Integer id) throws EntityNotFoundException {
		logger.info("deleting a CurvePoint");
		Optional<CurvePoint> curvePointFound = curvePointRepository.findById(id);
		if (curvePointFound.isEmpty()) {
			logger.error("The curve point id : " + id + ", you want to delete, does not exist!");
			throw new EntityNotFoundException("The curve point id : " + id + ", you want to delete, does not exist!");
		}

		logger.info("delete the curve point id " + id);
		curvePointRepository.deleteById(id);
		return true;
	}

}
