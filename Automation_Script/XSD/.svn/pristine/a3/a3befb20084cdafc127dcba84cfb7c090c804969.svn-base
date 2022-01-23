package com.interra.Base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class Logger {
	
	private static volatile Logger objLogger = null;
	
	// Constructor : Set the status PASS as default
	private Logger() {
	    System.out.println("Logger Object Created ...");
		this.detailedLogReport = new String("");
		this.logReportHash = new HashMap<String, String>();
		this.logReportHash.put("STATUS", "PASS");
	}
	
	public static Logger accessLogger() {
		if(null == objLogger) {
			synchronized (Logger.class) {
				if(null == objLogger) {
					objLogger = new Logger();
				}
			}
		}
		return objLogger;
		
	}

	
	// Set the log file to be dumped in
	public final void setLogFile(String path) {
		this.logReportFile = path;
	}
	
	// Set Testcase and Feature
	public final void setFeatureTestcase(String feature, String testcase) {
		this.feature = feature;
		this.testcase = testcase;
	}

	// Set the Header of report for the corresponding Testcase
	public final void setHeader(String header) {
		this.logReportHash.put("HEADER", header);
	}
	
	// Set the Header of report for the corresponding Testcase
	public final void setStartTime(String header) {
		this.logReportHash.put("STARTTIME", header);
		}
	// Set the Header of report for the corresponding Testcase
	public final void setEndTime(String header) {
		this.logReportHash.put("ENDTIME", header);
		}
	//Set the Header of report for the corresponding Testcase
	public final void setTotalTime(String header) {
		this.logReportHash.put("TOTALTIME", header);
		}

//	// Set the attributes of the Testcase
//	public final void setAttributes(HashMap<String, String> attr) {
//		String attrList = new String("");
//		if(attr != null) {
//			for (Map.Entry<String, String> entry : attr.entrySet()) {
//	            String key = entry.getKey();
//	            String values = entry.getValue();
//	            attrList += ("  " +  key + " : " + values + "\r\n");
//	        }
//		}
//        this.logReportHash.put("ATTRIBUTE", attrList);
//	}
	
	public final void setAttribute(String str)
	{
		 this.logReportHash.put("ATTRIBUTE", str);
	}

	// Add one line report to the consolidated detailed log report
	public final void addReport(String report, Integer tab, String check, Integer actVal, Integer astVal) {
		boolean passStatus = true;
		
        // Add tab before message
		if(0 == tab) {
			report = "  " + report;
		}
        else {
        	String tabString = "";
        	for(int i = 1; i <= tab; ++i)
        		tabString += "\t";
        	report = tabString + report;
        }

		// Assertion check 
        if(check.equals("EQ")) {
            if (actVal == astVal)
                {report += " [STATUS : PASS]";
                org.testng.Assert.assertEquals(true, true);}
            else {
            	passStatus = false;
            	this.logReportHash.put("STATUS", "FAIL");
                report += " [STATUS : FAIL]";
//                org.testng.Assert.fail(report);
            }
        }
        else if (check.equals("NE")) {
            if (actVal != astVal)
                {report += " [STATUS : PASS]";
                org.testng.Assert.assertEquals(false, false);}
            else {
            	passStatus = false;
            	this.logReportHash.put("STATUS", "FAIL");
                report += " [STATUS : FAIL]";
//                org.testng.Assert.fail(report);
            }
        }
        else if(check.equals("NO")) {}
        
        // Show Report In Console
        if(passStatus)
        	System.out.println(report);
        else {
        	System.err.println(report);
        }

        // Append report to detailed log report
        this.detailedLogReport += (report + "\r\n"); 
	}
	
	
	public void addReportAfterEQcheck(String report , int act, int expected)
	{
		this.addReport(report, 1, "EQ", act, expected);
	}
	public void addReport(String report , boolean condition){
		if(condition == true){
			report += " [STATUS : PASS]";
            org.testng.Assert.assertEquals(false, false);
		} else {
        	this.logReportHash.put("STATUS", "FAIL");
            report += " [STATUS : FAIL]";
            org.testng.Assert.fail(report);
		}
		
        // Show Report In Console
        if(condition)
        	System.out.println(report);
        else {
        	System.err.println(report);
        }

        // Append report to detailed log report
        this.detailedLogReport += (report + "\r\n"); 
	}

	// Add report overloaded
	public final void addReport(String report, Integer tab) {
		addReport(report, tab, "NO", 0, 0);
	}
	public final void addReport(String report)
	{
		addReport(report,1,"NO",0,0);
	}

	// Dump log report
	public final String dumpReportToLogFile() {

	    System.out.println("\n\nUpdating Report to Log ...");

	    // TODO Add TIME_INFO
		String status = this.logReportHash.get("STATUS").toString();
		String header = this.logReportHash.get("HEADER").toString();
		String attribute = this.logReportHash.get("ATTRIBUTE").toString();
		String StartTime = this.logReportHash.get("STARTTIME").toString();
		String EndTime = this.logReportHash.get("ENDTIME").toString();
		String TotalTime = this.logReportHash.get("TOTALTIME").toString();

		if(status.equals("PASS"))
			header += " : [PASSED] \r\n Start Time : [ "+ StartTime + "] || End Time : [ " + EndTime + " ] \r\n EXECUTION DURATION =[ " +  TotalTime + "]\r\n";
		else
			header += " : [FAILED] \r\n Start Time : [ "+ StartTime + "] || End Time : [ " + EndTime + " ] \r\n EXECUTION DURATION = [ " +  TotalTime + "]\r\n";

    	try {
    		File logFile = new File(this.logReportFile+ File.separator +attribute +".txt");
    		logFile.getParentFile().mkdirs();
    		// Create the file if not exists 
    		if(!logFile.exists())
    			logFile.createNewFile();
    		// Open the file in append mode
			FileWriter writer = new FileWriter(logFile, true); 

			// Writes the content to the file
	        writer.write(header);
	        if(status.equals("FAIL")) {
	        	writer.write(attribute);
	        	writer.write("  STACK TRACE\r\n");
	        	writer.write("  -----------\r\n");
	        	writer.write(this.detailedLogReport);
	        }
	        if(status.equals("PASS")) {
	        	writer.write(attribute);
	        	writer.write("  -----------\r\n");
	        	writer.write(this.detailedLogReport);
	        }
	        writer.flush();
	        writer.close();
 
	        System.out.println("Update to Log Report File Completed...");
    	}
		catch(IOException e) {
    		e.printStackTrace();
    	}
		finally {
	        this.cleanReport();
		}
		
		return status;
    }
	
	// Clean report of the last Testcase
	public final void cleanReport() {
		this.detailedLogReport = "";
		this.logReportHash.clear();
		this.logReportHash.put("STATUS", "PASS");
	}
	
	public void processTestSuiteName(String testSuite) {
		this.testSuiteName = testSuite;
	}
	

	// Variable declaration
	private String logReportFile = null;
	private String feature = null;
	private String testcase = null;
	private String detailedLogReport;
	private HashMap<String, String> logReportHash = null;
	private Object objWebUtility;
	private String testSuiteName = "testcaseSuite";

}