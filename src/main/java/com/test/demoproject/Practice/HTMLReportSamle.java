package com.test.demoproject.Practice;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.test.demoproject.Generic.Generic;

class HTMLReportSample extends Generic {

    public static void testReport(String Statement,String flag,String Screeshot) throws Exception {
    	long source = System.currentTimeMillis(); 
        File f = new File(currentDirectory+"\\Report\\"+source+".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><body><center><h1>Test Report</h1></center>");
        bw.write("<textarea cols=75 rows=10>");
        for (int ii=0; ii<20; ii++) {
            bw.write("Blah blah..");
        }
        bw.write("</textarea>");
        bw.write("</body></html>");
        bw.close();

        //Desktop.getDesktop().browse(f.toURI());
    }
}