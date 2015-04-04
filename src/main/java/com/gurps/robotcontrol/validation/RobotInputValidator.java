package com.gurps.robotcontrol.validation;

import com.gurps.robotcontrol.domain.Direction;
import com.gurps.robotcontrol.domain.RobotCommand;

/**
 * Validation utility to validate input string.
 * @author gurpiarbassi
 *
 */
public class RobotInputValidator {

	private static String REGEX_GRID_MAX_SETTING = "^[0-9]+ [0-9]+$";
	
	public static final String REGEX_INITIAL_POSITION = buildInitialPositionRegex();	
	public static final String REGEX_ACCEPTABLE_COMMANDS = buildCommandsRegex();
	
	
	/**
	 * Construct the regex dynamically based on the enum names
	 * @return regex used to validate the initial robot position
	 */
	private static final String buildInitialPositionRegex() {
		StringBuilder regex = new StringBuilder();
		regex.append("^[0-9]+ [0-9]+ [");
		regex.append(Direction.N.name());
		regex.append(Direction.S.name());
		regex.append(Direction.E.name());
		regex.append(Direction.W.name());
		regex.append("]?$");
		return regex.toString();
	}
	
	
	/**
	 * Construct the regex dynamically based on the enum names
	 * @return regex used to validate the command string
	 */
	private static final String buildCommandsRegex() {
		StringBuilder regex = new StringBuilder();
		regex.append("^[");
		regex.append(RobotCommand.L.name());
		regex.append(RobotCommand.R.name());
		regex.append(RobotCommand.M.name());
		regex.append("]*$");
		return regex.toString();
	}
	
	
	public static boolean isValidGridMaxPoint(String gridMaxPoint){
		if(!isEmpty(gridMaxPoint)){
			return gridMaxPoint.matches(REGEX_GRID_MAX_SETTING);
		}
		return false;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		return false;
	}	}

