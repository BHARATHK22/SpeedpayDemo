package com.test.demoproject.Generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenericFunctions extends Generic {

	public static String screeshot(WebDriver driver) {

		long randomNumber = System.currentTimeMillis();
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		String dest = currentDirectory + "\\Report\\" + randomNumber + ".png";
		File destination = new File(dest);
		try {
			FileUtils.copyFile(SrcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dest;

	}
}
