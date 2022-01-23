package com.interra.XSD;

import java.io.File;
import java.lang.reflect.TypeVariable;
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
import generated.Errors;
import generated.Field;
import generated.Field.ProgramConfigDolbyE;
import generated.Field.ProgramConfigDolbyE.ProgramConfiguration;
import generated.Field.ProgramConfigDolbyE.ProgramSequence;
import generated.Info;
import generated.ObjectFactory;
import generated.Program;
import generated.Streamnode;
import generated.Summary;
import generated.Systemencodingchecks;
import generated.TaskReport;

public class XML3 extends Base {
	
	public static void setLog() {
		LogManager.accessLogManager().createLogDirectory("XML_3");
	}
	
	@Test
	public void XML3(){
		try{
			setLog();

			/**********************
			 * Creating List of All the XMLs inside a folder
			 ***********************/
			String XmlPath = Configuration.accessConfiguration().getXMLPath("XML_3");

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
			int parmname = 0;
			List<Streamnode> listofStreamsNodes = taskReport.getStreamnode();
		    System.out.println(listofStreamsNodes);
		outer :    for (Streamnode item : listofStreamsNodes) {
		    List<Object> streamChildren = item.getErrorsOrInfoOrSummary();
		    Errors errorsObj = (Errors) streamChildren.get(0);
		    if(parmname == 0){
		    	try{
		   for(Customchecks check : errorsObj.getCustomchecks()){
		    	for(ChecksType syscheck : check.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks()){
		    			for(generated.Error error1 : syscheck.getError()){
		    				for(generated.Params parmvalue: error1.getParams()){
		    					for(generated.Param parmvalue1: parmvalue.getParam()){
		    						boolean b = parmvalue1.getName().matches("0-9");
		    						System.out.println(b);
		    						if(b==false){
		    						parmname = 1;
		    						break outer;
		    						}
		    					
		    					}		    							    					
		    				}		    						    				
		    			}	    			
		    		}
		    	  }
		    	}
		    	catch (Exception e) {
					System.out.println(e.getMessage());
				}	
		    	
		    }
		    }
		    Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>systemencodingchecks>>error>>Params>>parm(name is in String type)::", 1,
					"EQ", parmname, 1);
		   
		    Streamnode streamnode1 = listofStreamsNodes.get(1);
		    int audioname = 0;
		    int genericaudiocount = 0;
		    int audiocheckcount = 0;
		    int Ebucodecount = 0;
		    List<Object> streamchild2 = streamnode1.getErrorsOrInfoOrSummary();
		    generated.Errors errorsObj1 = (generated.Errors) streamchild2.get(0);
		    for(Customchecks errorcustom1: errorsObj1.getCustomchecks()) {
		    	try{
		    	for(ChecksType genericaudio : errorcustom1.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks()){
		    		System.out.print(genericaudio);
		    		if(genericaudio.getClass().toString().contains("Genericaudiochecks")){
		    		if(genericaudio.getSectionName().equals("Common")){
		    			audioname = 1;
		    		}
		    		}
		    		else if(genericaudio.getClass().toString().contains("Decodedaudiochecks")){
		    			if(genericaudio.getName()!=null && genericaudio.getSectionName()!=null ){
		    				genericaudiocount = 1;
		    			}			
		    			
		    		}
		    		else if(genericaudio.getClass().toString().contains("DolbyEaudiochecks")){
		    			System.out.println(genericaudio.getName());
		    			if(genericaudio.getName().equals("DolbyE Audio")){
		    				audiocheckcount = 1;
		    				for(generated.Error error : genericaudio.getError()){
		    					if(error.getEBUCode()!=null && error.getEBUCode().equals("0068F")){
		    						Ebucodecount = 1;
		    					}
		    				}
		    			}	    			
		    		}
		    	}		    	
		    }
		    	catch (Exception e) {
		    		System.out.println(e.getMessage());
		    	}
		    	
		    	    	
		      }
		    Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>genericaudiochecks(section name=custom)::" + streamnode1.getFolderName(), 1,
					"EQ", audioname, 1);
		    Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>Decodedaudiochecks(name not null)::", 1,
					"EQ", genericaudiocount, 1);
			Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>Decodedaudiochecks(sectionName not null)::", 1,
					"EQ", genericaudiocount, 1);
			Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>DolbyEaudiochecks(name= DolbyE Audio)::", 1,
					"EQ", audiocheckcount, 1);
			Logger.accessLogger().addReport("streamnode>>errors>>customchecks>>DolbyEaudiochecks>>error(EBUCode=0068F)::", 1,
					"EQ", Ebucodecount, 1);
		    Info infoObj = (Info) streamchild2.get(1);
		    int count1 = 0;
		    try{
	 outer: for (Field fieldItems : infoObj.getField()){
		    	System.out.println(fieldItems.getOtherAttributes());
		    	for(Object programconficDolbyE : fieldItems.getEntry()){
		    	  if(programconficDolbyE.getClass().toString().contains("ProgramConfigDolbyE")){
		    		  count1 = 1;
		    		  Field.ProgramConfigDolbyE programConficobj = (Field.ProgramConfigDolbyE) programconficDolbyE;
		    		  System.out.println(programConficobj);
		    		  List<Object> programobj = programConficobj.getProgramConfigurationAndProgramSequence();
		    		  System.out.println(programobj.get(0));
		    		  System.out.println(programobj.get(1));
		    	       Field.ProgramConfigDolbyE.ProgramConfiguration programconfic = (Field.ProgramConfigDolbyE.ProgramConfiguration)programobj.get(0);
		    	       ProgramSequence programconseq = (ProgramSequence) programobj.get(1);
			    	      
		    	       Logger.accessLogger().addReport("streamnode>>errors>>info>>field>>ProgramConfigDolbyE>>ProgramConfiguration(value should not be null or it should be 0)::" + programconfic.getValue(), 1,
		    					"EQ", (programconfic.getValue()!=null || programconfic.getValue().equals("0")) ? 1:0, 1);
		    	       Logger.accessLogger().addReport("streamnode>>errors>>info>>field>>ProgramConfigDolbyE>>ProgramSequence(value should not be null or it shlould be 5.1+2 )::" + programconseq.getValue(), 1,
		    					"EQ", (programconseq.getValue()!=null || programconseq.getValue().equals("5.1+2")) ? 1:0, 1); 
		    	       break outer;
		    	  }  	  
		    	}
		    }	 
		    }
		    catch (Exception e) {
				System.out.println(e.getMessage());
			}
		    Program programobj = (Program) streamchild2.get(3);
		    int numberofprogObj = 0;
		    System.out.println(programobj);
		    System.out.println(programobj.getError());
		    System.out.println(programobj.getProgramId());
		    System.out.println(programobj.getWarning());
		    System.out.println(programobj.getError().toString().equals("15"));
		    System.out.println(programobj.getProgramId().toString().equals("1"));
		    System.out.println(programobj.getWarning().toString().equals("1"));
		    if(programobj.getError().toString().equals("15") && programobj.getProgramId().toString().equals("1") && programobj.getWarning().toString().equals("1"))
		    {
		    	numberofprogObj = 1;		    	
		    }
		    	
		    	Logger.accessLogger().addReport("streamnode>>program>>(contains Error,ProgramId and Warning with correct value)::" + programobj.getProgramId(), 1,
    					"EQ", numberofprogObj, 1);
		    	 System.out.println(programobj.getInfo());
		    	 List<Object> programobj1 = programobj.getInfoOrErrorsOrSummary();
		    	 Info programInfoObj = (Info) programobj1.get(0);
		    	 int loudness = 0;
	    		 int loudnessvalues = 0;
	    		 int progrmanloudnesscount = 0;
	    		 int mappingcount = 0;
		    	 loudNess:	for(Field fieldinfo : programInfoObj.getField()){
		    	 for( Object fieldobj: fieldinfo.getEntry()){
		    		     if(mappingcount == 0){
		    		      Field.Mapping mappingObj = (Field.Mapping) fieldobj;
		    		      System.out.println(mappingObj.getKey());
		    		      System.out.println(mappingObj.getValue());
		    		      if(mappingObj.getKey().toString().equalsIgnoreCase("M6") && mappingObj.getValue().toString().equalsIgnoreCase("4.13")){
		    		    	  mappingcount = 1;  
		    		      }
		    		     }
		    			 try{
		    			 if(fieldobj.getClass().toString().contains("ProgramLoudness") && progrmanloudnesscount==0){
		    				 progrmanloudnesscount =1; 
		    			 Field.ProgramLoudness programloudness = (Field.ProgramLoudness)fieldobj;
		    			 List<Object> programLoudnessItem = programloudness.getLevelAndStartAndEnd();
		    			 Field.ProgramLoudness.Speech speechobj = (Field.ProgramLoudness.Speech) programLoudnessItem.get(0);
		    			 System.out.println(speechobj);
		    			 Field.ProgramLoudness.Level levelobj = (Field.ProgramLoudness.Level) programLoudnessItem.get(1);
		    			 System.out.println(speechobj.toString().length());
		    			 if(speechobj.toString().length()>1 && levelobj.toString().length()>1){
		    				 loudness++; 
		    			 }
		    			 if(speechobj.getValue().toString().contains("88")){
		    				 loudness++; 
		    			 }
		    			 if(levelobj.getUnit().toString().contains("LKFS") && levelobj.getDesc().toString().contains("Can't be determined")){
		    				 loudness++;
		    			 }
		    			 
		    		 }
		    			 else if(fieldobj.getClass().toString().contains("LoudnessValues")){
		    				 Field.LoudnessValues loudnessvalue = (Field.LoudnessValues) fieldobj;
		    				 List<Object> loudnessvalueitem = loudnessvalue.getSpeechAndEndAndStart();
		    				 Field.LoudnessValues.LoudnessRange loudnessRangeobj = (Field.LoudnessValues.LoudnessRange) loudnessvalueitem.get(0);
		    				 Field.LoudnessValues.ProgramLoudness progralousnessitem = (Field.LoudnessValues.ProgramLoudness) loudnessvalueitem.get(1);
		    				 Field.LoudnessValues.End loudnessEnd = (Field.LoudnessValues.End) loudnessvalueitem.get(2);
		    				 Field.LoudnessValues.Start loudnessStart = (Field.LoudnessValues.Start) loudnessvalueitem.get(3);
		    				 if(loudnessRangeobj.getValue()!=null && progralousnessitem.getValue()!=null && loudnessEnd.getValue()!=null && loudnessStart.getValue()!=null){
		    					 loudnessvalues = 1;
		    				 }
		    			 }
		    			 System.out.println(loudness);
		    			 if(loudness ==3 && loudnessvalues==1){ 
		    				 break loudNess;
		    			 }
		    			 
		    		 }
		    			 catch (Exception e) {
		    					System.out.println(e.getMessage());
		    				}	 
		    	 }
		    	 
		}Logger.accessLogger().addReport("streamnode>>program>>info>>field>>ProgramLoudness>>Speech(length is grater than 1 and value = 88)::" , 1,
					"EQ", loudness, 3); 
			 Logger.accessLogger().addReport("streamnode>>program>>info>>field>>ProgramLoudness>>level(length is grater than 1 and Unit =LKFS)::" , 1,
  					"EQ", loudness, 3);
			 Logger.accessLogger().addReport("streamnode>>program>>info>>field>>LoudnessValues(has LoudnessRange,ProgramLoudness,end,start child tag and are not null)::" , 1,
	  					"EQ", loudness, 3);
			 Logger.accessLogger().addReport("streamnode>>program>>info>>field>>mapping(key=M6 and value=4.13)::" , 1,
	  					"EQ", mappingcount, 1);
		}
	
	  
	catch (Exception e) {
		System.out.println(e.getMessage());
	}

}
}
