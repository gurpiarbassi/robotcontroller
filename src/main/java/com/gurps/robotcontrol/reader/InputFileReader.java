package com.gurps.robotcontrol.reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.validation.constraints.NotNull;

import com.gurps.robotcontrol.validation.InvalidInputException;
import com.gurps.robotcontrol.validation.RobotInputValidator;

public class InputFileReader {

	private String inputFilename;
	private int xGridMax;
	private int yGridMax;
	
	public InputFileReader(@NotNull String inputFilename) {
		this.inputFilename = inputFilename;
	}

	/**
	 * Executes record reading by parsing and validating the file and setting 
	 * attributes on this object for use by the caller;
	 * @exception InvalidInputException if any input validation error occurs
	 *            IOException if there is a problem reading the file
	 */
	public void execute() throws IOException{
	        
	        try (Scanner scanner = new Scanner(Paths.get(inputFilename), Charset.defaultCharset()
	                .name())) {
	            
	            if (scanner.hasNextLine()) {
	                String firstLine = scanner.nextLine();
	                if(!RobotInputValidator.isValidGridMaxPoint(firstLine)){
	                		throw new InvalidInputException(firstLine, "Invalid Max point in line 1 of input!");
	                }
	                
	                String[] tokens = firstLine.split(" ");
            		this.xGridMax = Integer.valueOf(tokens[0]);
            		this.yGridMax = Integer.valueOf(tokens[1]);
	                
	                while (scanner.hasNextLine()) {
	                	//first robot line is the initial position on the grid e.g. 1 2 N
	                }
	            }
	        }
	    }

	public int getxGridMax() {
		return xGridMax;
	}

	public int getyGridMax() {
		return yGridMax;
	}

	
}

