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
import generated.Field;
import generated.Field.BroadcastSystem;
import generated.Field.CbLevels.SignalLevelMaxAttr;
import generated.Field.CbLevels.SignalLevelMinAttr;
import generated.Field.NewBitDepths;
import generated.Field.NewBitDepths.NewBitDepth;
import generated.Field.PackageTrackOrder;
import generated.Field.TimeCodeTrack;
import generated.Field.YLevels;
import generated.Info;
import generated.ObjectFactory;
import generated.Streamnode;
import generated.Substream;
import generated.TaskReport;

public class XML5 extends Base{
	static ArrayList<String> listofFiles = new ArrayList<String>();
	
	public static void setLog() {
		LogManager.accessLogManager().createLogDirectory("XML5");
	}
	@Test
		public void XML5() {

			try {
				setLog();
				
				/**********************
				 * Creating List of All the XMLs inside a folder
				 ***********************/
				String XmlPath = Configuration.accessConfiguration().getXMLPath("XML_5");

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
				int systemItemcount = 0;
				int packageCount = 0;
				List<Streamnode> listofStreamsNodes = taskReport.getStreamnode();
				System.out.println(listofStreamsNodes.size());
			    System.out.println(listofStreamsNodes);
			    Logger.accessLogger().addReport("Total number of streamnode = 5" , 1,"EQ",listofStreamsNodes.size()==5?1:0, 1);
			    Streamnode streamnode1 = listofStreamsNodes.get(1);
		           List<Object> secondStreamnodeChild = streamnode1.getErrorsOrInfoOrSummary();
		           generated.Substream substreamobj = (Substream) secondStreamnodeChild.get(3);
		           System.out.println(substreamobj.toString().length());
		           if(substreamobj.getError().toString().equals("20") && substreamobj.getFileName().toString().equalsIgnoreCase("tos_video.mxf") && substreamobj.getLabel().toString().equalsIgnoreCase("MXF")){
		        	   List<Object> subStreamchild = substreamobj.getErrorsOrInfoOrSummary();
		        	   generated.Info info = (Info) subStreamchild.get(1);
			        	inner:   for(Field fieldobj : info.getField()){
			        		   for(Object objfieldentry : fieldobj.getEntry()){
			        			   System.out.println(objfieldentry);
		        			   if(objfieldentry.toString().contains("SystemItem")){
		        				   try{
			        				   Field.SystemItem sysItem = (Field.SystemItem)objfieldentry;
			        				   List<Object> sysItemobj = sysItem.getSourcePackageIDAndTImecodeAndStartTimeCode();
			        				   System.out.println(sysItemobj);
			        				   Field.SystemItem.SourcePackageID sourcePackageId = (Field.SystemItem.SourcePackageID)sysItemobj.get(0);
			        				   Field.SystemItem.TImecode timeCode = (Field.SystemItem.TImecode)sysItemobj.get(1);
			        				   boolean timecodevalue = timeCode.getValue().matches("0-9");
			        				   if(sourcePackageId.getValue().equals("1") && timecodevalue==false){
			        					   systemItemcount = 1;
			        				   }
			        			   }
		        				   catch (Exception e) {
		        						System.out.println(e.getMessage());

		        					}
		        			   }
		        			   else if(objfieldentry.toString().contains("PackageTrackOrder")){
		        				   try{
		        				   Field.PackageTrackOrder packageTrackage = (Field.PackageTrackOrder)objfieldentry;
		        				   List<Object> packageTrackObj = packageTrackage.getNameAndOffsetAndCount();
		        				   PackageTrackOrder.Count countvalue = (PackageTrackOrder.Count)packageTrackObj.get(0);
		        				   PackageTrackOrder.Name namevalue = (PackageTrackOrder.Name)packageTrackObj.get(1);
		        				   if(countvalue.getValue().equals("1") && namevalue.getValue().equals("Material Package (Index : 1)")){
		        					   packageCount = 1;
		        					   break inner;
		        				   }
		        			   }
		        				   catch (Exception e) {
		        						System.out.println(e.getMessage());

		        					}
			        			   
			        		   }
			        		   }
		           }
		        	   Logger.accessLogger().addReport("streamnode>>substream(Error = 20 and fileName = tos_video.mxf,Label = MXF)>>info>>field>>SystemItem>>SourcePackageID(value=1)::" , 1,
								"EQ", systemItemcount, 1);
		        	   Logger.accessLogger().addReport("streamnode>>substream(Error = 20 and fileName = tos_video.mxf,Label = MXF)>>info>>field>>SystemItem>>TImecode(value is in string format)::" , 1,
								"EQ", systemItemcount, 1);
		        	   Logger.accessLogger().addReport("streamnode>>substream(Error = 20 and fileName = tos_video.mxf,Label = MXF)>>info>>field>>PackageTrackOrder>>(Count = 1 and Name = Material Package (Index : 1))::" , 1,
								"EQ", systemItemcount, 1);
		           }
		           Streamnode streamnode3 = listofStreamsNodes.get(2);
		           int streamnodecount = 0;
		           int MPEGcount = 0;
		           int burninCount = 0;
		           int chromabitCount = 0;
		           int chromadepthCount = 0;
		           int yLevelCount = 0;
		           int signalLevelMinAttrCount = 0;
		           int signalLevelMaxAttrCount = 0;
		           int timecodeCountmin = 0;
		           int timecodeCountmax = 0;
		           int gopCount = 0;
		           int gopchild = 0;
		           int chromaBitDepthCount = 0;
		           
		           if(streamnode3.getCPLName().toString().equals("b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml") && streamnode3.getFolderName().toString().equals("PictureTrack_1_d") && streamnode3.getId().toString().equals("2")){
		        	   streamnodecount = 1;
		        	   List<Object> streamnodeChild3 = streamnode3.getErrorsOrInfoOrSummary();
		        	   generated.Errors errorsObj = (generated.Errors) streamnodeChild3.get(0);
		        	   for(Customchecks errorcustom1: errorsObj.getCustomchecks()) {
		        		   for(ChecksType errortypes : errorcustom1.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks()){
		        			   if(errortypes.getName().equals("Motion JPEG2000 Video") && errortypes.getSectionName().equals("Common")){
		        				   MPEGcount = 1;
		        			   }
		        			    if(errortypes.getName().equals("Burnt-in Subtitles") && errortypes.getSectionName().equals("Common")){
		        				   burninCount = 1;
		        			   }
		        		   }
		        	   }
		        	   Info infoObj = (Info) streamnodeChild3.get(1);
		        	   for(Field fieldObj : infoObj.getField()){
		        		   for(Object fieldObjentry : fieldObj.getEntry()){
		        			   if(fieldObjentry.toString().contains("ChromaBitDepth")){
		        				   chromabitCount++;
		        			   }
		        			   else if(fieldObjentry.toString().contains("LumaBitDepth")){
		        				   chromabitCount ++;
		        			   }
		        			   else if(fieldObjentry.toString().contains("NewBitDepths")){
		        				   chromabitCount ++;
		        				   try{
		        				   Field.NewBitDepths newBitDepthentry = (Field.NewBitDepths) fieldObjentry;		        			
		        				   List<Field.NewBitDepths.NewBitDepth> newBitDepthList =  newBitDepthentry.getNewBitDepth();
		        				   Field.NewBitDepths.NewBitDepth newBitDepth = newBitDepthList.get(0);
		        				   List<Object> newBitDepthObj = newBitDepth.getNewLumaBitDepthAndNewChromaBitDepth();
		        				   Field.NewBitDepths.NewBitDepth.NewChromaBitDepth newChromaBitDepth = (Field.NewBitDepths.NewBitDepth.NewChromaBitDepth)newBitDepthObj.get(0);
		        				   Field.NewBitDepths.NewBitDepth.NewLumaBitDepth newLumaBitDepth = (Field.NewBitDepths.NewBitDepth.NewLumaBitDepth)newBitDepthObj.get(1);
		        				   if(newChromaBitDepth.getValue().toString().equals(newLumaBitDepth.getValue())){
		        					   chromaBitDepthCount = 1;
		        				   }
		        			   }
		        				   catch (Exception e) {
		        						System.out.println(e.getMessage());

		        					}
		        				   
	        			   }
		        			   else if(fieldObjentry.toString().contains("YLevels")){
		        				   try{
		        				    Field.YLevels yLevel = (Field.YLevels)fieldObjentry;
		        				    List<Object> yLevelEntry= yLevel.getMinAndMaxAndSignalLevelMinAttr();
		        				    YLevels.Min min = (YLevels.Min)yLevelEntry.get(0);
		        				    YLevels.Max max = (YLevels.Max)yLevelEntry.get(1);
		        				    YLevels.SignalLevelMinAttr minattr = (YLevels.SignalLevelMinAttr)yLevelEntry.get(2);
		        				    YLevels.SignalLevelMaxAttr maxattr = (YLevels.SignalLevelMaxAttr)yLevelEntry.get(3);
		        				    if(min.getValue().toString().equals("0") && max.getValue().toString().equals("3400")){
		        				    	yLevelCount = 1;
		        				    }
		        				    if(minattr.toString().contains("SignalLevelMinAttr")){
		        				    List<Object> minattrObj = minattr.getLevelAndIreAndMv();
		        				    YLevels.SignalLevelMinAttr.Level  level = (YLevels.SignalLevelMinAttr.Level)minattrObj.get(0);
		        				    YLevels.SignalLevelMinAttr.Ire  ire = (YLevels.SignalLevelMinAttr.Ire)minattrObj.get(1);
		        				    YLevels.SignalLevelMinAttr.Mv  mv = (YLevels.SignalLevelMinAttr.Mv)minattrObj.get(2);
		        				    YLevels.SignalLevelMinAttr.SignalLevelTimeCodes signalLevelTimecode = (YLevels.SignalLevelMinAttr.SignalLevelTimeCodes)minattrObj.get(3);
		        				    System.out.println(signalLevelTimecode);
		        				    List<YLevels.SignalLevelMinAttr.SignalLevelTimeCodes.Timecode> timecodeObj = signalLevelTimecode.getTimecode();
		        				    YLevels.SignalLevelMinAttr.SignalLevelTimeCodes.Timecode timecode = timecodeObj.get(0);
		        				    System.out.println(timecode);
		        				    if(timecode.getValue().toString().equals("01:00:00:00")){
		        				    	timecodeCountmin =1;
		        				    }
		        				    if(level.getValue().toString().equals("0") && ire.getValue().toString().equals("7.50") && mv.getValue().toString().equals("54.00")){
		        				    	signalLevelMinAttrCount = 1;
		        				    }
		        				    }
		        				    if(maxattr.toString().contains("SignalLevelMaxAttr")){
		        				    	List<Object> maxattrObj = maxattr.getLevelAndIreAndMv();
		        				    	YLevels.SignalLevelMaxAttr.Level  maxlevel = (YLevels.SignalLevelMaxAttr.Level)maxattrObj.get(0);
		        				    	YLevels.SignalLevelMaxAttr.Ire  maxire = (YLevels.SignalLevelMaxAttr.Ire)maxattrObj.get(1);
		        				    	YLevels.SignalLevelMaxAttr.Mv  maxmv = (YLevels.SignalLevelMaxAttr.Mv)maxattrObj.get(2);
		        				    	YLevels.SignalLevelMaxAttr.SignalLevelTimeCodes signalLevelTimecodemax = (YLevels.SignalLevelMaxAttr.SignalLevelTimeCodes)maxattrObj.get(3);
		        				    	List<YLevels.SignalLevelMaxAttr.SignalLevelTimeCodes.Timecode> timecodeObjmax = signalLevelTimecodemax.getTimecode();
		        				    	YLevels.SignalLevelMaxAttr.SignalLevelTimeCodes.Timecode timecodemax = timecodeObjmax.get(0);
		        				    	if(timecodemax.getValue().toString().equals("01:00:05:21")){
		        				    		timecodeCountmax =1;
		        				    	}
		        				    	if(maxlevel.getValue().toString().equals("3400") && maxire.getValue().toString().equals("84.30") && maxmv.getValue().toString().equals("601.99")){
		        				    		signalLevelMaxAttrCount = 1;
		        				    	}
		        				    }
		        				    
		        			   }
		        			   
		        			   catch (Exception e) {
		        					System.out.println(e.getMessage());

		        				}
		        		   }
		        			   else if(fieldObjentry.toString().contains("GOPStructures")){
		        				   try{
		        				   gopchild ++;
		        				   Field.GOPStructures gopStructureEntry = (Field.GOPStructures)fieldObjentry;
		        				   List<Field.GOPStructures.GOPStructure> gopStructureObj = gopStructureEntry.getGOPStructure();
		        				   Field.GOPStructures.GOPStructure gobstructure = gopStructureObj.get(0);
		        				   List<Object> gobStructureList = gobstructure.getMRangeAndMminAndMmax();
		        				   Field.GOPStructures.GOPStructure.M gopM = (Field.GOPStructures.GOPStructure.M)gobStructureList.get(0);
		        				   Field.GOPStructures.GOPStructure.N gopN = (Field.GOPStructures.GOPStructure.N)gobStructureList.get(1);
		        				   Field.GOPStructures.GOPStructure.Coverage coverage = (Field.GOPStructures.GOPStructure.Coverage)gobStructureList.get(2);
		        				   if(gopM.getValue().toString().equals(gopN.getValue()) && coverage.getValue().toString().equals("100.0")){
		        					   gopCount = 1;
		        				   }
		        				   }
		        				   catch (Exception e) {
		        						System.out.println(e.getMessage());

		        					}
		        			   }
		        			   else if(fieldObjentry.toString().contains("AverageGOPLength")){
		        				   Field.AverageGOPLength gopLength = (Field.AverageGOPLength)fieldObjentry;
		        				   gopchild++;
		        			   }
		        			   else if(fieldObjentry.toString().contains("GOPCount")){
		        				   try{
		        				   gopchild++;
		        				   Field.GOPCount gopCountValue = (Field.GOPCount)fieldObjentry;
		        				   Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>GOPCount(value = 500)::" , 1,
		   								"EQ", gopCountValue.getValue().toString().equals("500")? 1:0, 1);
		        				   }
		        				   catch (Exception e) {
		        						System.out.println(e.getMessage());

		        					}
		        			   }
		        			   else if(fieldObjentry.toString().contains("MaxGOPLength")){
		        				   gopchild++;
		        			   }
		        			   else if(fieldObjentry.toString().contains("MinGOPLength")){
		        				   gopchild++;
		        			   }
		        		   }
		        	   }
		        	   Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)::" , 1,
								"EQ", systemItemcount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>errors>>customchecks>>MJPEG2000videochecks(name = Motion JPEG2000 Video, SectionName = common )::" , 1,
								"EQ", systemItemcount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>errors>>customchecks>>burntinsubtitlechecks(name = Burnt-in Subtitles, SectionName = common )::" , 1,
								"EQ", systemItemcount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>(contains ChromaBitDepth, LumaBitDepth and NewBitDepth)::" , 1,
								"EQ", chromabitCount, 3);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>NewBitDepths>>NewBitDepth(NewChromaBitDepth = 12 and NewLumaBitDepth = 12)::" , 1,
								"EQ", chromaBitDepthCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels(min = 0 and max = 3400)::" , 1,
								"EQ", yLevelCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels>>SignalLevelMinAttr(level=0,ire=7.50,mv=54.00)::" , 1,
								"EQ", signalLevelMinAttrCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels>>SignalLevelMaxAttr(level=3400,ire=84.30,mv=601.99)::" , 1,
								"EQ", signalLevelMinAttrCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels>>SignalLevelMaxAttr(level=3400,ire=84.30,mv=601.99)::" , 1,
								"EQ", signalLevelMinAttrCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels>>SignalLevelMinAttr>>SignalLevelTimeCodes(timecode=01:00:00:00)::" , 1,
								"EQ", signalLevelMinAttrCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>YLevels>>SignalLevelMaxAttr>>SignalLevelTimeCodes(timecode=01:00:05:21)::" , 1,
								"EQ", signalLevelMaxAttrCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>GOPStructures>>GOPStructure>>(M and N value are equal and coverage = 100.0)::" , 1,
								"EQ", gopCount, 1);
			           Logger.accessLogger().addReport("Third streamnode>>(CPLName = b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= PictureTrack_1, ID = 2)>>info>>field>>(contains GOPStructures,AverageGOPLength,GOPCount,MaxGOPLength and MinGOPLength)::" , 1,
								"EQ", gopchild, 5);
		           }
		           Streamnode streamnode4 = listofStreamsNodes.get(4);
		           int truePeakStructCount = 0;
		           int firsttruePeak = 0;
		           System.out.println(streamnode4.getStreamName());
		           if(streamnode4.getCPLName().toString().equals("b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml") && streamnode4.getFolderName().toString().equals("SoundTrack_1_d") && streamnode4.getStreamName().toString().equalsIgnoreCase("BWF Audio")){
		             List<Object> stramChild4 = streamnode4.getErrorsOrInfoOrSummary();
		             Info infoObj4 = (Info) stramChild4.get(1);
		        	   for(Field fieldObj : infoObj4.getField()){
		        		   for(Object fieldObjentry : fieldObj.getEntry()){
		        			   if( fieldObjentry.toString().contains("TruePeakStruct")){
		        				   truePeakStructCount++;
		        			   if(firsttruePeak==0){
		        				   Field.TruePeakStruct truePeakStruct = (Field.TruePeakStruct)fieldObjentry;
		        				   List<Object> truePeakStructObj = truePeakStruct.getLevelAndChannelAndTimecode();
		        				   Field.TruePeakStruct.Channel channel = (Field.TruePeakStruct.Channel)truePeakStructObj.get(0);
		        				   Field.TruePeakStruct.Level level = (Field.TruePeakStruct.Level)truePeakStructObj.get(1);
		        				   Field.TruePeakStruct.Timecode timecode = (Field.TruePeakStruct.Timecode)truePeakStructObj.get(2);
		        				   if(channel.getValue().toString().equals("0") && level.getValue().toString().equals("0.4") && timecode.getValue().toString().equals("00:00:09:834")){
		        					   firsttruePeak = 1;
		        				   }
		        			   }
		        		   }
		        	   }
		           }
		           System.out.println(truePeakStructCount);
		           Logger.accessLogger().addReport("Fifth streamnode>>(b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= SoundTrack_1_d, name = BWF Audio)>>info>>field>>(TruePeakStruct count = 10)::" , 1,
							"EQ", truePeakStructCount, 10);
		           Logger.accessLogger().addReport("Fifth streamnode>>(b5b465cc-1ed3-4497-8abc-023b09b5fc45_cpl.xml, FolderName= SoundTrack_1_d, name = BWF Audio)>>info>>field>>TruePeakStruct(channel = 0,level = 0.4,Timecode = 00:00:09:834)::" , 1,
							"EQ", firsttruePeak, 1);
			}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());

			}
	}

}
