package edu.wm.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.regex.Pattern;

import edu.wm.constants.ConfigConstants;
import edu.wm.exception.ProjectNotExistException;
import edu.wm.exception.ReadingFileException;

public class RunParas {
	public static void main(String [] args){


		System.out.println("Run TestStereotypeAnalyzer... ");
		MyLog.initLog();

		if(args.length != 2 && args.length != 3){
			System.out.println("Requires 2 or 3 parameters. ");
			MyLog.LOGGER.log(Level.SEVERE, "Requires 2 parameters. ");
		}else if(args.length == 2 ) {
			try {
				PrintStream outStream = new PrintStream(new FileOutputStream(args[1]));
				System.setOut(outStream);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			//Analyze project under the folder ConfigConstants.projectLoc
			TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
			try {
				analyzer.analyze(args[0]);
			} catch (ProjectNotExistException e) {
				System.out.println("EXCEPTION : " + e.toString());
				MyLog.LOGGER.log(Level.SEVERE, "EXCEPTION : " + e.toString());
			} catch(ReadingFileException e){
				System.out.println("EXCEPTION :" +e.toString());
				MyLog.LOGGER.log(Level.SEVERE, "EXCEPTION : " + e.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			analyzer.printTestType();

			System.out.println("\n");
			analyzer.printSummary();
			//System.out.println("Done. Thanks.");
		} else{
			//print out summary 1
			try {
				PrintStream outStream = new PrintStream(new FileOutputStream(args[1]));
				System.setOut(outStream);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Analyze project under the folder ConfigConstants.projectLoc
			TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
			try {
				analyzer.analyze(args[0]);
			} catch (ProjectNotExistException e) {
				System.out.println("EXCEPTION : " + e.toString());
				MyLog.LOGGER.log(Level.SEVERE, "EXCEPTION : " + e.toString());
			} catch(ReadingFileException e){
				System.out.println("EXCEPTION :" +e.toString());
				MyLog.LOGGER.log(Level.SEVERE, "EXCEPTION : " + e.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			analyzer.printTestType();

			System.out.println("\n");
			analyzer.printSummary();
			
			
			
			

			//print out summary 2
			String projectName = "";
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String [] projectNames = args[0].split(pattern);
			for(int i = projectNames.length -1; i >= 0 ; i--){
				if(!projectName.equals("")){
					break;
				}
				projectName = projectNames[i];
			}
					
			
			try {
				PrintStream outStream = new PrintStream(new FileOutputStream(args[2]));
				System.setOut(outStream);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			analyzer.printXML(projectName);
		}

	}

}
