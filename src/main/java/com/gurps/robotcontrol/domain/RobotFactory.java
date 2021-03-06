package com.gurps.robotcontrol.domain;


/**
 * Factory class for Robot creation
 * 
 * @author gurpiarbassi
 *
 */
public class RobotFactory {

	/**
	 * Creates a robot with the x,y starting position and the given direction
	 * string
	 * 
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param direction
	 *            a string that cannot be null and must be either N,E,S or W
	 * @return a Robot initialised for use
	 * @exception GridIndexOutOfBoundsException
	 *                is the robot is created outside the grid area
	 */
	public static Robot createRobot(int xCoordinate, int yCoordinate, String direction) {
		Grid grid = Grid.getInstance();
		Point point = new Point(xCoordinate, yCoordinate);
		if (!grid.isValidPosition(point)) {
			throw new GridIndexOutOfBoundsException(point,
					"Robot must be created to start at a valid position");
		}
		return new RobotImpl(point, Direction.valueOf(direction));
	}

	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param direction
	 *            a valid Direction
	 * @return a Robot initialised for use
	 * @exception GridIndexOutOfBoundsException
	 *                is the robot is created outside the grid area
	 */
	public static Robot createRobot(int xCoordinate, int yCoordinate, Direction direction) {
		Grid grid = Grid.getInstance();
		Point point = new Point(xCoordinate, yCoordinate);
		if (!grid.isValidPosition(point)) {
			throw new GridIndexOutOfBoundsException(point,
					"Robot must be created to start at a valid position");
		}
		return new RobotImpl(point, direction);
	}
}
