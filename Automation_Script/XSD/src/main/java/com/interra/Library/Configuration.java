package com.interra.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
//import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.google.gson.Gson;
import com.interra.Library.CommonUtility;

@JsonInclude(Include.NON_NULL)
public class Configuration {
	
	final static Logger logger = Logger.getLogger(Configuration.class);
	private static Config config;
	private static volatile Configuration objStream = null;

	public static Configuration accessConfiguration() {
		if (null == objStream) {
				synchronized (Configuration.class) {
					if (null == objStream) {
						objStream = new Configuration();
					}
				}
		
			try {
				File configFile = new File(CommonUtility.accessCommonUtility().getResourcePath() + File.separator + "doc"
						+ File.separator + "Configuration.yaml");
				Yaml yaml = new Yaml();
				InputStream in = new FileInputStream(configFile);
				Reader reader = new InputStreamReader(in, "UTF8");
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) yaml.load(reader);
	
				JSONObject jsonObject = new JSONObject(map);
				config = new Gson().fromJson(jsonObject.toString(), Config.class);
		
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return objStream;
		}else
			return objStream;
	}
	
	/** @return streams path */
	public String getXMLPath(String name) {
		return config.getXMLPath().get(name);
	}
	/** @return streams path */
	public String getStreamsPath(String name) {
		return config.getSharedStreamPath();
	}
	
	/** @return Shared Location*/
	public String getSharedLocation() {
		return config.getSharedLocation();
	}
	
	/** @return Shared Location User Name */
	public String getSharedLocationUserName() {
		return config.getSharedLocationUserName();
	}
	
	/** @return Shared Location Password */
	public String getSharedLocationPassword( ) {
		return config.getSharedLocationPassword();
	}
	
	/** @return FTPLocation */
	public String getFTPLocation( ) {
		return config.getFTPLocation();
	}
	
	/** @return FTPLocation */
	public String getFTPLocationUserName( ) {
		return config.getFTPLocationUserName();
	}
	
	/** @return FTPLocation */
	public String getFTPLocationPassword( ) {
		return config.getFTPLocationPassword();
	}
	
	/** @return streams Location */
	public String getStreamLocation( ) {
		return config.getStreamLocation();
	}
	
	/** @return Stream Location User Name */
	public String getStreamLocationUserName( ) {
		return config.getStreamLocationUserName(); 
	}
	
	/** @return Stream Locatio Password */
	public String getStreamLocationPassword( ) {
		return config.getStreamLocationPassword();
	}
	
	public String getBaseURL(){
		return config.getBaseURL();
	}
	public String getBatonHomeURL(){
		return config.getBatonHOmeURL();
	}
	
	public String getStreamPath(String name) {
		return config.getStreamsPath().get(name);
	}
	
	public String getTestPlan(String name) {
		return config.getTestPlans().get(name);
	}
	public String getFTPStreamPath(String name) {
		return config.getFtpStreams().get(name);
	}
	public String getFolderPathinSharedLocation(String name) {
		return config.getFolderPath_SharedLocation();
	}

	public String getLocalChecker() {
		return config.getLocalChecker();
	}
	
	public String getRemoteChecker() {
		return config.getRemoteChecker();
	}
	
	public String getLocalLocation(){
		return config.getLocalLocation();
	}
	
	public String getRetryCount(){
		return config.getRetryCount();
	}
	

}