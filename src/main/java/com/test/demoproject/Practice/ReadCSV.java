package com.test.demoproject.Practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.test.demoproject.Generic.Generic;
import com.test.demoproject.Generic.ReadData;

public class ReadCSV extends Generic {

	public static void main(String[] args) {

		// parsing a CSV file into Scanner class constructor
		Scanner sc;
		try {
			sc = new Scanner(new File(inputfolder+"\\ReadCSVFile.csv"));

			sc.useDelimiter(","); // sets the delimiter pattern
		
			while (sc.hasNextLine()) // returns a boolean value
			{
				System.out.print(sc.nextLine()); 
				System.out.print("\n");// find and returns the next complete token from this scanner
			}
			
			sc.close(); // closes the scanner
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
