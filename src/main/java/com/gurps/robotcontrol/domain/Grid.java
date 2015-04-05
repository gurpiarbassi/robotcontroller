package com.gurps.robotcontrol.domain;

/**
 * Represents a 2 dimensional mathematical grid object with an x and y axis.
 * This is a singleton. After getting an instance of the singleton, you can
 * invoke one of the init() methods to initialise it with specific starting
 * points. The default instance is a 5 x 5 grid.
 * 
 * @author gurpiarbassi
 *
 */
public final class Grid {

	Point gridMinPoint;
	Point gridMaxPoint;

	private static Grid INSTANCE = null;
	private static final Point DEFAULT_GRID_MIN = new Point(0, 0);
	private static final Point DEFAULT_GRID_MAX = new Point(5, 5);

	public static Grid getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Grid();
		}
		return INSTANCE;
	}

	private Grid() {
		init(DEFAULT_GRID_MIN, DEFAULT_GRID_MAX);
	}

	/**
	 * Creates a grid object with the supplied parameters. The parameters
	 * indicate the furthest points along the x and y axis. The lowest points
	 * are defaulted to (0,0).
	 * 
	 * @param gridMaxPoint
	 *            The furthest point along the x axis and the furthest point along the y axis
	 */
	public void init(Point gridMaxPoint) {
		if (gridMaxPoint.getX() <= 0 || gridMaxPoint.getY() <= 0
				|| gridMaxPoint.getX() > Integer.MAX_VALUE
				|| gridMaxPoint.getY() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(
					"maxX and maxY must be greater than 0 and a valid integer");
		}
		init(DEFAULT_GRID_MIN, gridMaxPoint);
	}

	/**
	 * Creates a grid given all 4 upper and lower bound points. Using this
	 * constructor it is possible to have a grid that covers the negative side
	 * of the x and y axis.
	 * 
	 * @param gridMinPoint
	 *            The minimum x and y coordinates of the grid
     * @param gridMaxPoint
	 *            The maximum x and y coordinates of the grid
	 *       
	 */
	public void init(Point gridMinPoint, Point gridMaxPoint) {
		if (gridMaxPoint.getX() < gridMinPoint.getX() || gridMaxPoint.getY() < gridMinPoint.getY()
				|| gridMaxPoint.getX() > Integer.MAX_VALUE
				|| gridMaxPoint.getY() > Integer.MAX_VALUE
				|| gridMinPoint.getX() < Integer.MIN_VALUE
				|| gridMinPoint.getY() < Integer.MIN_VALUE) {
			throw new IllegalArgumentException("Invalid grid dimensions");
		}
		this.gridMinPoint = gridMinPoint;
		this.gridMaxPoint = gridMaxPoint;
	}

	/**
	 * Check to see if the given coordinates are valid within the bounds of the
	 * grid.
	 * 
	 * @param point
	 *            The current position to validate
	 * @return True if the position is within the bounds of the grid. False if
	 *         the position has exceeded the bounds of the grid.
	 * 
	 */
	public boolean isValidPosition(Point point) {
		if (point.getX() > this.gridMaxPoint.getX() || point.getX() < this.gridMinPoint.getX() || point.getY() < this.gridMinPoint.getY()
				|| point.getY() > this.gridMaxPoint.getY()) {
			return false;
		}
		return true;
	}
}
