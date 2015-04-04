package com.gurps.robocontrol.domain;

import javax.validation.constraints.NotNull;

/**
 * Factory class for Robot creation
 * @author gurpiarbassi
 *
 */
public class RobotFactory {

	/**
	 * Creates a robot with the x,y starting position and the given direction string
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param direction a string that cannot be null and must be either N,E,S or W
	 * @return a Robot initialised for use
	 */
	public static Robot createRobot(int xCoordinate, int yCoordinate, @NotNull String direction){
		return new RobotImpl(new Point(xCoordinate, yCoordinate), Direction.valueOf(direction));
	}
	
	/**
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param direction a valid Direction
	 * @return a Robot initialised for use
	 */
	public static Robot createRobot(int xCoordinate, int yCoordinate, Direction direction){
		return new RobotImpl(new Point(xCoordinate, yCoordinate), direction);
	}
}
