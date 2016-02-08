package UnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.junit.Test;

import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.AssignmentMapVisitor;
import edu.wm.ast.MethodAnalyzer;
import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.ast.UtilAST;
import edu.wm.constants.TestAnnotation;
import edu.wm.core.TestStereotypeAnalyzer;
import edu.wm.core.TestUnderAnalysis;

public class TestSlicing {
	@Test
	public void TestAssignment(){
		String fileString = "public class A { "
				+ "		int f = 3;"
				+ "		@Test "				
				+ "		public void testHelloWorld() { "
				+ " 		int a = 1;"
				+ "			int b = 2;    "
				+ "			int c, d; "
				+ "			c = a + b;"
				+ "			d = f;"
				+ "			assertEquals(c,d);"
				+ "		}  "
				+ "}";

		//		String str = "aaaa";
		//		char c;
		//		c = str.charAt(3);
		//		
		//				int a = 1;
		//				int   b;
		//				int d; 
		//				 b = -3 + (a= 2); 



		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("c");
				assertTrue(containsInSets(set, "a"));
				assertTrue(containsInSets(set, "b"));
			}
		}
	}



	@Test
	public void TestAssignment2(){
		String fileString = "public class A { "
				+ "		int f = 3;"
				+ "		@Test "				
				+ "		public void testHelloWorld() { "
				+ " 		int a = 1;"
				+ "			int b = 2;    "
				+ "			int c, d; "
				+ "			c = a = b;"
				+ "			d = f;"
				+ "			assertEquals(c,d);"
				+ "		}  "
				+ "}";

		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("a");
				assertTrue(containsInSets(set, "b"));
				HashSet<SimpleName> set2 = assignmentMapVisitor.varAssignManager.getAssigningVars("d");
				assertTrue(containsInSets(set2, "f"));
			}
		}
	}





	@Test
	public void TestAssignment3(){
		String fileString = "public class A { "
				+ "		int f = 3;"
				+ "		@Test "				
				+ "		public void testHelloWorld(int b) { "
				+ " 		int a = b; "
				+ "			int c, d; "
				+ "			c = b;"
				+ "			assertEquals(c,a);"
				+ "		}  "
				+ "}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("a");
				assertTrue(containsInSets(set, "b"));
				HashSet<SimpleName> set2 = assignmentMapVisitor.varAssignManager.getAssigningVars("c");
				assertTrue(containsInSets(set2, "b"));
			}
		}
	}




	@Test
	public void TestAssignment4(){
		String fileString = "public class A { "
				+ "		int f = 3;"
				+ "		@Test "				
				+ "		public void testHelloWorld(int b) { "
				+ " 		int a = b; "
				+ " 		int c = 0; "
				+ "			b = -3 + (a= 2) + c;"
				+ "			assertEquals(c,b);"
				+ "		}  "
				+ "}";
		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("b");
				assertTrue(containsInSets(set, "a"));
				assertTrue(containsInSets(set, "c"));
				HashSet<SimpleName> set2 = assignmentMapVisitor.varAssignManager.getAssigningVars("a");
				assertTrue(!containsInSets(set2, "c"));
			}
		}
	}




	@Test
	public void TestAssignment5(){
		String fileString = "public class A { "
				+ "		int f = 3;"
				+ "		@Test "				
				+ "		public int testHelloWorld(int b) { "
				+ " 		int a = b; "
				+ "			int e, d; "
				+ "			return e = b;"
				+ "			assertEquals(c,a);"
				+ "		}  "
				+ "}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("a");
				assertTrue(containsInSets(set, "b"));
				HashSet<SimpleName> set2 = assignmentMapVisitor.varAssignManager.getAssigningVars("e");
				assertTrue(containsInSets(set2, "b"));
			}
		}
	}



	@Test
	public void TestAssignment6(){
		String fileString = "public class A {"
				+ " private class B{"
				+ " 	public int f = 2;"
				+ " }"
				+ " @Test 	"
				+ " public void testHelloWorld() { "
				+ " 	int a = 1;"
				+ " 	B b = new B();    "
				+ " 	int c;"
				+ " 	c = a + b.f;"
				+ " }  "
				+ " }";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor();
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> set = assignmentMapVisitor.varAssignManager.getAssigningVars("c");
				assertTrue(containsInSets(set, "a"));
				assertTrue(containsInSets(set, "b"));
			}
		}
	}


	
	@Test
	public void TestAssertions1(){
		String fileString = "public class A {"
				+ " private class B{"
				+ " 	public int f = 2;"
				+ " }"
				+ " @Test 	"
				+ " public void testHelloWorld() { "
				+ " 	int a = 1;"
				+ " 	B b = new B();    "
				+ " 	int c;"
				+ " 	c = a + b.f;"
				+ "		assertEquals(c,a);"
				+ " }  "
				+ " }";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor(testMethod.getAssertionStmts());
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> nameSet = assignmentMapVisitor.AsserstionToRelatedSM.get(assertionVisitor.getAssertions().get(0));
				assertTrue(containsInSets(nameSet, "b"));
				assertTrue(containsInSets(nameSet, "a"));        
				//assertTrue(containsInSets(nameSet, "c"));
				SimpleName b = getFirstInSets(nameSet, "b");
				assertTrue(UtilAST.IsQualifier(b));
				
			}
		}
	}

	
	
	@Test
	public void TestAssertions2(){
		
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
				+ " 	int b = 2;"
				+ "		int c;"
				+ " 	c = a;"
				+ "     c = b;"
				+ "		assertEquals(c, b);"
				+"	}"
				+"}";
	

		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor(testMethod.getAssertionStmts());
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> nameSet = assignmentMapVisitor.AsserstionToRelatedSM.get(assertionVisitor.getAssertions().get(0));
				assertTrue(containsInSets(nameSet, "b"));
				assertFalse(containsInSets(nameSet, "a"));        // c is refreshed. 
				SimpleName b = getFirstInSets(nameSet, "b");
				assertFalse(UtilAST.IsQualifier(b));
			}
		}
	}
	
	
	
	
	@Test
	public void TestAssertions3(){
		
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
		
		

		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor(testMethod.getAssertionStmts());
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> nameSet = assignmentMapVisitor.AsserstionToRelatedSM.get(assertionVisitor.getAssertions().get(0));
				assertTrue(containsInSets(nameSet, "b"));
				assertFalse(containsInSets(nameSet, "a"));        // c is refreshed. 
				SimpleName b = getFirstInSets(nameSet, "b");
				assertTrue(UtilAST.IsInvockedInternalMethod(b));
		
			}
		}
	}
	
	
	@Test
	public void TestAssertions4(){
		
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
				+ "     c = 1;"
				+ "		assertEquals(c, b);"
				+"	}"
				+"}";
		
		

		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);

				//detects all the assertions in the test
				AssertionInvocationVisitor assertionVisitor = new AssertionInvocationVisitor();
				testMethod.getMethod().accept(assertionVisitor);
				testMethod.setAssertionStmts(assertionVisitor.getAssertions());


				AssignmentMapVisitor assignmentMapVisitor = new AssignmentMapVisitor(testMethod.getAssertionStmts());
				testMethod.getMethod().accept(assignmentMapVisitor);
				HashSet<SimpleName> nameSet = assignmentMapVisitor.AsserstionToRelatedSM.get(assertionVisitor.getAssertions().get(0));
				assertFalse(containsInSets(nameSet, "b"));
				assertFalse(containsInSets(nameSet, "a"));        // c is refreshed. 
			}
		}
	}
	
	
	
	@Test
	public void TestEmpty(){
		
		String fileString = "public class A {"
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"	}"
				+"}";
		
		

		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {

			HashSet<Annotation> annotations = TestStereotypeAnalyzer.ReturnAnnotation(method);
			if(TestAnnotation.contains(annotations)){
				TestUnderAnalysis testMethod = new TestUnderAnalysis(method, annotations);
			
				assertTrue(testMethod.isEmpty());
			}
		}
	}
	
	
	
	
	

	/**
	 * Checks if set contains variable str by String checking (this is for testing purpose)
	 * @param set
	 * @param str
	 * @return
	 */
	public boolean containsInSets( HashSet <SimpleName> set, String str){
		for(SimpleName name : set){
			if (name.getIdentifier().equals(str)) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * @param set
	 * @param str
	 * @return
	 */
	public SimpleName getFirstInSets( HashSet <SimpleName> set, String str){
		for(SimpleName name : set){
			if (name.getIdentifier().equals(str)) {
				return name;
			}
		}
		return null;
	}
}







//
//String fileString = "public class A {"
//		+"	private class B{"
//		+"		public int f = 2;"
//		+"		public int foo(int p){"
//		+"			return f = p;"
//		+"		}"
//		+"	}"
//		+"	B b = new B();" 
//		+"	@Test 	"
//		+"	public void testHelloWorld() {" 
//		+"		int a = 1;"
//		+ " 	int c;"
//		+ " 	c = a;"
//		+ "     c = b"
//		+ "		assertEquals(c,a);"
//		+ "		c = b.foo(2);"
//		+ "		assertEquals(c,2);"
//		+"	}"
//		+"}";

//public class B { 
//	int f = 3;
//	@Test 	
//	public void testHelloWorld(int b) { 
//		int a = b; 
//		int c, d; 
//		c = b;
//		assertEquals(c,a);
//	}  
//}
