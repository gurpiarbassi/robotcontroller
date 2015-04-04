package com.gurps.robocontrol.domain;

/**
 * Interface to describe robot behavior
 * 
 * @author gurpiarbassi
 *
 */
public interface Robot {

	void turnLeft();

	void turnRight();

	void forward() throws GridIndexOutOfBoundsException;
	
    Point getPosition() ;

    Direction getDirection();

}
