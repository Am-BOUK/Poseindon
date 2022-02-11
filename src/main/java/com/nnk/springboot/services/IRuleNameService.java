package com.nnk.springboot.services;

import java.util.List;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exception.EntityNotFoundException;

/**
 * 
 * business interface that will allow implementation of application-specific
 * business processing
 * 
 * The important part is, the return value from operations which is a RuleName
 * object
 * 
 * It is used to interact with the database, defining method related to the
 * RuleName entity. Then is called/autowired in a controller layer.
 */
public interface IRuleNameService {

	public RuleName getRuleName(Integer id) throws EntityNotFoundException;

	public RuleName addNewRuleName(RuleName ruleName);

	public RuleName updateRuleName(Integer id, RuleName ruleName) throws EntityNotFoundException;

	public List<RuleName> ruleNameList();

	public boolean deleteRuleName(Integer id) throws EntityNotFoundException;

}
