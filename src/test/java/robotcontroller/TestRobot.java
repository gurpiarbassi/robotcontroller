package robotcontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.gurps.robocontrol.domain.Direction;
import com.gurps.robocontrol.domain.Grid;
import com.gurps.robocontrol.domain.GridIndexOutOfBoundsException;
import com.gurps.robocontrol.domain.Point;
import com.gurps.robocontrol.domain.Robot;
import com.gurps.robocontrol.domain.RobotFactory;

public class TestRobot {

	@Test
	/**
	 * LMLMLMLMM test case starting at (1,2) facing north
	 */
	public void testBasicScenario1() {
		Grid grid = Grid.getInstance();
		grid.init(0, 0, 5, 5);
		Robot robot = null;
		try {
			robot = RobotFactory.createRobot(1, 2, "N");
			robot.turnLeft();
			robot.forward();
			robot.turnLeft();
			robot.forward();
			robot.turnLeft();
			robot.forward();
			robot.turnLeft();
			robot.forward();
			robot.forward();
			assertEquals(new Point(1, 3), robot.getPosition());
			assertEquals(Direction.N, robot.getDirection());
		} catch (GridIndexOutOfBoundsException gioobe) {
			//gioobe.printStackTrace();
			fail("Exception caught");
		}

	}

	@Test
	/**
	 * MMRMMRMRRM test case starting at (3,3) facing east
	 */
	public void testBasicScenario2() {
		Grid grid = Grid.getInstance();
		grid.init(0, 0, 5, 5);
		Robot robot = null;
		try {
			robot = RobotFactory.createRobot(3, 3, "E");
			robot.forward();
			robot.forward();
			robot.turnRight();
			robot.forward();
			robot.forward();
			robot.turnRight();
			robot.forward();
			robot.turnRight();
			robot.turnRight();
			robot.forward();
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
		grid.init(0, 0, 5, 5);
		Robot robot = null;
		try {
			robot = RobotFactory.createRobot(3, 3, "E");
			robot.forward();
			robot.forward();
			robot.forward();
			robot.turnRight();
			fail("Exception should have been caught here since robot has moved off the grid.");
		} catch (GridIndexOutOfBoundsException gioobe) {
			//gioobe.printStackTrace();
			assertEquals(new Point(5, 3), robot.getPosition());
			assertEquals(Direction.E, robot.getDirection());
			assertEquals(6, gioobe.getX());
			assertEquals(3, gioobe.getY());
		}
	}

	@Test
	/**
	 * Test for robot that moves off the grid into the negative area
	 */
	public void testRobotMovesIntoNegativeArea() {
		Grid grid = Grid.getInstance();
		grid.init(0, 0, 5, 5);
		Robot robot = null;
		try {
			robot = RobotFactory.createRobot(3, 3, "N");
			robot.turnRight();
			robot.turnRight();
			robot.forward();
			robot.forward();
			robot.forward();
			robot.forward();
			fail("Exception should have been caught here since robot has moved off the grid.");
		} catch (GridIndexOutOfBoundsException gioobe) {
			//gioobe.printStackTrace();
			assertEquals(new Point(3, 0), robot.getPosition());
			assertEquals(Direction.S, robot.getDirection());
			assertEquals(3, gioobe.getX());
			assertEquals(-1, gioobe.getY());
		}
	}

	@Test
	/**
	 * Test for robot that is placed outside the grid to begin with.
	 */
	public void testRobotStartsOutsideGrid() {
		Grid grid = Grid.getInstance();
		grid.init(0, 0, 5, 5);
		Robot robot = null;
		try {
			robot = RobotFactory.createRobot(10, 10, "N");
			fail("Exception should have been caught here since robot has started outside the grid area.");
			robot.turnRight();
			robot.turnRight();
			robot.forward();
			robot.forward();
			robot.forward();
			robot.forward();
		} catch (GridIndexOutOfBoundsException gioobe) {
			//gioobe.printStackTrace();
			assertNull(robot);
			assertEquals(10, gioobe.getX());
			assertEquals(10, gioobe.getY());
		}
	}

	@Test
	/**
	 * Test for a grid that has size 0. 
	 */
	public void testGridSizeZero() {
		Grid grid = Grid.getInstance();

		try {
			grid.init(0, 0);
			fail("Exception should have been caught here since robot has started outside the grid area.");
		} catch (IllegalArgumentException iae) {
			//iae.printStackTrace();
		}
	}

}
