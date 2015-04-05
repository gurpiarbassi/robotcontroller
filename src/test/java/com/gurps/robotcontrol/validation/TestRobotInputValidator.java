package com.gurps.robotcontrol.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRobotInputValidator {
	
	@Test
	/**
	 * Test to ensure the command string is correctly validated
	 */
	public void testValidCommandString() {
		boolean result = RobotInputValidator.isValidCommandString("RRLLMMMM");
		assertTrue(result);
	}
	
	@Test
	/**
	 * Test to ensure that an empty command sring fails validation
	 */
	public void testEmptyCommandString() {
		boolean result = RobotInputValidator.isValidCommandString("");
		assertFalse(result);
	}
	
	@Test
	/**
	 * Test to ensure invalid commands are caught
	 */
	public void testInValidCommandString() {
		boolean result = RobotInputValidator.isValidCommandString("LLRBMMMM");
		assertFalse(result);
	}
	
	@Test
	/**
	 * Test to ensure spaces not allowed in command string
	 */
	public void testSpaceInCommandString() {
		boolean result = RobotInputValidator.isValidCommandString("LLRM MMMM");
		assertFalse(result);
	}
	
	@Test
	/**
	 * Test to ensure a valid initial position is accepted
	 */
	public void testValidInitialPosition() {
		boolean result = RobotInputValidator.isValidInitialPosition("1 5 E");
		assertTrue(result);
	}
	
	@Test
	/**
	 * Test to ensure a invalid direction in the initial position is rejected in validation
	 */
	public void testInvalidDirectionInInitialPosition() {
		boolean result = RobotInputValidator.isValidInitialPosition("1 5 G");
		assertFalse(result);
	}
	
	@Test
	/**
	 * Test to ensure the initial position x and y coordinates are integers
	 */
	public void testInvalidInitialPositionCoordinates() {
		boolean result = RobotInputValidator.isValidInitialPosition("A B G");
		assertFalse(result);
	}
	
	@Test
	/**
	 * Test to ensure the grid max point is valid
	 */
	public void testValidGridMaxPoint() {
		boolean result = RobotInputValidator.isValidGridMaxPoint("5 5");
		assertTrue(result);
	}
	
	@Test
	/**
	 * Test to ensure the grid max point is valid
	 */
	public void testInvalidCharactersInGridMaxPoint() {
		boolean result = RobotInputValidator.isValidGridMaxPoint("5 X");
		assertFalse(result);
	}

}
