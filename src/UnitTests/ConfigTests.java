package UnitTests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

		Class[] cArg = new Class[1];
		cArg[0] = String.class;

		Method method;
		try {
			method = analyzer.getClass().getDeclaredMethod("loadFilesInfo", cArg);
			method.setAccessible(true);
			method.invoke(analyzer, projectLoc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		assertEquals(currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\Durbodax", analyzer.projectLoc);
		assertNotEquals(currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\HelloWorldJunit", analyzer.projectLoc);
	}



	@Test
	public void testTestCaseDetection(){
		//Get current relative path
		Path currentRelativePath = Paths.get("");

		//HelloWorldJunit
		String projectLoc = currentRelativePath.toAbsolutePath().toString() + "\\" + "Apps\\HelloWorldJunit"; 
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();

		Class[] cArg = new Class[1];
		cArg[0] = String.class;

		Method method;
		try {
			method = analyzer.getClass().getDeclaredMethod("loadFilesInfo", cArg);
			method.setAccessible(true);
			method.invoke(analyzer, projectLoc);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		assertEquals(3, analyzer.mapSignToTest.size());
	}


}
