package edu.wm.core;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import edu.wm.exception.ProjectNotExistException;

public class TestStereotypeAnalyzer {

	
	//The project location
	public String projectLoc = "";

	
	//All java files in the project 
	public HashSet<String> javaFiles = new HashSet<String>();


	
	
	public void loadFilesInfo(String projectLoc) throws ProjectNotExistException{
		this.projectLoc = projectLoc;
		try{
			//iterate the project and find all java files 
			Collection<File> files = FileUtils.listFiles(new File(this.projectLoc), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
			for (File file : files) {
				if(file.getName().endsWith(".java")){
					javaFiles.add(file.getAbsolutePath());
				}
			}
			
		}catch(IllegalArgumentException exp){
			throw new ProjectNotExistException(projectLoc);
		}
	}
	
	
	
	public void analyze(String projectLoc) throws ProjectNotExistException{
		loadFilesInfo(projectLoc);
		
	}

}
