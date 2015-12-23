package UnitTests;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import edu.wm.core.TestStereotypeAnalyzer;
import edu.wm.exception.ProjectNotExistException;

public class ConfigTests {
	
	@Test
	public void testLoadFileInfo(){
		//Get current relative path
		Path currentRelativePath = Paths.get("");
		
		//Durbodax
		String projectLoc = currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\Durbodax"; 
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		
		//Load File Info
		try {
			analyzer.loadFilesInfo(projectLoc);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		assertEquals(currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\Durbodax", analyzer.projectLoc);
		assertNotEquals(currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\HelloWorldJunit", analyzer.projectLoc);
	}
	
	
	
	
	@Test
	public void testLoadFileInfoSize(){
		//Get current relative path
		Path currentRelativePath = Paths.get("");
		
		//HelloWorldJunit
		String projectLoc = currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\HelloWorldJunit"; 
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		
		//Load File Info
		try {
			analyzer.loadFilesInfo(projectLoc);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		//assertEquals(3, analyzer.mapFileToUnit.size());
	}
	
	
	
	
	

}
