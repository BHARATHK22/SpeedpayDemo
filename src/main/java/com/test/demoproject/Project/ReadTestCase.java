package com.test.demoproject.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.test.demoproject.Generic.ActionItem;
import com.test.demoproject.Generic.Generic;

public class ReadTestCase extends Generic{
	public static void readTest(String testCase) {

		
		Scanner sc;
		try {
			sc = new Scanner(new File(inputfolder+"\\"+testCase+".csv"));

			while (sc.hasNextLine()) 
			{
				
				String line=sc.nextLine();
				//System.out.println("Line::"+line);
				if(line.contains("Action")) {
					continue;
				}
				ActionItem.actionTest(line);
			}
			
			sc.close(); 
		} catch (FileNotFoundException e) {
			System.out.println("Exception is::"+e.getMessage());
			e.printStackTrace();
		}
	}

}
