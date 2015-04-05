package com.gurps.robotcontrol.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestRobot {

	@Test
	/**
	 * LMLMLMLMM test case starting at (1,2) facing north
	 */
	public void testBasicScenario1() {
		Grid grid = Grid.getInstance();
		grid.init(new Point(0,0), new Point(5,5));
		Robot robot = null;
		String commands = "LMLMLMLMM";
		try {
			robot = RobotFactory.createRobot(1, 2, "N");
			for (int i = 0; i < commands.length(); i++) {
				robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(i))));
			}
			assertEquals(new Point(1, 3), robot.getPosition());
			assertEquals(Direction.N, robot.getDirection());
		} catch (GridIndexOutOfBoundsException gioobe) {
			// gioobe.printStackTrace();
			fail("Exception caught");
		}

	}

	@Test
	/**
	 * MMRMMRMRRM test case starting at (3,3) facing east
	 */
	public void testBasicScenario2() {
		Grid grid = Grid.getInstance();
		grid.init(new Point(0,0), new Point(5,5));
		Robot robot = null;
		String commands = "MMRMMRMRRM";
		try {
			robot = RobotFactory.createRobot(3, 3, "E");
			for (int i = 0; i < commands.length(); i++) {
				robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(i))));
			}
			assertEquals(new Point(5, 1), robot.getPosition());
			assertEquals(Direction.E, robot.getDirection());
		} catch (GridIndexOutOfBoundsException gioobe) {
			gioobe.printStackTrace();
			fail("Exception caught");
		}
	}

	@Test
	/**
	 * Test for robot that moves off the grid along the positive axis
	 */
	public void testRobotMovesOffGrid() {
		Grid grid = Grid.getInstance();
		grid.init(new Point(0,0), new Point(5,5));
		Robot robot = null;
		String commands = "MMMR";
		try {
			robot = RobotFactory.createRobot(3, 3, "E");
			for (int i = 0; i < commands.length(); i++) {
				robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(i))));
			}
			fail("Exception should have been caught here since robot has moved off the grid.");
		} catch (GridIndexOutOfBoundsException gioobe) {
			// gioobe.printStackTrace();
			assertEquals(new Point(5, 3), robot.getPosition());
			assertEquals(Direction.E, robot.getDirection());
			assertEquals(6, gioobe.getOutOfBoundsPoint().getX());
			assertEquals(3, gioobe.getOutOfBoundsPoint().getY());
		}
	}

	@Test
	/**
	 * Test for robot that moves off the grid into the negative area
	 */
	public void testRobotMovesIntoNegativeArea() {
		Grid grid = Grid.getInstance();
		grid.init(new Point(0,0), new Point(5,5));
		Robot robot = null;
		String commands = "RRMMMM";
		try {
			robot = RobotFactory.createRobot(3, 3, "N");
			for (int i = 0; i < commands.length(); i++) {
				robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(i))));
			}
			fail("Exception should have been caught here since robot has moved off the grid.");
		} catch (GridIndexOutOfBoundsException gioobe) {
			// gioobe.printStackTrace();
			assertEquals(new Point(3, 0), robot.getPosition());
			assertEquals(Direction.S, robot.getDirection());
			assertEquals(3, gioobe.getOutOfBoundsPoint().getX());
			assertEquals(-1, gioobe.getOutOfBoundsPoint().getY());
		}
	}

	@Test
	/**
	 * Test for robot that is placed outside the grid to begin with.
	 */
	public void testRobotStartsOutsideGrid() {
		Grid grid = Grid.getInstance();
		grid.init(new Point(0,0), new Point(5,5));
		Robot robot = null;
		String commands = "RRMMMM";
		try {
			robot = RobotFactory.createRobot(10, 10, "N");
			fail("Exception should have been caught here since robot has started outside the grid area.");
			for (int i = 0; i < commands.length(); i++) {
				robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(i))));
			}
		} catch (GridIndexOutOfBoundsException gioobe) {
			// gioobe.printStackTrace();
			assertNull(robot);
			assertEquals(10, gioobe.getOutOfBoundsPoint().getX());
			assertEquals(10, gioobe.getOutOfBoundsPoint().getY());
		}
	}

	@Test
	/**
	 * Test for a grid that has size 0. 
	 */
	public void testGridSizeZero() {
		Grid grid = Grid.getInstance();

		try {
			grid.init(new Point(0,0));
			fail("Exception should have been caught here since robot has started outside the grid area.");
		} catch (IllegalArgumentException iae) {
			// iae.printStackTrace();
		}
	}

}
