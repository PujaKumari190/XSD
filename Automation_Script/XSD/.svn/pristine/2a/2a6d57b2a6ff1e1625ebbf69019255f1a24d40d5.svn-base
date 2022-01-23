package com.interra.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Assert;

import com.interra.Library.Configuration;


public class Reporter {

	private static volatile Reporter objReporter = null;
	public static String logDir = null; 
//	public static String baseURL = "http://localhost:8080/Baton/api/";//Read from config file TODO
	public static String baseURL = Configuration.accessConfiguration().getBaseURL();
	public String folderName = "";
	public String currentfile = "";
	public String masterfile ="";
	public PrintWriter writer = null;
	public void startModule(String moduleName)
	{
		folderName =  System.getProperty("user.dir")+ File.separator+  "src" +File.separator+ "main" + File.separator + "java" + File.separator + "logs" + File.separator + moduleName + "_" + Reporter.accessReporter().setCurrentTime();
		System.out.println("The folder to be created" + folderName);
		new File(folderName).mkdirs();
		try {
			writer = new PrintWriter(folderName+ File.separator + "master.html", "UTF-8");
			this.initializemaster(writer);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void initializemaster(PrintWriter writer) {
		// TODO Auto-generated method stub
		writer.println("");
	}

	public void startTest(String testname)
	{
//		if(t)
		//create file in the directory for the logging purpose if not exist
			
			
			
		//write the file header and all other details
			
		
	}
	
	public void endTest()
	{
		
		//stop writing to file save file
	}
	
	
	public void passedstmt(String buffer)
	{
		//write buffer line to the file for the output
	}
	
	public void failedstmt(String buffer)
	{
		// write buffet line to the f
	}
	
	
	/*******
	 * prefer the first argument to be actual valkue and second one to the the expected value
	 * @param str1
	 * @param str2
	 */
	public void validate(String str1, String str2)
	{
//		if(str1.equals(str2))
		
		//write the file implementation with tags red bg for errors and blue for warning
	}
	
	
	
	
	
	
	
	
	
	
	

	public static Reporter accessReporter() {
		if (null == objReporter) {
			synchronized (Reporter.class) {
				if (null == objReporter) {
					objReporter = new Reporter();
				}
			}
		}
		return objReporter;
	}
	

	/**************************************************************
	 * Get log directory path
	 **************************************************************/
	public String getLogDirectoryPath() {
		String dirname = "LOG_" + setCurrentTime();
		String path = null;
		String cwd = System.getProperty("user.dir");
		Pattern pattern = Pattern.compile("(.*)XSD(.*)");
		Matcher matcher = pattern.matcher(cwd);
		if (matcher.matches())
			path = matcher.group(1).toString() + "XSD" + File.separator + "src" + File.separator + "main"
					+ File.separator + "java" + File.separator + "logs";
		logDir = path + File.separator + dirname;
		return logDir;
	}

	/**************************************************************
	 * Create Log Directory
	 **************************************************************/
	public void createLogDirectory(String moudule) {
		new File(getLogDirectoryPath()).mkdir();
		this.setlog4j(Reporter.logDir + File.separator + moudule + ".txt");
	}
	
	/**************************************************************
	 * Get current time stamp : Instantiate a Date object
	 **************************************************************/
	public String setCurrentTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		return dateFormat.format(date);
	}

	private final static Logger log = Logger.getLogger(Reporter.class);

	public void addreport(String msg){
		log.info(msg);

	}

	public void addreport(String msg , boolean contion){
		if(contion == true){
			log.info("[ PASS ] : " + msg);
		} else {
			log.error("[ FAIL ] : " +msg);
		}
		Assert.assertTrue(contion, msg);
	}
	
	public void addreport(String msg , String error){
		log.fatal("[ Exception ] : Message : " + msg + " : Error : " + error);
	}
	
	/*************************************************************************
	 *  Set property for logs.
	 *************************************************************************/
	public void setlog4j(String Path){
		PatternLayout layout = new PatternLayout();
		String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
		layout.setConversionPattern(conversionPattern);
		
		// creates console appender
		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(layout);
		consoleAppender.activateOptions();
	
		// creates file appender
		FileAppender fileAppender = new FileAppender();
		fileAppender.setFile(Path);
		fileAppender.setLayout(layout);
		fileAppender.activateOptions();
		
		// configures the root logger
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);
//		rootLogger.addAppender(consoleAppender);
		rootLogger.addAppender(fileAppender);
	}
	
	/*************************************************************************
	 * Place all testNG files in log area.
	 *************************************************************************/

}
