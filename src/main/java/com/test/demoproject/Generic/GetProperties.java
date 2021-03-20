package com.test.demoproject.Generic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetProperties extends Generic {
	
	static int targetLength;
	static String[] root;
	static String returnValue;
	@SuppressWarnings("unchecked")
	public static String initializeEnvironment(String file){

		
		String filePath = inputfolder + "\\"+file+".json";
		System.out.println(filePath);
		try {
			
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
			Set<String> keys = jsonObject.keySet();
			for(String key:keys) {
				hm.put(key, (String) jsonObject.get(key));
			}
			

		} catch (FileNotFoundException e) {
			System.out.println("Exception ::"+e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("incorrect input");
			//e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Exception ::"+e.getMessage());
			//e.printStackTrace();
		}
		return returnValue;
		

	}
}