package com.nnk.springboot.exception;

/**
 * The EntityNotFoundException class that extends PoseidonException
 *
 */
public class EntityNotFoundException extends PoseidonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
