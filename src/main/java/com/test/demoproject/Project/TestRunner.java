package com.test.demoproject.Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.test.demoproject.Generic.Generic;
import com.test.demoproject.Generic.GetProperties;
import com.test.demoproject.ReportModule.HTMLReport;


public class TestRunner extends Generic {
	static Scanner scanner;
	public static void main(String[] args) {
		GetProperties.initializeEnvironment("UiConfig");
		
		try {
			
			scanner = new Scanner(new File(inputfolder+"\\TestRunner.csv"));
			
			while (scanner.hasNextLine()) // returns a boolean value
			{
				
				testCase= scanner.nextLine();
				HtmlReportFile = new File(currentDirectory + "\\Report\\" + testCase+"_"+uniqueNumber + ".html");
				bw = new BufferedWriter(new FileWriter(HtmlReportFile));
			
				System.out.println("Started executing Test Case::"+testCase);
				HTMLReport.initialize();
				ReadTestCase.readTest(testCase);
				System.out.println("Finished executing Test Case::"+testCase);
				HTMLReport.closeReport();
			}
			
			scanner.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Invalid File Provided");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
