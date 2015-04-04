package com.gurps.robocontrol.domain;

/**
 * Represents a 2 dimensional mathematical grid object with an x and y axis. This
 * is a singleton. After getting an instance of the singleton, you can invoke
 * one of the init() methods to initialise it with specific starting points.
 * The default instance is a 5 x 5 grid.
 * @author gurpiarbassi
 *
 */
public final class Grid {

	private int maxX;
	private int minX;
	private int maxY;
	private int minY;

	private static Grid INSTANCE = null;

	public static Grid getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Grid();
		}
		return INSTANCE;
	}

	private Grid() {
		init(0, 0, 5, 5);
	}

	/**
	 * Creates a grid object with the supplied parameters. The parameters
	 * indicate the furthest points along the x and y axis. The lowest points
	 * are defaulted to (0,0).
	 * 
	 * @param maxX
	 *            The furthest point along the x axis
	 * @param maxY
	 *            The furthest point along the y axis
	 */
	public void init(int maxX, int maxY) {
		if(maxX <=0 || maxY<=0 || maxX > Integer.MAX_VALUE || maxY > Integer.MAX_VALUE){
			throw new IllegalArgumentException("maxX and maxY must be greater than 0 and a valid integer");
		}
		init(0, 0, maxX, maxY);
	}

	/**
	 * Creates a grid given all 4 upper and lower bound points.
	 * Using this constructor it is possible to have a grid that covers the negative side of the x and y axis.
	 * 
	 * @param minX
	 *            The starting point of the x axis
	 * @param minY
	 *            The starting point of the y axis
	 * @param maxX
	 *            The furthest point along the x axis
	 * @param maxY
	 *            The furthest point along the y axis
	 */
	public void init(int minX, int minY, int maxX, int maxY) {
		if(maxX < minX || maxY < minY || maxX > Integer.MAX_VALUE || maxY > Integer.MAX_VALUE || minX < Integer.MIN_VALUE || minY < Integer.MIN_VALUE){
			throw new IllegalArgumentException("Invalid grid dimensions");
		}
		this.maxX = maxX;
		this.maxY = maxY;
		this.minX = minX;
		this.minY = minY;
	}

	/**
	 * Check to see if the given coordinates are valid within the bounds of the
	 * grid.
	 * 
	 * @param xPosition
	 *            The position along the x axis
	 * @param yPosition
	 *            The position along the y axis
	 * @return True if the position is within the bounds of the grid. False if
	 *         the position has exceeded the bounds of the grid.
	 * 
	 */
	public boolean isValidPosition(int xPosition, int yPosition) {
		if (xPosition > this.maxX || xPosition < this.minX || yPosition < this.minY
				|| yPosition > this.maxY) {
			return false;
		}
		return true;
	}
}
