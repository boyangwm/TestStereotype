package edu.wm.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.wm.constants.ConfigConstants;
import edu.wm.exception.ProjectNotExistException;
import edu.wm.exception.ReadingFileException;

public class Run {
	
	
	
	
	public static void main(String [] args){
		
		
		System.out.println("Run TestStereotypeAnalyzer... ");
		MyLog.initLog();
		
		try {
			PrintStream outStream = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(outStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
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
		
		System.out.println("\n");
		analyzer.printSummary();
		//System.out.println("Done. Thanks.");
		
	}

}
