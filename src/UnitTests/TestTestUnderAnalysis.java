package UnitTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.junit.Test;

import edu.wm.ast.AssertionInvocationVisitor;
import edu.wm.ast.MethodDeclarationVisitor;


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
}
