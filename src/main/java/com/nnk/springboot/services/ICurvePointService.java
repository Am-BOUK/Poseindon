package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.EntityAlreadyExistException;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a CurvePoint
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * CurvePoint entity. Then is called/autowired in a controller layer.
 */
public interface ICurvePointService {

	public CurvePoint getCurvePoint(Integer id) throws EntityNotFoundException;

	public CurvePoint addCurvePoint(CurvePoint curvePoint) throws EntityAlreadyExistException;

	public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) throws EntityNotFoundException;

	public List<CurvePoint> curvePointList();

	public boolean deleteCurvePoint(Integer id) throws EntityNotFoundException;

}
