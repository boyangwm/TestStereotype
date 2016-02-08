package UnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.junit.Before;
import org.junit.Test;

import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.ast.UtilAST;
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
				assertTrue(ContainsType(rules, TestStereotype.BooleanV));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void TestAssertionTypeNull(){
		String methodTest = "public class A { "
				+ "		@Test "
				+ "		public void testHelloWorld() { "
				+ " 		h.setBool(false);"
				+ "			assertNull(h); "
				+ "		}  "
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.NullVerifier));
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
				assertTrue(ContainsType(rules, TestStereotype.Equality));
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
				assertTrue(ContainsType(rules, TestStereotype.Identity));
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
				assertTrue(ContainsType(rules, TestStereotype.Utility));
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
				assertTrue(ContainsType(rules, TestStereotype.Hybrid));
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
				assertTrue(ContainsType(rules, TestStereotype.Initializer));

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
				assertTrue(ContainsType(rules, TestStereotype.Cleaner));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	public void TestAssertionTypeException(){
		String methodTest = "public class A { "
				+ 	"@Test(expected=IllegalArgumentException.class)"
				+ 	"public void testException(String input) {"
				+ 	"   System.out.println(\"@Test(expected) will check for specified exception during its run\");"  
				+ 	"}"
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Exception));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestAssertionTypeForLoop(){
		String methodTest = "public class A { "
				+ "@Test"
				+" public void testHelloEmpty(){" 
				+	"for(int i = 0; i < 10; i++){"
				+		"assertEquals(h.getMessage(),\"Hello!\");"
				+	"}"
				+ "}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;

				assertTrue(ContainsType(rules, TestStereotype.Equality));
				assertTrue(ContainsType(rules, TestStereotype.Iterative));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	@Test
	public void TestAssertionTypeForBranch(){
		String methodTest = "public class A { "
				+ "@Test"
				+ " public void testHelloEmpty()" 
				+ "{"
				+	"int a = 1;"
				+	"if(a == 1){"
				+		"assertEquals(h.getMessage(),\"Hello!\");"
				+	"}else{"
				+	"	a = 2;"
				+	"}"
				+ "}"
				+"};";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(methodTest);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;

				assertTrue(ContainsType(rules, TestStereotype.Branch));
				assertTrue(ContainsType(rules, TestStereotype.Equality));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	@Test
	public void TestAssertionTypeForInternal(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public int foo(int p){"
				+"			return f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+ "		int c;"
				+ " 	c = a;"
				+ "     c = b.foo(3);"
				+ "		assertEquals(c, b);"
				+"	}"
				+"}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Equality));
				assertTrue(ContainsType(rules, TestStereotype.InternalCall));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	@Test
	public void TestAssertionTypeForField(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public int foo(int p){"
				+"			return f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+ "		int c;"
				+ " 	c = a;"
				+ "     c = b.f;"
				+ "		assertEquals(c, b);"
				+"	}"
				+"}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Equality));
				assertTrue(ContainsType(rules, TestStereotype.PublicField));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	public void TestAssertionTypeForExternal(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public int foo(int p){"
				+"			return f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+ "		int c;"
				+ " 	c = a;"
				+ "     c = b.toString();"
				+ "		assertEquals(c, b);"
				+"	}"
				+"}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Equality));
				assertTrue(ContainsType(rules, TestStereotype.ApiUtility));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Test
	public void TestAssertionTypeForInternal2(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public int foo(int p){"
				+"			return f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+ "		int c;"
				+ " 	c = a;"
				+ "     b.foo(3);"
				+ "		assertTrue(b==3);"
				+"	}"
				+"}";
		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				//assertTrue(ContainsType(rules, TestStereotype.Equality));
				assertTrue(ContainsType(rules, TestStereotype.InternalCall));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	@Test
	public void TestEmpty(){

		String fileString = "public class A {"
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"	}"
				+"}";


		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Empty));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	@Test
	public void TestLogger(){

		String fileString = "public class A {"
				+"@Test"
				+" public void testPreample() throws Exception {"
				+"    byte[] bytes = \"\".getBytes(\"utf-16\");"
				+"    System.out.println(\"Preample len is \" + bytes.length);"
				+" }"
				+"}";



		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Logger));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//	@Test
	//    public void testPreample() throws Exception {
	//		Logger tlog = Logger.getLogger("myLog");
	//		tlog.log(Level.SEVERE, "test");
	//    }
	//	

	@Test
	public void TestLogger2(){

		String fileString = "import java.util.logging.Level;"
				+ "import java.util.logging.Logger;"
				+ "public class A {"
				+"@Test"
				+" public void testPreample() throws Exception {"
				+"	Logger tlog = Logger.getLogger(\"myLog\");"
				+"	tlog.log(Level.SEVERE, \"test\");"
				+"}"
				+"}";

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Logger));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	@Test
	public void TestConditionMatcher(){

		String fileString = "import java.util.logging.Level;"
				+ "import java.util.logging.Logger;"
				+ "public class A {"
				+"@Test"
				+"  public void testAssertThatBothContainsString() {"
				+"    org.junit.Assert.assertThat(\"albumen\", both(containsString(\"a\")).and(containsString(\"b\")));"
				+" }"
				+"}";

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.ConditionMatcher));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestIgnore(){

		String fileString = "import java.util.logging.Level;"
				+ "import java.util.logging.Logger;"
				+ "public class A {"
				+"@Ignore(\"Test is ignored as a demonstration\")"
				+"@Test"
				+"public void testSame() {"
				+"    assertThat(1, is(1));"
				+"}"
				+"}";

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Ignore));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

	@Test
	public void TestAssumption(){

		String fileString = "import java.util.logging.Level;"
				+ "import java.util.logging.Logger;"
				+ "public class A {"
				+" @Test public void filenameIncludesUsername() {"
				+"    assumeThat(File.separatorChar, is('/'));"
			    +"    assertThat(new User(\"optimus\").configFileName(), is(\"configfiles/optimus.cfg\"));"
			    +"}"
				+"}";

		TestStereotypeAnalyzer analyzer = new TestStereotypeAnalyzer();
		try {
			analyzer.analyzeString(fileString);
			for (Map.Entry<String, TestUnderAnalysis>  entry : analyzer.mapSignToTest.entrySet()) {
				TestUnderAnalysis test = entry.getValue();
				HashSet<TestStereotype> rules = test.matchedRules;
				assertTrue(ContainsType(rules, TestStereotype.Assumption));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
   

	/**
	 * Check if rules contains type type
	 * @param rules
	 * @param type
	 * @return
	 */
	private boolean ContainsType(HashSet<TestStereotype> rules, TestStereotype type){
		for(TestStereotype rule : rules){
			if(rule == type){
				return true;
			}
		}
		return false;

	}

}
