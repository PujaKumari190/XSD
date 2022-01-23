package com.interra.Library;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Config {

	/** @return streams path */
	public String getSharedStreamPath() {
		return sharedlocation;
	}

	/** @return Shared Location */
	public String getSharedLocation() {
		return SharedLocation;
	}

	/** @return Shared Location User Name */
	public String getSharedLocationUserName() {
		return SharedLocationUserName;
	}

	/** @return Shared Location Password */
	public String getSharedLocationPassword() {
		return SharedLocationPassword;
	}

	/** @return FTP Location */
	public String getFTPLocation() {
		return FTPLocation;
	}
	
	/** @return FTP Location User Name*/
	public String getFTPLocationUserName( ) {
		return FTPUserName;
	}
	
	/** @return FTP Location Password */
	public String getFTPLocationPassword( ) {
		return FTPPassword;
	}

	/** @return streams Location */
	public String getStreamLocation() {
		return StreamLocation;
	}

	/** @return Stream Location User Name */
	public String getStreamLocationUserName() {
		return StreamLocationUserName;
	}

	/** @return Stream Location Password */
	public String getStreamLocationPassword() {
		return StreamLocationPassword;
	}
	
	/** @return Base URL */
	public String getBaseURL(){
		return BaseURL;
	}
	
	/** @return Large Stream Path */
	public String getSharedLargeStreamPath(){
		return SharedLargeStreamPath;
	}
	
	/** @return Large Stream Path */
	public String getBatonHOmeURL(){
		return BatonHomeURL;
	}
	
	public String getFolderPath_SharedLocation(){
		return FolderpathSharedLocation;
	}
	
	/** @return streams path */
	public Map<String, String> getStreamsPath() {
		return streamPath;
	}
	
	public Map<String, String> getTestPlans() {
		return testPlans;
	}
	
	public Map<String, String> getFtpStreams() {
		return ftpTestStreams;
	}
	
	public String getLocalChecker(){
		return LocalChecker;
	}
	
	public String getRemoteChecker(){
		return RemoteChecker;
	}
	
	public String getLocalLocation() {
		return localLocation;
	}
	
	public String getRetryCount() {
		return retryCount;
	}
	
	public Map<String, String> getXMLPath(){
		return XMLS;
	}
	
	
	String sharedlocation;
	private Map<String, String> XMLS;
	
	private Map<String, String> testPlans;
	private Map<String, String> streamPath;
	private Map<String, String> ftpTestStreams;
	String SharedLocation;
	String SharedLocationUserName;
	String SharedLocationPassword;
	String FTPLocation;
	String FTPUserName;
	String FTPPassword;
	String StreamLocation;
	String StreamLocationUserName;
	String StreamLocationPassword;
	String secretkey;
	String accesskey;
	String bucketname;
	String BaseURL;
	String SharedLargeStreamPath;
	String BatonHomeURL;
	String FolderpathSharedLocation;
	String LocalChecker;
	String RemoteChecker;
	String localLocation;
	String retryCount;
	
}
