package com.gurps.robotcontrol.domain;

/**
 * Concrete implementation of Robot interface with Grid traversal behavior
 * 
 * @author gurpiarbassi
 *
 */

public class RobotImpl implements Robot {

	private Point position;
	private Grid grid;
	private Direction direction;

	/**
	 * Creates a robot with a starting position and a starting direction
	 * 
	 * @param position
	 *            the current position of the robot
	 * @param direction
	 *            the current direction the robot is facing
	 */
	public RobotImpl(final Point initialPosition, final Direction direction) {
		super();
		grid = Grid.getInstance();
		this.position = initialPosition;
		this.direction = direction;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	private void turnLeft() {
		direction = direction.left();
	}

	private void turnRight() {
		direction = direction.right();

	}

	/**
	 * This method moves the robot forward one square in the direction it is
	 * facing.
	 * 
	 * @exception GridIndexOutOfBoundsException
	 *                if the robot 'could' go off the grid with this movement
	 *                forward.
	 **/
	private void forward() {
		switch (direction) {
		case N:
			if (grid.isValidPosition(new Point(position.getX(), position.getY() + 1))) {
				position.plusY(1);
			} else {
				throw new GridIndexOutOfBoundsException(new Point(position.getX(), position.getY() + 1),
						"Moving this way will take you to (" + position.getX() + ","
								+ (position.getY() + 1) + ")");
			}
			break;
		case E:
			if (grid.isValidPosition(new Point(position.getX() + 1, position.getY()))) {
				position.plusX(1);
			} else {
				throw new GridIndexOutOfBoundsException(new Point(position.getX() + 1, position.getY()),
						"Moving this way will take you to (" + (position.getX() + 1) + ","
								+ position.getY() + ")");
			}
			break;
		case S:
			if (grid.isValidPosition(new Point(position.getX(), position.getY() - 1))) {
				position.plusY(-1);
			} else {
				throw new GridIndexOutOfBoundsException(new Point(position.getX(), position.getY() - 1),
						"Moving this way will take you to (" + position.getX() + ","
								+ (position.getY() - 1) + ")");
			}
			break;
		case W:
			if (grid.isValidPosition(new Point(position.getX() - 1, position.getY()))) {
				position.plusX(-1);
			} else {
				throw new GridIndexOutOfBoundsException(new Point(position.getX() - 1, position.getY()),
						"Moving this way will take you to (" + (position.getX() - 1) + ","
								+ position.getY() + ")");
			}
			break;
		}

	}

	@Override
	/**
	 * Executes a single command
	 * @param command A RobotCommand
	 */
	public void executeCommand(RobotCommand command) {
		switch (command) {
		case L:
			turnLeft();
			break;

		case R:
			turnRight();
			break;

		case M:
			forward();
			break;

		default:
			throw new UnsupportedOperationException("operation " + command.name()
					+ " is not supported by this robot implementation");
		}
	}
}