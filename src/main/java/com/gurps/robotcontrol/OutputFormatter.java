package com.gurps.robotcontrol;

import com.gurps.robotcontrol.domain.Direction;
import com.gurps.robotcontrol.domain.Point;

public class OutputFormatter {

	public static String format(Point position, Direction direction){
		return position.getX() + " " + position.getY() + " " + direction.name();
	}
}
