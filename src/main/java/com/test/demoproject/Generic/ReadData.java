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

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

public class ReadData extends Generic {
	static String targetData = "publisher11";
	static int targetLength;
	static String[] root;
	static int count =0;
	static String value=null;
	
 
	
	@SuppressWarnings("unchecked")
	public static String readWebElement(String locatorPath){

		
		int indexOfDot=locatorPath.indexOf(".");
		System.out.println("indexOfDot ::"+indexOfDot);
		String fileName= locatorPath.substring(0, indexOfDot);
		String elementPath = locatorPath.substring(indexOfDot+1, locatorPath.length());
		
		System.out.println("fileName   -->" + fileName);
		System.out.println("elementPath-->" + elementPath);
		String filePath = locatorPathFolder + "\\"+fileName+".json";
		
		root = elementPath.split("\\.");
		targetLength = root.length;
		jsonParser = new JSONParser();
		

		System.out.println(filePath);
		//JSONObject jsonObject;
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
			System.out.println("jsonObject   Size::"+jsonObject.size());
			System.out.println("targetLength Size::"+targetLength);
			if(targetLength==1){
				value= jsonObject.get(elementPath).toString();
				System.out.println("Key Value is ::" + jsonObject.get(elementPath));
			}
			else if (targetLength>1) {
				Set<String> keys = jsonObject.keySet();
				System.out.println("Keys::"+keys);
				for(String key:keys) {
					System.out.println("root["+count+"] ::"+root[count]);
					if(key.equalsIgnoreCase(root[count])) {
						count++;
						System.out.println("Target Reached::"+key+" Found");
						//JSONObject childObject=(JSONObject) jsonObject.get(key);
						JSONObject childObject=childObjectMethod((JSONObject) jsonObject.get(key));
						System.out.println("ChildObject Keys::"+childObject);
						//System.out.println("ChildObject::"+childObject.get("SignIn"));
						System.out.println("root ::"+root[targetLength-1]);
						//System.out.println("Final Value:: "+childObject.get("SignIn"));
					}
				}
				//System.out.println("Final Value::"+hm.get(targetKey));
				System.out.println("Final Value::"+value);
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
		}catch (ArrayIndexOutOfBoundsException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();

	}
		count=0;
		return value;
		
	}

	/*
	 * @SuppressWarnings("unchecked") private static JSONObject
	 * jsonObjectMethod(JSONObject jsonObject) { JSONObject jo = null; Set<String>
	 * keys = jsonObject.keySet(); System.out.println("Keyset ::" + keys);
	 * 
	 * // while(targetLength>=count) { //System.out.println("target ::" +
	 * root[count]); for (String key : keys) {
	 * System.out.println("keys.size()::"+keys.size()); if(keys.size()==1) { jo=
	 * jsonObject; System.out.println("SUSPECTING this::"); break; } if(flag==1) {
	 * System.out.println("HERE::"); // jo= jsonObject;
	 * System.out.println("flag is set already::"); break; }
	 * 
	 * System.out.println("count ==" + count + " targetLength==" + targetLength);
	 * 
	 * 
	 * if (count < targetLength) { if ((root[count]).contentEquals(key)) { int
	 * zeroKeys = 0; System.out.println("Matched-->root[" + count + "] == " +
	 * root[count] + "  && key==" + key);
	 * //if((boolean)jsonObject.get(key).equals(null)) {
	 * 
	 * if(key.contains(root[root.length-1])) {
	 * System.out.println("reached to last node::"); flag=1; jo=jsonObject; break; }
	 * JSONObject childObject = (JSONObject) jsonObject.get(key); //jo =
	 * childObject; System.out.println("Child Object:: " + childObject.toString());
	 * 
	 * if (!childObject.isEmpty()) { System.out.println("HAHAHAHA"); count = count +
	 * 1; System.out.println("Count::" + count); System.out.println();
	 * jo=jsonObjectMethod((JSONObject) childObject); } }
	 * 
	 * else { System.out.println("Not matched (root[" + count + "]) == " +
	 * (root[count]) + "  && key==" + key); } } else {
	 * System.out.println("It came here::"); //return jsonObject; //break; } }
	 * return jo;
	 * 
	 * }
	 */
	@SuppressWarnings("unchecked")
	private static JSONObject childObjectMethod(JSONObject childObject) {
		//System.out.println("root size::"+root.length);
		//System.out.println("root[0]::"+root[0]);
		for(int i=0;i<root.length;i++) {
			System.out.println("root["+i+"]-->"+root[i]);
		}
		JSONObject returnObject=null;
		
		Set<String> childKeys = childObject.keySet();
		System.out.println("childKeys ::"+childKeys);
		
		Set<String> entrySet = childObject.entrySet();
		System.out.println("entrySet ::"+entrySet);
		for(String childKey:childKeys) {
			
			System.out.println("Child Key ::"+childKey+"=="+count+"-->"+root[count]);
			if(childKey.equalsIgnoreCase(root[count])) {
				count++;
				if(count==targetLength) {
					System.out.println("TARGET ZONE::"+childObject.get(childKey));
					returnObject =childObject;
					value= childObject.get(childKey).toString();
					break;
				}	
				else{
					childObjectMethod((JSONObject) childObject.get(childKey));
					continue;
				}
				
			}
		}		
		return returnObject;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String,String> readWebElementValue(String fileName, String fileValues) {
		HashMap<String,String> childObject = new HashMap<String, String>();
		childSetValue = new HashMap<String, String>();
		targetLength=0;
		root=null;
		value=null;
		count=0;
		jsonObject=null;
		int lastIndex=fileName.lastIndexOf(".");
		System.out.println("lastIndex::"+lastIndex);
		
		String filePath=fileName.substring(0, lastIndex).replace(".","\\");
		System.out.println("filePath ::"+filePath);
		
		String extension =fileName.substring(lastIndex, fileName.length());
		System.out.println("extension ::"+extension);
		
		String completeFilePath=inputfolder+"\\"+filePath+extension;
		System.out.println("completeFilePath ::"+completeFilePath);
		
		System.out.println("fileValue::"+fileValues);
		
		root = fileValues.split("\\.");
		targetLength = root.length;
		System.out.println("root[0]-->"+root[0]);
		jsonParser = new JSONParser();
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(completeFilePath));
			System.out.println("jsonObject   Size::"+jsonObject.size());
			System.out.println("targetLength Size::"+targetLength);
			if(targetLength==1){
				System.out.println("Key Value is ::" + jsonObject.get(root[0]));
			}
			if(targetLength==1 && !jsonObject.isEmpty()){
				childObject=childObjectMethodValues(jsonObject);
				System.out.println("childObject ::"+childObject.toString());
				childSetValue=null;
			}
			else if (targetLength>1) {
				Set<String> keys = jsonObject.keySet();
				System.out.println("Keys::"+keys);
				for(String key:keys) {
					if(key.equalsIgnoreCase(root[count])) {
						count++;
						System.out.println("Target Reached::"+key+" Found");
						//JSONObject childObject=(JSONObject) jsonObject.get(key);
						//JSONObject childObject=childObjectMethod((JSONObject) jsonObject.get(key));
						System.out.println("ChildObject Keys::"+childObject);
						//System.out.println("ChildObject::"+childObject.get("SignIn"));
						System.out.println("root ::"+root[targetLength-1]);
						//System.out.println("Final Value:: "+childObject.get("SignIn"));
					}
				}
				//System.out.println("Final Value::"+hm.get(targetKey));
				System.out.println("Final Value::"+value);
			}	
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return childObject;
	}
	@SuppressWarnings("unchecked")
	private static HashMap<String, String> childObjectMethodValues(JSONObject childObject) {
		System.out.println("root size::"+root.length);
		//System.out.println("root[0]::"+root[0]);
		
	try {
		
		for(int i=0;i<root.length;i++) {
			
			System.out.println("root["+i+"]-->::"+root[i]);
		}
		
		
		Set<String> childKeys = childObject.keySet();
		System.out.println("childKeys ::"+childKeys);
		
		Set<String> entrySet = childObject.entrySet();
		System.out.println("entrySet ::"+entrySet);
		
		if(status) {
			 for(String childkey:childKeys) {
				 System.out.println("SUCCESS::");
				 System.out.println("childkey::"+childkey+" Value::"+ childObject.get(childkey));
				 childSetValue.put(childkey, (String) childObject.get(childkey));
				 
			 }
			 //break;
			}
		
		for(String childKey:childKeys) {
			
			System.out.println("Child Key ::"+childKey+"=="+count+"-->"+root[count]);
			if(childKey.equalsIgnoreCase(root[count])) {
				count++;
				if(count==targetLength) {
					System.out.println("TARGET ZONE::"+childObject.get(childKey));
					if(!((JSONObject)childObject.get(childKey)).isEmpty()) {
						System.out.println("STATUS TRUE::");
						status=true;
						childObjectMethodValues((JSONObject) childObject.get(childKey));
					}
					//returnObject =childObject;
					value= childObject.get(childKey).toString();
					break;
				}	
				else{
					childObjectMethodValues((JSONObject) childObject.get(childKey));
					continue;
				}
				
			}
		}		
		
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println("Silence::");
	}
	return childSetValue;
	
	}
}
