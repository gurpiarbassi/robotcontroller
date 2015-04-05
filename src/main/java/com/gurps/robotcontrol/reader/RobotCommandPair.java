package com.gurps.robotcontrol.reader;

/**
 * Captures 2 line pairs of the input file. One line being the robots initial
 * position and the second line being the robots commands
 * 
 * @author gurpiarbassi
 *
 */
public class RobotCommandPair {

	private String robotPosition;
	private String commands;

	public RobotCommandPair(String robotPosition, String commands) {
		super();
		this.robotPosition = robotPosition;
		this.commands = commands;
	}

	public String getRobotPosition() {
		return robotPosition;
	}

	public String getCommands() {
		return commands;
	}

}
