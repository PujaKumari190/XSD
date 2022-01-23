package com.interra.Library;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.apache.commons.io.FileUtils;


public class CommonUtility {

	
	private static volatile CommonUtility objCommonUtility = null;

	public static CommonUtility accessCommonUtility() {
		if (null == objCommonUtility) {
			synchronized (CommonUtility.class) {
				if (null == objCommonUtility) {
					objCommonUtility = new CommonUtility();
				}
			}
		}
		return objCommonUtility;
	}

	public HashMap<String, String> getBatonFeature()
	{
		HashMap<String, String> featurevalues  = new HashMap<String, String>();
		
		String path = "";
		
//		if()
		
		
		
		return featurevalues;
	}
	
	
	
	/************************************************************
	 * This is use to get system IP.
	 ***********************************************************/
	public String getSystemIP() {
		String IP = null;
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			IP = ipAddr.getHostAddress();
			return IP.toString();
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		return IP;
	}

	/************************************************************
	 * This is use to get user name.
	 ***********************************************************/
	public String getCurrentUserName() {
		try {
			return System.getProperty("user.name");
		} catch (Exception error) {
			System.out.println(error.toString());
			return null;
		}
	}



	/*****************************************************************************
	 * API return path of resource location
	 *****************************************************************************/
	public String getResourcePath() {
		String path = null;
		String cwd = System.getProperty("user.dir");
		Pattern pattern = Pattern.compile("(.*)XSD(.*)");
		Matcher matcher = pattern.matcher(cwd);
		if (matcher.matches())
			path = matcher.group(1).toString() + "XSD" + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		else {
			System.out.println("FATAL : Incorrect path has been set for the project, kindly fix it, or might be other issue.");
			System.exit(1);
		}
		return path;
	}

	
	/**************************************************************************************
	 * This method is used to Copy files form one Location to Another location
	 **************************************************************************************/
//	public void filecopy(String source,String destination){
//		File source1 = new File(source);
//		File dest1 = new File(destination);
//		try {
//			FileUtils.copyFileToDirectory(source1, dest1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/***************************************************************************************
	 * This method is used to Copy whole content  form one Location to Another location
	 ***************************************************************************************/
//	public void directorycontentcopy(String source,String destination){
//		File source1 = new File(source);
//		File dest1 = new File(destination);
//		try {
//			FileUtils.copyDirectory(source1, dest1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}