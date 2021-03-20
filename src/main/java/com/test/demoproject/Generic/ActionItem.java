package com.test.demoproject.Generic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.demoproject.ReportModule.HTMLReport;

public class ActionItem extends Generic {

	public static void actionTest(String actionItem) {
		System.out.println(actionItem);
		String[] line = actionItem.split(",");
		System.out.println("Action ::" + line[0]);
		String xpath = null;

		switch (CMD.valueOf(line[0])) {

		case Click:
			System.out.println("*************Start of Click*****************");
			// System.out.println("Clicked");
			xpath = ReadData.readWebElement(line[1]);

			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				System.out.println("WebElement Found::");
				driver.findElement(By.xpath(xpath)).click();
				HTMLReport.testReport(line[0], line[1], "PASS", GenericFunctions.screeshot(driver));
			} else {
				System.out.println("WebElement NOT  Found::");
				HTMLReport.testReport(line[0], line[1], "FAIL", GenericFunctions.screeshot(driver));
			}
			System.out.println("*************End of Click*****************");
			break;

		case InputData:
			// System.out.println("InputData");
			System.out.println("*************Start of InputData*****************");
			HashMap<String, String> hashMap = ReadData.readWebElementValue(line[2], line[3]);
			System.out.println("hashMap::" + hashMap.toString());
			System.out.println("*************End of InputData*****************");
			System.out.println("*************Start of Xpath*****************");
			xpath = ReadData.readWebElement(line[1]);
			System.out.println("WebElement::" + xpath);
			System.out.println("*************End of Xpath*****************");
			try {

				
				//driver.navigate().refresh();
				//Thread.sleep(8000);
				List<WebElement> list = driver.findElements(By.xpath(xpath));

				Thread.sleep(3000);
				Set<String> keys = hashMap.keySet();
				java.util.Iterator<String> iterator = keys.iterator();
				System.out.println("InputData keys::"+keys.toString());
				if (list.size() == keys.size()) {
					System.out.println("Welcome boss::");
					for (int i = 0; i < list.size(); i++) {
						list.get(i).sendKeys(hashMap.get(iterator.next()));
					}
					HTMLReport.testReport(line[0], line[1] + "\n" + line[2] + "\n" + line[3], "PASS",
							GenericFunctions.screeshot(driver));
				} else {
					HTMLReport.testReport(line[0], line[1] + line[2] + line[3], "FAIL",
							GenericFunctions.screeshot(driver));
				}
				list=null;
				Thread.sleep(3000);

			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case Navigate:
			// System.out.println("Navigate");
			if (hm.get("environment").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", driversPath + "\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			if (hm.get("environment").equalsIgnoreCase("firefox")) {
				System.out.println("firefox not available");
			}
			driver.manage().window().maximize();
			driver.get(hm.get("URL"));
			String url = driver.getCurrentUrl();
			boolean flag = url.contentEquals(hm.get("URL"));
			if (flag == true) {
				HTMLReport.testReport(line[0], url, "PASS", GenericFunctions.screeshot(driver));
			} else {
				HTMLReport.testReport(line[0], url, "FAIL", GenericFunctions.screeshot(driver));
			}
			break;
		case IsDisplayed:
			System.out.println("*************Start of IsDisplayed*****************");
			// System.out.println("Clicked");
			xpath = ReadData.readWebElement(line[1]);

			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				System.out.println("WebElement Found::");
				HTMLReport.testReport(line[0], line[1], "PASS", GenericFunctions.screeshot(driver));
			} else {
				System.out.println("WebElement NOT  Found::");
				HTMLReport.testReport(line[0], line[1], "FAIL", GenericFunctions.screeshot(driver));
			}
			System.out.println("*************End of IsDisplayed*****************");

			break;

		case Quit:
			driver.close();
			HTMLReport.testReport(line[0], "", "PASS", "");
			break;
		default:
			System.out.println("Incorrect input keyword proived");
			break;
		}

	}
}
