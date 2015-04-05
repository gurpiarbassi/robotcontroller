package com.gurps.robotcontrol.domain;

/**
 * Custom exception to indicate Grid perimeter has been breached.
 * @author gurpiarbassi
 *
 */
public class GridIndexOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	Point outOfBoundsPoint;
	private String message;

	public GridIndexOutOfBoundsException(final Point outOfBoundsPoint, final String message) {
		this.outOfBoundsPoint = outOfBoundsPoint;
		this.message = message;
	}

	public Point getOutOfBoundsPoint() {
		return outOfBoundsPoint;
	}

	public String getMessage() {
		return String.format("Invalid input: [%s,%s] - %s", outOfBoundsPoint.getX(), outOfBoundsPoint.getY(), message);
	}

}
