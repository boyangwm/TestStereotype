package edu.wm.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.wm.Rules.FlowDataCollector;
import edu.wm.Rules.JavaJunit4Collector;
import edu.wm.Rules.RuleCollector;
import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.AssignmentMapVisitor;
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
	//public HashMap<String, MethodDeclaration> mapSignToMethod = new  HashMap<String, MethodDeclaration>();

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
					//mapSignToMethod.put(sign, method);
					//If it's a test case, put the method into the map.
					HashSet<Annotation> annotations = ReturnAnnotation(method);
					if(TestAnnotation.contains(annotations)){
						if(method.getBody() == null){
							continue;
						}
						TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);
						mapSignToTest.put(sign, testMethod);
					}
				}
			}
		}
	}

	private void loadStringInfo(String fileString) throws ProjectNotExistException, IOException{

		String[] sources = { "" };
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setEnvironment(null, sources, new String[] { "UTF-8" }, true);

		Hashtable<String, String> options = JavaCore.getDefaultOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
		options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);

		parser.setCompilerOptions(options);
		parser.setUnitName("String");

		/*
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource((fileString).toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		 */

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			String sign = UtilAST.getMethodSignature(method);
			//mapSignToMethod.put(sign, method);
			//If it's a test case, put the method into the map.
			HashSet<Annotation> annotations = ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				if(method.getBody() == null){
					continue;
				}
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);
				mapSignToTest.put(sign, testMethod);
			}
		}
	}



	public static CompilationUnit StringToCU(String fileString){
		String[] sources = { "" };
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(fileString.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setEnvironment(null, sources, new String[] { "UTF-8" }, true);

		Hashtable<String, String> options = JavaCore.getDefaultOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
		options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);

		parser.setCompilerOptions(options);
		parser.setUnitName("String");

		/*
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource((fileString).toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		 */

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();

		return (CompilationUnit) parser.createAST(null);
	}

	/**
	 * Returns the annotation of the given method
	 * @param method
	 * @return
	 */
	public static HashSet<Annotation> ReturnAnnotation(MethodDeclaration method){
		MarkerAnnotationVisitor visitorAnnotation = new MarkerAnnotationVisitor();
		method.accept(visitorAnnotation);
		return visitorAnnotation.getAnnotation();
		//return visitorAnnotation.getAnnotation().getTypeName().getFullyQualifiedName();
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

		//1. detects all the assertions in the test
		AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
		test.getMethod().accept(assertionVisitor);
		test.setAssertionStmts(assertionVisitor.getAssertions());

		//2. Slicing analysis/method analysis , dataflow analysis
		AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor(test.getAssertionStmts());
		test.getMethod().accept(assignmentMapVisitor);
		test.MergeInfo(assignmentMapVisitor);


	}


	/**
	 * Match the rule by using given rule collector
	 */
	private void RuleMatching(){
		//Category 1 rules matching
		RuleCollector ruleCollectorJunit4 = new JavaJunit4Collector();

		//Category 2 rules matching
		RuleCollector flowCollector = new FlowDataCollector();


		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			String key = entry.getKey();
			TestUnderAnalysis test = entry.getValue();
			test.applyRuleCollector(ruleCollectorJunit4);
			test.applyRuleCollector(flowCollector);
			if(test.matchedRules.size() == 0) {
				test.matchedRules.add(TestStereotype.Unclassified);
			}
		}
	}



	/**
	 * Prints all detected tests' signature and its types 
	 */
	public void printTestType(){
		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			System.out.println("");
			System.out.println("Method: " + entry.getKey());
			TestUnderAnalysis test = entry.getValue();
			HashSet<TestStereotype> rules = test.matchedRules;
			System.out.print("Rules: ");
			int i = 0;
			for(TestStereotype rule : rules){
				if(i < rules.size() - 1)
					System.out.print(rule.toString() + ",");
				else
					System.out.print(rule.toString());
				i++;
			}
			System.out.println("");
		}
	}



	public void printSummary(){
		HashMap <TestStereotype, Integer> typeToOccurrence = new HashMap<TestStereotype, Integer>();
		for(TestStereotype type: TestStereotype.values()){
			typeToOccurrence.put(type, 0);
		}

		System.out.println("# of Detected test cases : " + mapSignToTest.size() );

		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			TestUnderAnalysis test = entry.getValue();
			HashSet<TestStereotype> rules = test.matchedRules;
			for(TestStereotype rule : rules){
				int currentOccur = typeToOccurrence.get(rule);
				typeToOccurrence.put(rule, currentOccur + 1);
			}
		}

		for(Map.Entry<TestStereotype, Integer>  entry : typeToOccurrence.entrySet()){
			System.out.println(entry.getKey().toString()  +  " occurs " + entry.getValue() + " times."); 
		}

	}


	public void printXML(String projName){
		System.out.println("<report>");
		System.out.println("<name>");
		System.out.println(projName);
		System.out.println("</name>");

		System.out.println("<detailSummary>");
		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			TestUnderAnalysis test = entry.getValue();
			System.out.println("<method>");
			System.out.println("<signature>" +  StringEscapeUtils.escapeXml(entry.getKey()) + "</signature>");
			System.out.println("<code>");
			String code = StringEscapeUtils.escapeXml(test.getMethod().toString());
			//			code = code.replaceAll("<", "&lt;");       
			//			code = code.replaceAll(">", "&gt;");
			// *     & - &amp;
			//    < - &lt;
			//    > - &gt;
			//    " - &quot;
			//    ' - &apos;

			
			System.out.print(code);
			System.out.println("</code>");
			System.out.println("<types>");
			HashSet<TestStereotype> rules = test.matchedRules;
			int i = 0;
			for(TestStereotype rule : rules){
				System.out.println("<type id=\"" + rule.getId() + "\">");
				System.out.println(rule.toString());
				System.out.println("</type>");
			}
			System.out.println("</types>");
			System.out.println("</method>\n");
		}
		System.out.println("</detailSummary>");

		//summary
		HashMap <TestStereotype, Integer> typeToOccurrence = new HashMap<TestStereotype, Integer>();
		for(TestStereotype type: TestStereotype.values()){
			typeToOccurrence.put(type, 0);
		}
		for (Map.Entry<String, TestUnderAnalysis>  entry : mapSignToTest.entrySet()) {
			TestUnderAnalysis test = entry.getValue();
			HashSet<TestStereotype> rules = test.matchedRules;
			for(TestStereotype rule : rules){
				int currentOccur = typeToOccurrence.get(rule);
				typeToOccurrence.put(rule, currentOccur + 1);
			}
		}


		System.out.println("<summary>");
		System.out.println("<totalMethod>");
		System.out.println(mapSignToTest.size() );
		System.out.println("</totalMethod>");
		for(Map.Entry<TestStereotype, Integer>  entry : typeToOccurrence.entrySet()){
			System.out.println("<type>");
			System.out.print("<name>");
			System.out.print(entry.getKey().toString());
			System.out.print("</name>");
			System.out.print("<occurs>");
			System.out.print(entry.getValue().toString());
			System.out.print("</occurs>");
			System.out.println("</type>");
		}

		System.out.println("</summary>");
		System.out.println("</report>");
	}

}
