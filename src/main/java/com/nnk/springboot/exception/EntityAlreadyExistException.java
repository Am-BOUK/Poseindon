package com.nnk.springboot.exception;

/**
 * The EntityAlreadyExistException class that extends PoseidonException
 *
 */
public class EntityAlreadyExistException extends PoseidonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistException(String message) {
		super(message);
	}

}
