package com.gurps.robocontrol.domain;


/**
 * Concrete implementation of Robot interface with Grid traversal behaviour
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
	public RobotImpl(final Point position, final Direction direction) {
		super();
		grid = Grid.getInstance();
		this.position = position;
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

	@Override
	public void turnLeft() {
		switch (direction) {
		case N:
			direction = Direction.W;
			break;

		case E:
			direction = Direction.N;
			break;

		case S:
			direction = Direction.E;
			break;

		case W:
			direction = Direction.S;
			break;
		}
	}

	@Override
	public void turnRight() {
		switch (direction) {
		case N:
			direction = Direction.E;
			break;

		case E:
			direction = Direction.S;
			break;

		case S:
			direction = Direction.W;
			break;

		case W:
			direction = Direction.N;
			break;
		}

	}

	@Override
	public void forward() throws GridIndexOutOfBoundsException {
		switch (direction) {
		case N:
			if (grid.isValidPosition(position.getX(), position.getY() + 1)) {
				position.plusY(1);
			} else {
				throw new GridIndexOutOfBoundsException(position.getX(), position.getY() + 1, "Moving this way will take you to ("
						+ position.getX() + "," + (position.getY() + 1) + ")");
			}
			break;
		case E:
			if (grid.isValidPosition(position.getX() + 1, position.getY())) {
				position.plusX(1);
			} else {
				throw new GridIndexOutOfBoundsException(position.getX() + 1, position.getY(), "Moving this way will take you to ("
						+ (position.getX() + 1) + "," + position.getY() + ")");
			}
			break;
		case S:
			if (grid.isValidPosition(position.getX(), position.getY() - 1)) {
				position.plusY(-1);
			} else {
				throw new GridIndexOutOfBoundsException(position.getX(), position.getY() - 1, "Moving this way will take you to ("
						+ position.getX() + "," + (position.getY() - 1) + ")");
			}
			break;
		case W:
			if (grid.isValidPosition(position.getX() - 1, position.getY())) {
				position.plusX(-1);
			} else {
				throw new GridIndexOutOfBoundsException(position.getX() - 1, position.getY(), "Moving this way will take you to ("
						+ (position.getX() - 1) + "," + position.getY() + ")");
			}
			break;
		}

	}
}