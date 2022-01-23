package com.interra.Base;

public class Log {
	
	public static String logpath = System.getenv("user.dir"); 
	
	public static void loginfo(String msg)
	{
		System.out.println("Log path is " + logpath);
	}
	
	public static void logsucess(String msg)
	{
		
	}
	
	public static void logfailure(String msg)
	{
		
	}
	
	public static void logwarning(String msg)
	{
		
	}
	
	public static void logexception(String msg)
	{
		
	}
	
/**
 * 
 * @param message the message to be printed for  this validation in the log
 * @param expected 
 * @param actual
 * @return
 */
	public static boolean validate(String message,String expected, String actual)
	{ boolean flag = false;
		
		if(expected.equals(actual))
		{
			//addd to db
			// file
		}
		else
		{
			// dba nd file
		}
	  return flag;
	}
	
	public static boolean validate(String message,int expected, int actual)
	{ boolean flag = false;
		
		if(expected == actual)
		{
			//addd to db
			// file
		}
		else
		{
			// dba nd file
		}
	  return flag;
	}
	
	public static boolean validate(String message,boolean expected, boolean actual)
	{ boolean flag = false;
		
		if(expected==actual)
		{
			//add to db		//file
		}
		else
		{
			// dba nd file
		}
	  return flag;
	}

	public static void dumplogtofile()
	{ System.out.println("STarting the dumping to the txzt log file here!");
		
		
	}
}
