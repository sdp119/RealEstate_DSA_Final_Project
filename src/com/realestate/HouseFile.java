package com.realestate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class HouseFile {
	
	private static BufferedReader inputFileBufferReader;
	private static PrintWriter outPutFilePrintWriter;
	private static boolean isInputFileOpen = false;
	private static boolean isOutPutFileOpen = false;
	private static String inputString = "";// Holds "next" line from file
											// Equals null if at end of file

	// Reset file for reading
	public static void reset()
	{
		try {
			if (isInputFileOpen)
			{
				inputFileBufferReader.close();
			}
			if (isOutPutFileOpen)
			{
				outPutFilePrintWriter.close();
			}

			inputFileBufferReader = new BufferedReader(new FileReader("houses.dat"));
			isInputFileOpen = true;
			inputString = inputFileBufferReader.readLine();

		} catch (Exception exception) {

			exception.printStackTrace();

		}

	}

	public static void rewrite() // Reset file for writing
	{
		try {
			if (isInputFileOpen)
			{
				inputFileBufferReader.close();
			}
			if (isOutPutFileOpen)
			{
				outPutFilePrintWriter.close();
			}
			outPutFilePrintWriter = new PrintWriter(
					new FileWriter("houses.dat"));
			
			isOutPutFileOpen = true;
		} catch (Exception exception) 
		{

			exception.printStackTrace();

		}

	}
		
	public static boolean moreHouses() 
	{
		boolean hasMoreRecords = false;
		try {
			if (!isInputFileOpen || (inputString == null))
			{
				hasMoreRecords =  false;
			}
			else
			{
				hasMoreRecords =   true;
			}
		} catch (Exception exception) 
		{

			exception.printStackTrace();

		}
		
		return hasMoreRecords;
	}

	// Gets and returns house information from the house info file
	public static ListHouse getNextHouse()
	{
		String lastName = "xxxxx";
		String firstName = "xxxxx";
		int lotNumber = 0;
		int price = 0;
		int squareFeet = 0;
		int noOfBedRooms = 0;
		ListHouse house = null;
		try {
			lastName = inputString;
			firstName = inputFileBufferReader.readLine();
			lotNumber = Integer.parseInt(inputFileBufferReader.readLine());
			price = Integer.parseInt(inputFileBufferReader.readLine());
			squareFeet = Integer.parseInt(inputFileBufferReader.readLine());
			noOfBedRooms = Integer.parseInt(inputFileBufferReader.readLine());

			inputString = inputFileBufferReader.readLine();
			house = new ListHouse(lotNumber, firstName, lastName,
					price, squareFeet, noOfBedRooms);
		} catch (Exception exception) {

			exception.printStackTrace();

		}
		return house;

	}

	// Puts parameter house information into the house info file
	public static void putToFile(ListHouse house)  {

		try {
			outPutFilePrintWriter.println(house.getLastName());
			outPutFilePrintWriter.println(house.getFirstName());
			outPutFilePrintWriter.println(house.getLotNumber());
			outPutFilePrintWriter.println(house.getPrice());
			outPutFilePrintWriter.println(house.getSquareFeet());
			outPutFilePrintWriter.println(house.getNoOfBedRooms());
		} catch (Exception exception) {

			exception.printStackTrace();

		}
	}

	public static void close()  // Closes house info file
	{
		try {
			if (isInputFileOpen)
			{
				inputFileBufferReader.close();
			}
			if (isOutPutFileOpen)
			{
				outPutFilePrintWriter.close();
			}
			isInputFileOpen = false;
			isOutPutFileOpen = false;
		} catch (Exception exception) {

			exception.printStackTrace();

		}
	}
}
