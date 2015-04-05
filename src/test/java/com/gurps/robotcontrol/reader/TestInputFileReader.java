package com.gurps.robotcontrol.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.Test;

import com.gurps.robotcontrol.domain.Point;
import com.gurps.robotcontrol.validation.InvalidInputException;

public class TestInputFileReader {

	@Test
	/**
	 * Test with valid data
	 */
	public void testSuccessfulRead() {
		URL resourceUrl = getClass().getResource("/SampleInput.txt");
		URI absolutePath;
		try {
			absolutePath = resourceUrl.toURI();
			InputFileReader reader = new InputFileReader(absolutePath.getPath());
			reader.execute();
			Queue<RobotCommandPair> instructions = reader.getInstructionQueue();
			Point gridMaxPoint = reader.getGridMaxPoint();
			assertEquals(new Point(5, 5), gridMaxPoint);
			assertEquals(2, instructions.size());
			
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			fail("Exception was caught");
		}
	}
	
	@Test
	/**
	 * Test with empty file
	 */
	public void testEmptyFile() {
		URL resourceUrl = getClass().getResource("/EmptyFile.txt");
		URI absolutePath;
		try {
			absolutePath = resourceUrl.toURI();
			InputFileReader reader = new InputFileReader(absolutePath.getPath());
			reader.execute();
			fail("Exception should have been caught");
			
		} catch (URISyntaxException | IOException e) {
			//e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * Test with file containing invalid grid max coordinates
	 */
	public void testInvalidGridSize() {
		URL resourceUrl = getClass().getResource("/InvalidGridSize.txt");
		URI absolutePath;
		try {
			absolutePath = resourceUrl.toURI();
			InputFileReader reader = new InputFileReader(absolutePath.getPath());
			reader.execute();
			fail("Exception should have been caught");
			
		} catch (URISyntaxException | IOException e) {
			fail("Should not be in here");
		} catch(InvalidInputException iie){
			//iie.printStackTrace();
		}
	}
	
	@Test
	/**
	 * Test with file containing invalid grid max coordinates
	 */
	public void testMissingCommands() {
		URL resourceUrl = getClass().getResource("/MissingInstructionLine.txt");
		URI absolutePath;
		try {
			absolutePath = resourceUrl.toURI();
			InputFileReader reader = new InputFileReader(absolutePath.getPath());
			reader.execute();
			fail("Exception should have been caught");
			
		} catch (URISyntaxException | IOException e) {
			fail("Should not be in here");
		} catch(InvalidInputException iie){
			fail("Should not be in here");
		} catch(NoSuchElementException nse){
			//nse.printStackTrace();
		}
	}
}
