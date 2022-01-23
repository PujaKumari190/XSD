package com.interra.Base;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.interra.Base.Logger;
import com.interra.Library.Configuration;
//import com.interra.rest.TestSuite.Retry.RetryAnalyzer;


public class Base {
	int a =0;
	private String reportName = null;
	private String reportPath = "";
	private String regid;
	private Date datestart;
	private Date dateend;
	private String sectionname;
	private String machinename;
	private String regdate;
	private String regtime;
	private int passcount;
	private int failcount;
	private int skipcount;
	private int totalcount;
	private String logpath;
	private String serverlogpath;
	private String screenshotpath;
	private int priority;
	private String versionid;
	private int build;
	private String edition;
	private String platform;
	private String timetaken;
	private Date starttime;
	ArrayList<String> taskids = new ArrayList<>();
	ArrayList<String> profileids = new ArrayList<>();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

//	@BeforeSuite
//	public void beforeSuite(ITestContext context)
//	{	
//		String startTime = LocalDateTime.now().toString();
//	System.out.println("Execution start time: "+ startTime);
//
//	//suitename 
//	if (context.getSuite().getName().equalsIgnoreCase("Default suite")) {
//		List<String> testClasses = new ArrayList<String>();
//		for (ITestNGMethod test : context.getAllTestMethods()) {
//			if (!testClasses
//					.contains(test.getConstructorOrMethod().getMethod().getDeclaringClass().getSimpleName())) {
//				testClasses.add(test.getConstructorOrMethod().getMethod().getDeclaringClass().getSimpleName());
//			}
//		}
//		reportName = String.join(" & ", testClasses);
//	} else {
//		reportName = context.getSuite().getName();
//	}
//	System.out.println("The report name is: "+ reportName);
//	//creation of db
//	// use the report name and timespace to dot he dbw work ...
//
//	}

	
	@BeforeClass(alwaysRun = true)
	public synchronized void preClassLog(){
		//update values of all stat variables here
		sectionname = getClass().getSimpleName();
		machinename= System.getenv("COMPUTERNAME").toUpperCase();
		datestart = new Date();
		regdate= dateFormat.format(datestart).split(" ")[0].replace("/", "_");
		regtime= dateFormat.format(datestart).split(" ")[1].replace(":", "_");
		Logger.accessLogger().setStartTime(datestart.toString());
		try {
			datestart = dateFormat.parse(dateFormat.format(datestart));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Start date for pre class log is " + datestart);
//		HashMap<String,String> details  = SuiteUtility.accessSuiteUtility().getBatonDetails();
//		passcount= 0;
//		failcount= 0;
//		totalcount= 0; // to be changed later. check options in testng
//		versionid= details.get("major")+"."+details.get("minor");
//		build= Integer.parseInt(details.get("build"));
//		edition= details.get("edition").replace(" edition", "").toUpperCase();
		platform= System.getProperty("os.name");
		logpath=System.getProperty("user.dir")+ File.separator + "Logs" + "_" + (String) regdate+"_"+ regtime;//get current syspath

		timetaken= "0.00";//implement logic and functions for the same afterclass.

		System.out.println("LOGPATH " + System.getProperty("user.dir"));
		//LOG FILE ACTIONS
		Logger.accessLogger().setLogFile(logpath);
		//create log file
		//write header info


	}

	@AfterClass(alwaysRun = true)
	public synchronized void postClassLog(){
		System.out.println(">>>  Finished execution for "+ sectionname+ " at "+ LocalDateTime.now().toString());
		dateend = new Date();
		try {
			dateend = dateFormat.parse(dateFormat.format(dateend));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception in fetching end date " + e.getMessage());
		}
		System.out.println("Start date for pre class log is " + dateend);

		long diff = dateend.getTime() - datestart.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		timetaken =diffDays + "d " + diffHours + ":" + diffMinutes + ":" + diffSeconds;
		System.out.println("Time taken in execution of " + sectionname + " is " + timetaken);
		String query= "update regressions set timetaken = '"+ timetaken + "' , passcount= "+ passcount +" "
				+ "where regid = "+regid+";";

		System.out.println(query);
		Logger.accessLogger().addReport(query, 1);


	}


	@BeforeMethod
	protected synchronized void beforeMethod(ITestResult result) throws Exception {
		System.out.println("BEFORE METHOD OF BASE >>>>>>>>");
		starttime = new Date();
		starttime= dateFormat.parse(dateFormat.format(starttime));
		Logger.accessLogger().setStartTime(starttime.toString());
		Logger.accessLogger().setHeader("["+result.getMethod().getMethodName()+"]");
		HashMap<String, String> attr = new HashMap<>();
		Logger.accessLogger().setAttribute(sectionname);
	}


	@AfterMethod
	protected synchronized void afterMethod(ITestResult result) throws Exception {
		String tc = result.getMethod().getMethodName();
		String status = "EX";
		if (result.getStatus() == ITestResult.FAILURE) {
			//				if (result != null && (result.getThrowable() == null || result.getThrowable().getMessage() == null
			//						|| result.getThrowable().getMessage().equalsIgnoreCase("null"))) {
			//					//mark test as failed in db 
			//					System.out.println("Insert in DB. FAILED CASE . Increment counter: " + result.getName());
			//					//incerement fail counter
			//				} 
			failcount++;
			status = "FAILED";
			System.out.println("============[ " +tc + " ]============ [[[ FAILED ]]]");
		} else if (result.getStatus() == ITestResult.SKIP ) {
//			if(!(RetryAnalyzer.counter <= RetryAnalyzer.retryLimit)) {
//				System.out.println("============[ " + tc + " ]============ [[[ SKIPPED ]]]");
//				status = "SKIPPED";
//				skipcount++;
//			}
		} else if (result.getStatus() == -1) {
			System.out.println("============[ " +tc + " ]============ [[[ SKIPPED ]]]");
			skipcount++;
			status = "SKIPPED";
		} else {
			System.out.println("============[ " + tc + " ]============ [[[ PASSED ]]]");
			passcount++;
			status = "PASSED";
		}

		//update date time stats
		dateend = new Date();
		try {
			dateend = dateFormat.parse(dateFormat.format(dateend));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception in fetching end date " + e.getMessage());
		}
		Logger.accessLogger().setEndTime(dateend.toString());

		long diff = dateend.getTime() - starttime.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff/ (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		timetaken =diffDays + "d " + diffHours + ":" + diffMinutes + ":" + diffSeconds;
		Logger.accessLogger().setTotalTime(timetaken);
		Logger.accessLogger().dumpReportToLogFile();


		//DB actions

		//update passed and failed count in the master table.

		//copying the file to wamp server
//		String path =  "//ARIZONA"  + File.separator +"Logs" + File.separator + edition + File.separator + sectionname + "_" + (String) regdate+"_"+ regtime;//get current syspath
//		
//		File targetLogFile = new File(path +File.separator+ sectionname + ".txt");
//		targetLogFile.getParentFile().mkdirs();
//		if(targetLogFile.exists())
//			targetLogFile.delete();
//		Files.copy(new File(logpath+ File.separator +sectionname +".txt"), targetLogFile);
	}


	public void setupTest()
	{
		System.out.println("The name of the test is ");
	}
	//
	//	@AfterSuite(alwaysRun = true)
	//	protected synchronized void afterSuite(ITestContext context) throws Exception {
	//		endTime = LocalDateTime.now().toString();
	//		System.out.println("Test end time is: " + endTime);
	//		//update the last updated time for the current module!!
	//	}


	//TEST LOGGING METHODS


	// FRAMEWORK SPECIFIC LIBRARY FUNCTIONS. IN THIS CASE REST FOR BATON

//	@AfterSuite
//    public void afterSuite(ITestContext context) {
//        printSuiteResults(context.getSuite());
//    }
//
//    private void printSuiteResults(ISuite suite) {
//        Collection<ISuiteResult> suiteResults = suite.getResults().values();
//        for (ISuiteResult suiteResult : suiteResults) {
//            printAllResults(suiteResult.getTestContext());
//        }
//    }
//
//    private void printAllResults(ITestContext context) {
//        Set<ITestResult> actualPassedSet = context.getPassedTests().getAllResults();
//        Set<ITestResult> actualFailedSet = context.getFailedTests().getAllResults();
//        Set<ITestResult> skipSet = context.getSkippedTests().getAllResults();
//        for(ITestResult res : skipSet) {
//        	for(ITestResult passResult : actualPassedSet) {
//        		String str = res.toString();
//        		String skipTestname = str.substring(str.indexOf('=')+1, str.indexOf(" ", str.indexOf('=')));
//        		String str1 = passResult.toString();
//        		String passedTestname = str1.substring(str1.indexOf('=')+1, str1.indexOf(" ", str1.indexOf('=')));
//        		if(skipTestname.equals(passedTestname)) {
//        			skipSet.remove(res);
//        		}
//        	}
//        	for(ITestResult failresult : actualFailedSet) {
//        		String str = res.toString();
//        		String skipTestname = str.substring(str.indexOf('=')+1, str.indexOf(" ", str.indexOf('=')));
//        		String str1 = failresult.toString();
//        		String passedTestname = str1.substring(str1.indexOf('=')+1, str1.indexOf(" ", str1.indexOf('=')));
//        		if(skipTestname.equals(passedTestname)) {
//        			skipSet.remove(res);
//        		}
//        	}
//        }
//    }
}
