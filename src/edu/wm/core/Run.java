package edu.wm.core;

import edu.wm.constants.ConfigConstants;
import edu.wm.exception.ProjectNotExistException;
import edu.wm.exception.ReadingFileException;

public class Run {
	public static void main(String [] args){
		
		
		System.out.println("Run TestStereotypeAnalyzer... ");
		MyLog.initLog();
		
		//Analyze project under the folder ConfigConstants.projectLoc
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyze(ConfigConstants.projectLoc);
		} catch (ProjectNotExistException e) {
			System.out.println("EXCEPTION : " + e.toString());
		} catch(ReadingFileException e){
			System.out.println("EXCEPTION :" +e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		analyzer.printTestType();
		analyzer.printSummary();
		System.out.println("Done. Thanks.");
		
	}

}
