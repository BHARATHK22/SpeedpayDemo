package com.test.demoproject.Generic;

import java.io.BufferedWriter;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public class Generic {
	public static String currentDirectory = System.getProperty("user.dir");
	public static String inputfolder = currentDirectory + "\\InputFiles";
	public static String driversPath = currentDirectory + "\\Drivers";
	public static String locatorPathFolder = currentDirectory + "\\Locators";
	public static String[] root;
	public static int count = 0;
	public static int flag = 0;
	public static WebDriver driver;
	public static Scanner scanner;
	public static JSONObject jsonObject;
	public static JSONParser jsonParser = new JSONParser();
	public static String environment;
	public static HashMap<String, String> hm = new HashMap<String, String>();
	public static long uniqueNumber = System.currentTimeMillis();
	public static File HtmlReportFile; 
	public static  BufferedWriter bw;
	public static String targetKey;
	public static boolean status=false;
	public static HashMap<String,String> childSetValue=null;
	public static String testCase=null;
}
