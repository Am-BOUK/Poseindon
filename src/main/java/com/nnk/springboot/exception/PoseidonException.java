package com.nnk.springboot.exception;

/**
 * The Class PoseidonException that extends Exception class.
 */
public class PoseidonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Instantiates a new poseidon Exception
	 * 
	 * @param message : the message that contains exception message
	 */
	public PoseidonException(String message) {
		super();
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
