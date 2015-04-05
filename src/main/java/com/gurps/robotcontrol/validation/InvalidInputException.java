package com.gurps.robotcontrol.validation;

/**
 * Exception class to indicate input validation problems
 * 
 * @author gurpiarbassi
 *
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = -8183236610259236080L;

	private String input;

	private String message;

	public InvalidInputException(String input, String message) {
		this.message = message;
		this.input = input;

	}

	@Override
	public String getMessage() {
		return String.format("Invalid input: [%s] - %s", input, message);
	}

}
