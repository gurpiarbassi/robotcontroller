package com.gurps.robotcontrol;

import java.io.IOException;
import java.util.Queue;

import com.gurps.robotcontrol.domain.Direction;
import com.gurps.robotcontrol.domain.Grid;
import com.gurps.robotcontrol.domain.Point;
import com.gurps.robotcontrol.domain.Robot;
import com.gurps.robotcontrol.domain.RobotCommand;
import com.gurps.robotcontrol.domain.RobotFactory;
import com.gurps.robotcontrol.reader.InputFileReader;
import com.gurps.robotcontrol.reader.RobotCommandPair;

/**
 * Main class for execution. Reads the robot commands as a single file input.
 * 
 * @author gurpiarbassi
 *
 */
public class RobotController {

	/**
	 * It is assumed that the file needs to be completely parsed first before
	 * 
	 * @param args
	 *            contains the absolute file path for the robot instructions
	 * @throws IOException if there is a problem reading the input file
	 * @exception Exception
	 *                thrown if any problem occurs in parsing the input file
	 * 
	 */
	public static void main(String[] args) throws IOException {
		//try {
			System.out.println("Starting Robot Program...");

			if (args.length <= 0) {
				System.out.println("Error: Please enter a valid file absolute filename");
				return;
			}
			String inputFilename = args[0];

			// read the input file and extract out data objects
			InputFileReader reader = new InputFileReader(inputFilename);
			reader.execute();

			Point gridMaxPoint = reader.getGridMaxPoint();
			Queue<RobotCommandPair> instructionQueue = reader.getInstructionQueue();

			Grid grid = Grid.getInstance();
			grid.init(gridMaxPoint);

			for (int i = 0; i <= instructionQueue.size(); i++) {
				RobotCommandPair rcPair = instructionQueue.poll();
				// create a robot
				String[] positionTokens = rcPair.getRobotPosition().split(" ");
				String commands = rcPair.getCommands();
				Robot robot = RobotFactory.createRobot(Integer.valueOf(positionTokens[0]),
						Integer.valueOf(positionTokens[1]), positionTokens[2]);

				for (int j = 0; j < commands.length(); j++) {
					robot.executeCommand(RobotCommand.valueOf(String.valueOf(commands.charAt(j))));
				}

				Point finalPosition = robot.getPosition();
				Direction finalDirection = robot.getDirection();
				System.out.println(OutputFormatter.format(finalPosition, finalDirection));

			}
			System.out.println("Ending Robot Program...");
	}
}
