package com.gurps.robotcontrol.domain;

/**
 * Interface to describe robot behavior
 * 
 * @author gurpiarbassi
 *
 */
public interface Robot {

	/**
	 * Executes a command
	 * @param command the command to execute
	 */
	void executeCommand(RobotCommand command);

	/**
	 * Gets the current position of the robot
	 * @return a Point describing the x and y position of the robot
	 */
	Point getPosition();

	/**
	 * Gets the current direction / orientation of the robot
	 * @return Direction indicating direction of the robot
	 */
	Direction getDirection();

}
