package com.interra.XSD;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.testng.annotations.Test;

import com.interra.Base.Base;
import com.interra.Base.Logger;
import com.interra.Controller.LogManager;
import com.interra.Library.Configuration;

import generated.ChecksType;
import generated.Customchecks;
import generated.ObjectFactory;
import generated.Streamnode;
import generated.TaskReport;

public class XML6 extends Base {
static ArrayList<String> listofFiles = new ArrayList<String>();
	
	public static void setLog() {
		LogManager.accessLogManager().createLogDirectory("XML6");
	}
	@Test
	public void XML5() {
		try {
			setLog();
			
			/**********************
			 * Creating List of All the XMLs inside a folder
			 ***********************/
			String XmlPath = Configuration.accessConfiguration().getXMLPath("XML_6");

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			TaskReport taskReport = (TaskReport) unmarshaller.unmarshal(new File(XmlPath));
			
			/**********************
			 * Parse Task Report TAG Information
			 ***********************/
			System.out.println(" ###### Printing Items of TaskReport Tags #####");
			/**********************
			 * Parse Top Level tag information
			 ***********************/
			
			System.out.println("toplevelinfo >> summary : " + taskReport.getToplevelinfo().getSummary());
			List<Streamnode> listofStreamsNodes = taskReport.getStreamnode();
			System.out.println(listofStreamsNodes.size());
		    System.out.println(listofStreamsNodes);
		    Logger.accessLogger().addReport("Total number of streamnode = 19" , 1,"EQ",listofStreamsNodes.size()==19?1:0, 1);
		    Streamnode streamnode2 = listofStreamsNodes.get(2);
		    int flag = 0;
		    if(streamnode2.getFileName().toString().equals("B000016581S0_1460.ismv") && streamnode2.getFolderName().toString().equals("B000016581S0_1460.ismv_d") && streamnode2.getStreamName().toString().equals("H264 Video")){
		    	flag = 1;
		    }
		    Logger.accessLogger().addReport("streamnode>>(FileName = B000016581S0_1460.ismv, FolderName = B000016581S0_1460.ismv_d, name = H264 Video)::" , 1,
					"EQ", flag, 1);
	           List<Object> secondStreamnodeChild = streamnode2.getErrorsOrInfoOrSummary();
        	   generated.Errors errorsObj = (generated.Errors) secondStreamnodeChild.get(0);
        	   for(Customchecks errorcustom1: errorsObj.getCustomchecks()) {
        		   for(ChecksType errortypes : errorcustom1.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks()){
        			  if(errortypes.getName().toString().equalsIgnoreCase("Burnt-in Subtitles") && errortypes.getSectionName().toString().equalsIgnoreCase("Common")){
        				  System.out.println("correct");
        			  }
        				  
        		   }
        	   }
		}
		catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}


}
