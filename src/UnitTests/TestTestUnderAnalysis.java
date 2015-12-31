package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.junit.Before;
import org.junit.Test;

import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.constants.TestAnnotation;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestStereotypeAnalyzer;
import edu.wm.core.TestUnderAnalysis;


public class TestTestUnderAnalysis {


	@Test
	public void TestAssertionName1(){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(("public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setName(\"World\");"
				+ "			Assert.assertEquals(h.getName(),\"World\");    "
				+ "			assertEquals(h.getMessage(),\"Hello World!\"); "
				+ "		}  "
				+ "}").toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);


		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
			method.accept(assertionVisitor);
			List<MethodInvocation> assertions = assertionVisitor.getAssertions();
			for(MethodInvocation assertion: assertions){
				assertEquals("assertEquals", assertion.getName().getFullyQualifiedName());
			}
		}
	}



	@Test
	public void TestAssertionName2(){
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(("public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setBool(false);"
				+ "			assertTrue(h.getBool(),false); "
				+ "		}  "
				+ "}").toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);


		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
			method.accept(assertionVisitor);
			List<MethodInvocation> assertions = assertionVisitor.getAssertions();
			for(MethodInvocation assertion: assertions){
				assertEquals("assertTrue", assertion.getName().getFullyQualifiedName());
			}
		}
	}


	@Test
	public void TestAssertionTypeCondition(){
		String methodTest = "public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setBool(false);"
				+ "			assertTrue(h.getBool(),false); "
				+ "		}  "
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Condition, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	public void TestAssertionTypeEquality(){
		String methodTest = "public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setName(\"World\");"
				+ "			Assert.assertEquals(h.getName(),\"World\");    "
				+ "			assertEquals(h.getMessage(),\"Hello World!\"); "
				+ "		}  "
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Equality, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void TestAssertionTypeIdentity(){
		String methodTest = "public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setName(\"World\");"
				+ "			assertSame(h.getMessage(),\"Hello World!\"); "
				+ "		}  "
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Identity, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	public void TestAssertionTypeUtility(){
		String methodTest =  "public class A { "
				+ "		@Test "
				+ "		test_addNilThrowsNullPointerException()"
				+"		{"
				+"			try {"
				+"					foo.add(NIL);                    "
				+"					fail(\"No NullPointerException\");  "            
				+"			} catch (Exception e) {"
				+"			}"
				+"		}"
				+"}";
				TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Utility, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestAssertionTypeHybrid(){
		String methodTest = "public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setName(\"World\");"
				+ "			Assert.assertEquals(h.getName(),\"World\");    "
				+ "			assertSame(h.getMessage(),\"Hello World!\"); "
				+ "		}  "
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Hybrid, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestAssertionTypeBefore(){
		String methodTest = "public class A { "
			+	  "@Before"
			+	  " public void setUp() throws Exception" 
			+	  " {"
			+	  "    h = new HelloWorld();"
			+	  "}"
			+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Setter, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test
	public void TestAssertionTypeAfter(){
		String methodTest = "public class A { "
			+	  "@AfterClass"
			+	  " public void setUp() throws Exception" 
			+	  " {"
			+	  "    h = new HelloWorld();"
			+	  "}"
			+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				for(TestStereotype rule : rules){
					assertEquals(TestStereotype.Cleaner, rule);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
