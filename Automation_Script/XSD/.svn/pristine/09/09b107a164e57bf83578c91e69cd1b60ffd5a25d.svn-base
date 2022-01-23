package com.interra.XSD;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.interra.Base.Base;
import com.interra.Base.Logger;
import com.interra.Library.Configuration;
import com.interra.Controller.LogManager;

import generated.ChecksType;
import generated.Conformancechecks;
import generated.Customchecks;
import generated.Errors;
import generated.Field;
import generated.Field.SpecifiedLayout.Segment;
import generated.Info;
import generated.ObjectFactory;
import generated.Param;
import generated.Params;
import generated.Streamnode;
import generated.TaskReport;

public class XML1 extends Base {

	static ArrayList<String> listofFiles = new ArrayList<String>();

	// private static void listFiles(String path) {
	// File folder = new File(path);
	// File[] files = folder.listFiles();
	// for (File file : files) {
	// if (file.isDirectory()) {
	// listFiles(file.getAbsolutePath());
	// }
	// if (file.isFile() && file.getName().endsWith(".xml")) {
	// listofFiles.add(file.getAbsolutePath());
	// System.out.println(file);
	// }
	// }
	// }
	public static void setLog() {
		LogManager.accessLogManager().createLogDirectory("XML_1");
		// Logger.accessLogger().setLogFile(logpath);
	}

	// public static void main(String[] args) {
	@Test
	public void XML1() {

		try {
			// String logpath=System.getProperty("user.dir")+ File.separator +
			// "Logs" + "_" + "abc";//get current syspath
			// Logger.accessLogger().setLogFile(logpath);
			setLog();

			/**********************
			 * Creating List of All the XMLs inside a folder
			 ***********************/
			String XmlPath = Configuration.accessConfiguration().getXMLPath("XML_1");

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			TaskReport taskReport = (TaskReport) unmarshaller.unmarshal(new File(XmlPath));

			/**********************
			 * Parse Task Report TAG Information
			 ***********************/
			System.out.println(" ###### Printing Items of TaskReport Tags #####");

			// Assert.assertEquals("abc", "acd");

			// Logger.accessLogger().addReport("The value of task Report is >>
			// ",1, "EQ", 1 , 0);
			// Logger.accessLogger().addReport("The value of task Report is >>
			// ",1, "EQ", 1 , 1);
			// Logger.accessLogger().addReport("The value of task Report is >>
			// ",1, "EQ", 1 , 0);
			// LogManager.accessLogManager().addreport("Exception Caught !!! ",
			// true);
			// try {
			// Logger.accessLogger().addReport("Validation of name in xml
			// returned on caliing export API .",true);
			//
			// } catch (Exception e) {
			// LogManager.accessLogManager().addreport("Exception Caught !!! " +
			// e.getMessage(), false);
			// }
			System.out.println(" ###### Printing Items of TaskReport Tags #####");

			// System.out.println("Task Report >> Encoding : " +
			// taskReport.getEncoding());
			// System.out.println("Task Report >> lastUpdate : " +
			// taskReport.getLastUpdate());
			// System.out.println("Task Report >> lastUpdate_ISO : " +
			// taskReport.getLastUpdateISO());

			/**********************
			 * Parse Top Level tag information
			 ***********************/
			
			
			System.out.println("toplevelinfo >> File path : " + taskReport.getToplevelinfo().getFilepath());
			String filePath = taskReport.getToplevelinfo().getFilepath();
			Logger.accessLogger().addReport("toplevelinfo >> File path:: " + filePath, 1, "EQ",
					filePath != null ? 1 : 0, 1);
			BigInteger errorinXML = taskReport.getToplevelinfo().getError();
			Logger.accessLogger().addReport("toplevelinfo >> Error:: " + errorinXML, 1, "EQ",
					errorinXML != null ? 1 : 0, 1);
			BigInteger warning = taskReport.getToplevelinfo().getWarning();
			Logger.accessLogger().addReport("toplevelinfo >> Warning:: " + warning, 1, "EQ", warning != null ? 1 : 0,
					1);
			String format = taskReport.getToplevelinfo().getFormat();
			Logger.accessLogger().addReport("toplevelinfo >> Format:: " + format, 1, "EQ", format != null ? 1 : 0, 1);

			// System.out.println("toplevelinfo >> Error : " +
			// taskReport.getToplevelinfo().getError());
			// System.out.println("toplevelinfo >> Warning : " +
			// taskReport.getToplevelinfo().getWarning());
			// System.out.println("toplevelinfo >> Format : " +
			// taskReport.getToplevelinfo().getFormat());

			/**********************
			 * Parse Hierarchy tag information
			 ***********************/
			// System.out.println("hierarchy >> value : " +
			// taskReport.getHierarchy().getValue());
			// System.out.println("hierarchy >> warning : " +
			// taskReport.getHierarchy().getWarning());
			// System.out.println("hierarchy >> Info : " +
			// taskReport.getHierarchy().getInfo());

			/**********************
			 * Info about Stream Nodes
			 ***********************/

			int streamNodeSize = taskReport.getStreamnode().size();
			Logger.accessLogger().addReport("Total Stream Nodes Present:: " + streamNodeSize, 1, "EQ",
					taskReport.getStreamnode().size() == 2 ? 1 : 0, 1);
			List<Streamnode> listofStreamsNodes = taskReport.getStreamnode();
			for (Streamnode item : listofStreamsNodes) {

				System.out.println(" streamnode >> Fetching Data for Stream name: " + item.getStreamName());
				Logger.accessLogger().addReport("streamnode>>Fetching Data for Stream name::" + item.getStreamName(), 1,
						"EQ", item.getStreamName() != null ? 1 : 0, 1);

				System.out.println(" streamnode >> Errors in StreamNode : " + item.getError());
				Logger.accessLogger().addReport("streamnode>>Error::" + item.getError(), 1, "EQ",
						item.getError() != null ? 1 : 0, 1);

				System.out.println(" streamnode >> id : " + item.getId());
				Logger.accessLogger().addReport(" streamnode >> id ::" + item.getId(), 1, "EQ",
						item.getId() != null ? 1 : 0, 1);

				Logger.accessLogger().addReport(" streamnode >> PARENT::" + item.getParent(), 1, "EQ",
						item.getParent() != null ? 1 : 0, 1);
			}

			/***********************
			 * Get Streamnode Info>>Fields>>ContentLayout>>specifiedLayout
			 * Info>>Fields>>ContentLayout>>actualLayout
			 **********************************/
			Logger.accessLogger().addReport(
					" #### Going to access StreamNode>>Info>>Field(ContentLayout)>>specifiedLayout>>segment(Type-Freeze)");
			Logger.accessLogger().addReport(
					" #### Going to access StreamNode>>Info>>Field(ContentLayout)>>actualLayout>>segment(Type-Black, FrameDuration-24)");
			int specifiedLayoutFlag = 0;
			int actualLayoutFlag = 0;
			for (Streamnode item : listofStreamsNodes) {
				
				
				List<Object> streamChildren = item.getErrorsOrInfoOrSummary();
				Info infoObj = (Info) streamChildren.get(1);
				for (Field fieldItems : infoObj.getField()) {
					System.out.println(fieldItems.getName());

					if (fieldItems.getName().equals("ContentLayout")) {
						Logger.accessLogger().addReport(
								"streamnode(" + item.getStreamName() + ")>>info>>field(ContentLayout)::", 1, "EQ",
								fieldItems.getName() != null ? 1 : 0, 1);
						for (Object objFieldEntry : fieldItems.getEntry()) {
							System.out.println(objFieldEntry.getClass());
							if (objFieldEntry.getClass().toString().contains("SpecifiedLayout")) {
								Field.SpecifiedLayout objFieldSL = (Field.SpecifiedLayout) objFieldEntry;
								List<Field.SpecifiedLayout.Segment> segmentList = objFieldSL.getSegment();
								for (Field.SpecifiedLayout.Segment objSegment : segmentList) {
									if (objSegment.getType().equals("Freeze")) {
										Logger.accessLogger().addReport(
												"streamnode(" + item.getStreamName() + ")>>info>>field("
														+ fieldItems.getName()
														+ ")>>SpecifiedLayout>>Segment(Type-Freeze)::",
												1, "EQ", objSegment.getType() != null ? 1 : 0, 1);
										specifiedLayoutFlag = 1;
									}
								}
							}
							if (objFieldEntry.getClass().toString().contains("ActualLayout")) {
								Field.ActualLayout objFieldSL = (Field.ActualLayout) objFieldEntry;
								List<Field.ActualLayout.Segment> segmentList = objFieldSL.getSegment();
								for (Field.ActualLayout.Segment objSegment : segmentList) {
									if (objSegment.getType().equals("Black")) {
										Logger.accessLogger().addReport(
												"streamnode(" + item.getStreamName() + ")>>info>>field("
														+ fieldItems.getName()
														+ ")>>ActualLayout>>Segment(Type-Black,FrameDuration-24)::",
												1, "EQ", objSegment.getFrameDuration() == "24" ? 1 : 0, 1);
										actualLayoutFlag = 1;
									}
								}
							}
						}
					}

				}
				Logger.accessLogger().addReport(
						"Going to access StreamNode>>Info>>Field(ContentLayout)>>specifiedLayout>>segment(Type-Freeze)",
						1, "EQ", specifiedLayoutFlag == 1 ? 1 : 0, 1);
				Logger.accessLogger().addReport(
						"Going to access StreamNode>>Info>>Field(ContentLayout)>>actualLayout>>segment(Type-Black, FrameDuration-24)",
						1, "EQ", actualLayoutFlag == 1 ? 1 : 0, 1);

				/***********************
				 * Getting access to Custom Checks
				 **********************************/

//				Logger.accessLogger().addReport(
//						" #### Going to access StreamNode>>Errors>>CustomChecks>>systemencodingchecks(name=System Level, sectionName=Common)>>segment(Type-Freeze)");
//				Logger.accessLogger().addReport(
//						" #### Going to access StreamNode>>Info>>Field(ContentLayout)>>actualLayout>>segment(Type-Black, FrameDuration-24)");
				int flag = 0;
				// int fdg = 0;

				Errors errorsObj = (Errors) streamChildren.get(0);
				outerCustomChecks: for (Customchecks check : errorsObj.getCustomchecks()) {

					System.out.println(check);
					System.out.println(check.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks().getClass());
					for (ChecksType checksTypeitem : check
							.getDecoded3DvideochecksOrGIFimagechecksOrCanopusvideochecks()) {
						Logger.accessLogger().addReport(
								"StreamNode>>Errors>>CustomChecks>>systemencodingchecks(sectionName=Common)", 1, "EQ",
								checksTypeitem.getSectionName().equals("Common") ? 1 : 0, 1);
						Logger.accessLogger().addReport(
								"StreamNode>>Errors>>CustomChecks>>systemencodingchecks(name=System Level)", 1, "EQ",
								checksTypeitem.getName().equals("System Level") ? 1 : 0, 1);
						for (generated.Error errorObj : checksTypeitem.getError()) {
							Logger.accessLogger().addReport(
									"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)", 1,
									"EQ", errorObj.getId().equals("SYS.0003.01") ? 1 : 0, 1);

							Logger.accessLogger().addReport(
									"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(item=VideoTracks)",
									1, "EQ", errorObj.getItem().equals("VideoTracks") ? 1 : 0, 1);
							for (Param paramsObj : errorObj.getParams().get(0).getParam()) {
								if (paramsObj.getName().equals("VideoTracks") && paramsObj.getValue().equals("9")) {
									flag = 1;
									break outerCustomChecks;
								}
							}
						}
					}
				}

				Logger.accessLogger().addReport(
						"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)>>params>>param(Name=VideoTracks)",
						1, "EQ", flag == 1 ? 1 : 0, 1);
				Logger.accessLogger().addReport(
						"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)>>params>>param(Value=9)",
						1, "EQ", flag == 1 ? 1 : 0, 1);
				
				
				
				/***********************
				 * Getting access to Conformance Checks
				 **********************************/

//				Logger.accessLogger().addReport(
//						" #### Going to access StreamNode>>Errors>>CustomChecks>>systemencodingchecks(name=System Level, sectionName=Common)>>segment(Type-Freeze)");
//				Logger.accessLogger().addReport(
//						" #### Going to access StreamNode>>Info>>Field(ContentLayout)>>actualLayout>>segment(Type-Black, FrameDuration-24)");
				flag = 0;
				// int fdg = 0;

				errorsObj = (Errors) streamChildren.get(0);
				outerConformanceChecks: for (Conformancechecks conformanceCheck : errorsObj.getConformancechecks()) {

					System.out.println(conformanceCheck);
					System.out.println(conformanceCheck.getBufferAnalysisChecksOrError().getClass());
					for (Object conformanceCheckChild : conformanceCheck.getBufferAnalysisChecksOrError()) {
						System.out.println(conformanceCheckChild);
						generated.Error error = (generated.Error)conformanceCheckChild;
						
						error.getCheckId();
						
						
//						for(true)
//						{
//							System.out.println(errorObj.getCheckId());
//						}
						
						
						
//						Logger.accessLogger().addReport(
//								"StreamNode>>Errors>>ConformanceChecks>>error(checkId=17302)", 1, "EQ",
//								checksTypeitem().equals("Common") ? 1 : 0, 1);
//						Logger.accessLogger().addReport(
//								"StreamNode>>Errors>>CustomChecks>>systemencodingchecks(name=System Level)", 1, "EQ",
//								checksTypeitem.getName().equals("System Level") ? 1 : 0, 1);
//						for (generated.Error errorObj : checksTypeitem.getError()) {
//							Logger.accessLogger().addReport(
//									"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)", 1,
//									"EQ", errorObj.getId().equals("SYS.0003.01") ? 1 : 0, 1);
//
//							Logger.accessLogger().addReport(
//									"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(item=VideoTracks)",
//									1, "EQ", errorObj.getItem().equals("VideoTracks") ? 1 : 0, 1);
//							for (Param paramsObj : errorObj.getParams().get(0).getParam()) {
//								if (paramsObj.getName().equals("VideoTracks") && paramsObj.getValue().equals(9)) {
//									flag = 1;
//									break outerCustomChecks;
//								}
//							}
//						}
					}
				}

				Logger.accessLogger().addReport(
						"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)>>params>>param(Name=VideoTracks)",
						1, "EQ", flag == 1 ? 1 : 0, 1);
				Logger.accessLogger().addReport(
						"StreamNode>>Errors>>CustomChecks>>systemencodingchecks>>error(id=SYS.0003.01)>>params>>param(Value=9)",
						1, "EQ", flag == 1 ? 1 : 0, 1);

				// for (Conformancechecks check :
				// errorsObj.getConformancechecks()) {
				// for (Object objitem : check.getBufferAnalysisChecksOrError())
				// {
				// if (objitem instanceof Error) {
				// Error error = (Error) objitem;
				// // System.out.println( "Check ID: " +
				// // error.getCheckId() + ".Description: " +
				// // error.getDescription());
				// }
				// }
				// }

				System.out.println(" streamnode >> Fetching Data for Stream name: " + item.getStreamName());
				Logger.accessLogger().addReport("streamnode>>Fetching Data for Stream name::" + item.getStreamName(), 1,
						"EQ", item.getStreamName() != null ? 1 : 0, 1);

				System.out.println(" streamnode >> Errors in StreamNode : " + item.getError());
				Logger.accessLogger().addReport("streamnode>>Error::" + item.getError(), 1, "EQ",
						item.getError() != null ? 1 : 0, 1);

				System.out.println(" streamnode >> id : " + item.getId());
				Logger.accessLogger().addReport(" streamnode >> id ::" + item.getId(), 1, "EQ",
						item.getId() != null ? 1 : 0, 1);

				Logger.accessLogger().addReport(" streamnode >> PARENT::" + item.getParent(), 1, "EQ",
						item.getParent() != null ? 1 : 0, 1);
			}
			Streamnode firstStream = taskReport.getStreamnode().get(0);
			System.out.println("Steam name: " + firstStream.getStreamName());
			System.out.println("Start TimeCode: " + firstStream.getStartTimecode());
			/*
			 * Ouput: Steam name: MPEG2 Transport Start TimeCode: 0
			 */
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

}
