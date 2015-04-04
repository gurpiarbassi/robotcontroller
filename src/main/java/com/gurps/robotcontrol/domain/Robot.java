package com.gurps.robotcontrol.domain;

/**
 * Interface to describe robot behavior
 * 
 * @author gurpiarbassi
 *
 */
public interface Robot {

	void turnLeft();

	void turnRight();

	void forward();
	
    Point getPosition() ;

    Direction getDirection();

}
