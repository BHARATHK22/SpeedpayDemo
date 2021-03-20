package com.test.demoproject.Generic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainDriver2 extends Generic {
	static String targetData = "publisher";
	static int targetLength;


	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		root = targetData.split("\\.");
		System.out.println("Array size==" + root.length);
		targetLength = root.length;
		JSONParser jsonParser = new JSONParser();
		String filePath = inputfolder + "\\SampleInputFile3.json";

		System.out.println(targetData);
		JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));

			if(targetLength==1){
				System.out.println("Key Value is ::" + jsonObject.get(targetData));
			}
			else if ((jsonObject.size() > 1)) {
				JSONObject jo=jsonObjectMethod(jsonObject);
				
				System.out.println("jo object::"+jo.toString());
				
				
				if(!jo.isEmpty()) {
					Set<String> keys = jo.keySet();
					System.out.println("jo.keySet()::"+jo.keySet());
					HashMap<String, String> hm = new HashMap<String, String>();
					for(String key:keys) {
						hm.put(key, (String) jo.get(key));
					}
					System.out.println("HaskMap:: "+hm);
				}
				if(flag==1) {
					System.out.println();
					System.out.println("Single Value::"+jo.get(root[targetLength-1]));
				}
			} 
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("incorrect input");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JSONObject jsonObject = (JSONObject) obj;

	}

	@SuppressWarnings("unchecked")
	private static JSONObject jsonObjectMethod(JSONObject jsonObject) {
		JSONObject jo = null;
		Set<String> keys = jsonObject.keySet();
		System.out.println("Keyset ::" + keys);
		
		// while(targetLength>=count) {
		//System.out.println("target ::" + root[count]);
		for (String key : keys) {
			System.out.println("keys.size()::"+keys.size());
			if(keys.size()==1) {
				jo= jsonObject;
				System.out.println("SUSPECTING this::");
				break;
			}
			if(flag==1) {
				System.out.println("HERE::");
		//		jo= jsonObject;
				System.out.println("flag is set already::");
				break;
			}

			System.out.println("count ==" + count + " targetLength==" + targetLength);
			

			if (count < targetLength) {
				if ((root[count]).contentEquals(key)) {
					int zeroKeys = 0;
					System.out.println("Matched-->root[" + count + "] == " + root[count] + "  && key==" + key);
					//if((boolean)jsonObject.get(key).equals(null)) {
						
					if(key.contains(root[root.length-1])) {
						System.out.println("reached to last node::");
						flag=1;
						jo=jsonObject;
						break;
					}
					JSONObject childObject = (JSONObject) jsonObject.get(key);
					//jo = childObject;
					System.out.println("Child Object:: " + childObject.toString());

					if (!childObject.isEmpty()) {
						System.out.println("HAHAHAHA");
						count = count + 1;
						System.out.println("Count::" + count);
						System.out.println();
						jo=jsonObjectMethod((JSONObject) childObject);
					}
				}
					
				 else {
					System.out.println("Not matched (root[" + count + "]) == " + (root[count]) + "  && key==" + key);
				}
			}
			else {
				System.out.println("It came here::");
				//return jsonObject;
				//break;
			}
		}
		return jo;
		
	}
}
