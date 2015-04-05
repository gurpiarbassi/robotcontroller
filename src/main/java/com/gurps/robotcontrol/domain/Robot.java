package com.gurps.robotcontrol.domain;

/**
 * Interface to describe robot behavior
 * 
 * @author gurpiarbassi
 *
 */
public interface Robot {
	
	void executeCommand(RobotCommand command);
	
    Point getPosition() ;

    Direction getDirection();

}
