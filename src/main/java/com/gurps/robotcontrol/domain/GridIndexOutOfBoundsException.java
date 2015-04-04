package com.gurps.robotcontrol.domain;

public class GridIndexOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private String message;
	
	public GridIndexOutOfBoundsException(final int x, final int y, final String message) {
		this.x = x;
		this.y = y;
		this.message = message;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String getMessage(){
		return String.format("Invalid input: [%s,%s] - %s", x, y, message);
	}
	
}
