package com.test.demoproject.ReportModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.test.demoproject.Generic.Generic;

public class HTMLReport extends Generic {

	public static void initialize() {
		try {
			bw.write("<html><body>");
			bw.write("<style>table, th, td {border: 1px solid black;border-collapse: collapse;}</style>");
			bw.write("<table style=\"width:100%\"><tr><th>Action-Item</th><th>Properties</th><th>Result</th><th>Screenshot</th></tr>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testReport(String actionItem, String properties, String result, String screenShot) {
		
		try {

			System.out.println("screenShotPath::"+screenShot);

			bw.write("<tr>");
			bw.write("<td>"+actionItem+"</td>");
			bw.write("<td>"+properties+"</td>");
			bw.write("<td>"+result+"</td>");
			bw.write("<td>");
			bw.write("<img src=\""+screenShot+"\" alt=\"image-failed\" width=\"40\" height=\"30\">");
			bw.write("</td>");
			bw.write("</tr>");
			//bw.write("<img src="+screenShot+"width=100 height=200></img>" );
			/*
			 * bw.write("<textarea cols=75 rows=10>"); for (int ii = 0; ii < 20; ii++) {
			 * bw.write("Blah blah.."); } bw.write("</textarea>");
			 */
			
		} catch (Exception e) {
			System.out.println("Exception ::" + e.getMessage());
		}
		// Desktop.getDesktop().browse(f.toURI());
	}
	public static void closeReport() {
		try {
			
			bw.write("</table>");
			bw.write("</body></html>");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}