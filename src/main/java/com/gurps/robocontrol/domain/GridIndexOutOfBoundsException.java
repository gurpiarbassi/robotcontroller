package com.gurps.robocontrol.domain;

public class GridIndexOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	
	public GridIndexOutOfBoundsException(final int x, final int y, final String message) {
		super(message);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
