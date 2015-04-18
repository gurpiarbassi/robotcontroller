package com.gurps.robotcontrol.reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.gurps.robotcontrol.domain.Point;
import com.gurps.robotcontrol.validation.InvalidInputException;
import com.gurps.robotcontrol.validation.RobotInputValidator;

public class InputFileReader {

	private String inputFilename;
	private Point gridMaxPoint;
	
	private Queue<RobotCommandPair> instructionQueue = new LinkedList<RobotCommandPair>();

	public InputFileReader(String inputFilename) {
		this.inputFilename = inputFilename;
	}

	/**
	 * Executes record reading by parsing and validating the file and setting
	 * attributes on this object for use by the caller;
	 * 
	 * @exception InvalidInputException
	 *                if any input validation error occurs IOException if there
	 *                is a problem reading the file
	 */
	public void execute() throws IOException {

		try (Scanner scanner = new Scanner(Paths.get(inputFilename), Charset.defaultCharset()
				.name())) {

			if (scanner.hasNextLine()) {
				String firstLine = scanner.nextLine();
				if (!RobotInputValidator.isValidGridMaxPoint(firstLine)) {
					throw new InvalidInputException(firstLine,
							"Invalid Max point in line 1 of input!");
				}

				String[] tokens = firstLine.split(" ");
				int xGridMax = Integer.valueOf(tokens[0]);
				int yGridMax = Integer.valueOf(tokens[1]);
				this.gridMaxPoint = new Point(xGridMax, yGridMax);

				while (scanner.hasNextLine()) {
					// first robot line is the initial position on the grid e.g.
					// 1 2 N
					String positionInput = scanner.nextLine();
					String commandInput = scanner.nextLine();
					
					if (!RobotInputValidator.isValidInitialPosition(positionInput)) {
						throw new InvalidInputException(positionInput, "Invalid initial position!");
					} else if (!RobotInputValidator.isValidCommandString(commandInput)) {
						throw new InvalidInputException(commandInput, "Invalid command string!");
					}
					
					RobotCommandPair rcPair = new RobotCommandPair(positionInput, commandInput);
					instructionQueue.offer(rcPair);
				}
			}else{
				throw new IOException("File contains no data");
			}
		}
	}

	
	public Point getGridMaxPoint() {
		return gridMaxPoint;
	}

	public Queue<RobotCommandPair> getInstructionQueue() {
		return instructionQueue;
	}

}
