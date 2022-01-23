package com.interra.Controller;

import java.io.File;
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



public class LogManager {

	private static volatile LogManager objLogManager = null;
	public static String logDir = null; 
	
//	public static String baseURL = "http://localhost:8080/Baton/api/";//Read from config file TODO
//	public static String baseURL = Configuration.accessConfiguration().getBaseURL();

	public static LogManager accessLogManager() {
		if (null == objLogManager) {
			synchronized (LogManager.class) {
				if (null == objLogManager) {
					objLogManager = new LogManager();
				}
			}
		}
		return objLogManager;
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
		this.setlog4j(LogManager.logDir + File.separator + moudule + ".txt");
	}
	
	/**************************************************************
	 * Get current time stamp : Instantiate a Date object
	 **************************************************************/
	public String setCurrentTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		return dateFormat.format(date);
	}

	private final static Logger log = Logger.getLogger(LogManager.class);

	public void addreport(String msg){
		System.out.println(msg );
		log.info(msg);
	}

	public void addreport(String msg , boolean condition){
		if(condition == true){
			System.out.println(msg +"[ PASS ] ");
			log.info("[ PASS ] : " + msg);
			Assert.assertEquals(true, true);
		} else {
			System.out.println(msg +"[ FAIL ] ");
			log.error("[ FAIL ] : " +msg);
			Assert.assertEquals(true, false);
		}
	
		
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
