package edu.wm.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.wm.Rules.JavaJunit4Collector;
import edu.wm.Rules.RuleCollector;
import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.MarkerAnnotationVisitor;
import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.ast.UtilAST;
import edu.wm.constants.TestAnnotation;
import edu.wm.constants.TestStereotype;
import edu.wm.exception.ProjectNotExistException;
import edu.wm.exception.ReadingFileException;

public class TestStereotypeAnalyzer {


	//The project location
	public String projectLoc = "";


	//All java files in the project 
	//public HashSet<String> javaFiles = new HashSet<String>();

	//Map of method signature to method definition
	public HashMap<String, MethodDeclaration> mapSignToMethod = new  HashMap<String, MethodDeclaration>();

	//Map of method signature to tests
	public HashMap<String, TestUnderAnalysis> mapSignToTest = new  HashMap<String, TestUnderAnalysis>();



	/**
	 * Analyze the given project
	 * @param projectLoc
	 * @throws Exception
	 */
	public void analyze(String projectLoc) throws Exception{
		//Load all method information
		loadFilesInfo(projectLoc);

		//Analyze all the test cases
		AnalyzeTestCases();

		//Rule matching
		RuleMatching();


		//printTestType();

	}


	
	/**
	 * Analyze the given string (source code)
	 * @param FileString
	 * @throws Exception
	 */
	public void analyzeString(String FileString) throws Exception{
		//Load all method information by string
		loadStringInfo(FileString);

		//Analyze all the test cases
		AnalyzeTestCases();

		//Rule matching
		RuleMatching();


		//printTestType();

	}




	/**
	 * load all java files' info into the memory
	 * @param projectLoc
	 * @throws ProjectNotExistException
	 * @throws IOException
	 */
	private void loadFilesInfo(String projectLoc) throws ProjectNotExistException, IOException{
		this.projectLoc = projectLoc;
		String currentFile = "";
		Collection<File> files = null;
		try{
			//iterate the project and find all java files 
			files = FileUtils.listFiles(new File(this.projectLoc), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		}catch(IllegalArgumentException ex){
			MyLog.LOGGER.log(Level.SEVERE, "Error occur in TestStereotypeAnalyzer.loadFilesInfo.", ex);
			throw new ProjectNotExistException(projectLoc);
		}

		for (File file : files) {
			if(file.getName().endsWith(".java")){
				currentFile = file.getAbsolutePath();
				String unitName = currentFile.substring(currentFile.lastIndexOf(File.separator)+1).replace(".java", "");
				//System.out.println(file.toString());
				String fileString = "";
				try{
					fileString = Util.readFileToString(currentFile);
				} catch(IOException ex){
					MyLog.LOGGER.log(Level.SEVERE, "Error occur in TestStereotypeAnalyzer.loadFilesInfo.", ex);
					throw ex;
				}

				//Get all method definition in the file 
				CompilationUnit cu = UtilAST.getASTAndBindings(fileString, projectLoc, unitName);
				MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
				cu.accept(visitor);
				for (MethodDeclaration method : visitor.getMethods()) {
					String sign = UtilAST.getMethodSignature(method);
					mapSignToMethod.put(sign, method);
					//If it's a test case, put the method into the map.
					MarkerAnnotation annotation = ReturnAnnotation(method);
					if(TestAnnotation.contains(annotation)){
						TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotation);
						mapSignToTest.put(sign, testMethod);
					}
				}
			}
		}
	}

	private void loadStringInfo(String fileString) throws ProjectNotExistException, IOException{
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource((fileString).toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
 
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			String sign = UtilAST.getMethodSignature(method);
			mapSignToMethod.put(sign, method);
			//If it's a test case, put the method into the map.
			MarkerAnnotation annotation = ReturnAnnotation(method);
			if(TestAnnotation.contains(annotation)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotation);
				mapSignToTest.put(sign, testMethod);
			}
		}
	}

	/**
	 * Returns the annotation of the given method
	 * @param method
	 * @return
	 */
	public MarkerAnnotation ReturnAnnotation(MethodDeclaration method){
		MarkerAnnotationVisitor visitorAnnotation = new MarkerAnnotationVisitor();
		method.accept(visitorAnnotation);
		if(visitorAnnotation.getAnnotation() != null){
			return visitorAnnotation.getAnnotation();
			//return visitorAnnotation.getAnnotation().getTypeName().getFullyQualifiedName();
		}
		return null;
	}




	/**
	 * Analyzes all detected test cases
	 */
	private void AnalyzeTestCases(){

		for (Map.Entry<String, TestUnderAnalysis> entry : mapSignToTest.entrySet()) {
			TestUnderAnalysis test = entry.getValue();
			AnalyzeTest(test);
		}
	}



	/**
	 * Analyzes the given test cases
	 */
	private void AnalyzeTest(TestUnderAnalysis test){
		AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
		test.getMethod().accept(assertionVisitor);
		test.setAssertionStmts(assertionVisitor.getAssertions());
	}


	/**
	 * Match the rule by using given rule collector
	 */
	private void RuleMatching(){
		RuleCollector ruleCollectorJunit4 = new JavaJunit4Collector();
		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			String key = entry.getKey();
			TestUnderAnalysis test = entry.getValue();
			test.applyRuleCollector(ruleCollectorJunit4);
		}
	}



	private void printTestType(){
		RuleCollector ruleCollectorJunit4 = new JavaJunit4Collector();
		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			System.out.println("\nMethod : " + entry.getKey());
			TestUnderAnalysis test = entry.getValue();
			HashSet<TestStereotype> rules = test.matchedRules;
			System.out.println("Rules : ");
			for(TestStereotype rule : rules){
				System.out.println(rule.toString());
			}

		}
	}


}
