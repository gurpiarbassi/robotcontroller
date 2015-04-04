package com.gurps.robotcontrol;

import com.gurps.robotcontrol.reader.InputFileReader;

/**
 * Main class for execution. Reads the robot commands as a single file input.
 * @author gurpiarbassi
 *
 */
public class RobotController {

	/**
	 * It is assumed that the file needs to be completely parsed first before 
	 * @param args contains the absolute filepath for the robot instructions
	 * @exception Exception thrown if any problem occurs in parsing the input file
	 * 
	 */
	public static void main(String[] args) throws Exception{
		try {
			System.out.println("Starting Rover Program...");
			
			if(args.length <= 0) {
				System.out.println("Error: Please enter a valid file absolute filename");
				return;
			}
			String inputFilename = args[0];	
			InputFileReader reader = new InputFileReader(inputFilename);
			reader.execute();
		} finally {
			System.out.println("Ending Rover Program...");
		}
	}
}
