package edu.wm.core;

import java.nio.file.Path;
import java.nio.file.Paths;

import edu.wm.exception.ProjectNotExistException;

public class Run {
	public static void main(String [] args){
		
		
		System.out.println("Run TestStereotypeAnalyzer... ");
		
		
		
		
		//Analyze project under the folder ConfigConstants.projectLoc
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyze(ConfigConstants.projectLoc);
		} catch (ProjectNotExistException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		
		System.out.println("Done. Thanks.");
		
	}

}
