package com.interra.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.google.common.io.Files;


public class TLM {
	
	private static volatile TLM objTLM = null;
	
	// Constructor : Set the status PASS as default
	private TLM() {
	    System.out.println("TLM Object Created ...");
		this.detailedLogReport = new String("");
		this.logReportHash = new HashMap<String, String>();
		this.logReportHash.put("STATUS", "PASS");
	}
	
	public static TLM accessTLM() {
		if(null == objTLM) {
			synchronized (TLM.class) {
				if(null == objTLM) {
					objTLM = new TLM();
				}
			}
		}
		return objTLM;
		
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

	// Set the attributes of the Testcase
	public final void setAttributes(HashMap<String, String> attr) {
		String attrList = new String("");
		if(attr != null) {
			for (Map.Entry<String, String> entry : attr.entrySet()) {
	            String key = entry.getKey();
	            String values = entry.getValue();
	            attrList += ("  " +  key + " : " + values + "\r\n");
	        }
		}
        this.logReportHash.put("ATTRIBUTE", attrList);
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
                report += " [STATUS : PASS]";
            else {
            	passStatus = false;
            	this.logReportHash.put("STATUS", "FAIL");
                report += " [STATUS : FAIL]";
            }
        }
        else if (check.equals("NE")) {
            if (actVal != astVal)
                report += " [STATUS : PASS]";
            else {
            	passStatus = false;
            	this.logReportHash.put("STATUS", "FAIL");
                report += " [STATUS : FAIL]";
            }
        }
		else if (check.equals("EX")){
        	passStatus = false;
        	this.logReportHash.put("STATUS", "EXCEPTION");
            report += " [STATUS : EXCEPTION]";
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

	// Add report overloaded
	public final void addReport(String report, Integer tab) {
		addReport(report, tab, "NO", 0, 0);
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
			header += " : [PASSED] \r\n Start Time : [ "+ StartTime + "] || End Time : [ " + EndTime + " ] \r\n TOTAL TIME CONSUME =[ " +  TotalTime + "]\r\n";
		else if(status.equals("FAIL"))
			header += " : [FAILED] \r\n Start Time : [ "+ StartTime + "] || End Time : [ " + EndTime + " ] \r\n TOTAL TIME CONSUME = [ " +  TotalTime + "]\r\n";
		else
			header += " : [EXCEPTION] \r\n Start Time : [ "+ StartTime + "] || End Time : [ " + EndTime + " ] \r\n TOTAL TIME CONSUME = [ " +  TotalTime + "]\r\n";


    	try {
    		File logFile = new File(this.logReportFile);
    		// Create the file if not exists 
    		if(!logFile.exists())
    			logFile.createNewFile();
    		// Open the file in append mode
			FileWriter writer = new FileWriter(logFile, true); 
			// Writes the content to the file
	        writer.write(header);
	        if(status.equals("FAIL")|| status.equals("EXCEPTION")) {
	        	writer.write(attribute);
	        	writer.write("  STACK TRACE\r\n");
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
	
	/****
	 * 
	 * @param report String to be added in report
	 * @param actual value to be matched
	 * @param expected value to be matched with
	 */
	public final void addReportAfterEqualityCheck(String report, Integer actual, Integer expected) {
		addReport(report, 1, "EQ", actual, expected);
	}
	
	/******+
	 * 
	 * @param report text to be added in report
	 * @param flag true for passed, false for failed
	 */
	public final void addReport(String report, Boolean flag) {
		if(flag)
			addReport(report, 1, "EQ", 1, 1);
		else
			addReport(report, 1, "EQ", 1, 0);
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