package UnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.junit.Test;

import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.ast.SimpleVarNameVisitor;
import edu.wm.ast.UtilAST;

public class ASTUtilTest {
	@Test
	public void TestVariableWithCall(){
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
				+"		a = b.foo(a);"
				+"	}"
				+"}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			if(method.getName().getFullyQualifiedName().equals("testHelloWorld")){
				SimpleVarNameVisitor smVisitor = new SimpleVarNameVisitor();
				method.accept(smVisitor);
				HashSet<SimpleName> names = smVisitor.getNames();
				for(SimpleName name : names){
					if(name.getFullyQualifiedName().equals("b")){
						assertTrue(UtilAST.IsInvockedMethod(name));
					}
				}
			}
		}
	}
	
	
	
	
	@Test
	public void TestVariableWithFieldAccess(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public void foo(int p){"
				+"			f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+"		b.f = a;"
				+"	}"
				+"}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			if(method.getName().getFullyQualifiedName().equals("testHelloWorld")){
				SimpleVarNameVisitor smVisitor = new SimpleVarNameVisitor();
				method.accept(smVisitor);
				HashSet<SimpleName> names = smVisitor.getNames();
				for(SimpleName name : names){
					if(name.getFullyQualifiedName().equals("b")){
						assertTrue(UtilAST.IsQualifier(name));
						assertFalse(UtilAST.IsInvockedInternalMethod(name));
					}
				}
			}
		}
	}
	
	
	
	
	@Test
	public void TestVariableWithCall2(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public void foo(int p){"
				+"			f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+"		b.foo(a);"
				+"	}"
				+"}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			if(method.getName().getFullyQualifiedName().equals("testHelloWorld")){
				SimpleVarNameVisitor smVisitor = new SimpleVarNameVisitor();
				method.accept(smVisitor);
				HashSet<SimpleName> names = smVisitor.getNames();
				for(SimpleName name : names){
					if(name.getFullyQualifiedName().equals("b")){
						//System.out.println("internal  : "  + UtilAST.IsInvockedInternalMethod(name));
						assertTrue(UtilAST.IsInvockedInternalMethod(name));
						assertFalse(UtilAST.IsInvockedExternalMethod(name));
					}
				}
			}
		}
	}
	
	
	
	@Test
	public void TestVariableWithCall3(){
		String fileString = "public class A {"
				+"	private class B{"
				+"		public int f = 2;"
				+"		public void foo(int p){"
				+"			f = p;"
				+"		}"
				+"	}"
				+"	B b = new B();" 
				+"	@Test 	"
				+"	public void testHelloWorld() {" 
				+"		int a = 1;"
				+"		b.toString();"
				+"	}"
				+"}";


		Path currentRelativePath = Paths.get("");

		final CompilationUnit cu = UtilAST.getASTAndBindings(fileString, currentRelativePath.toAbsolutePath().toString()+File.separator+"Apps"+File.separator+"Testing", "A");
		MethodDeclarationVisitor methodVisitor = new MethodDeclarationVisitor();
		cu.accept(methodVisitor);
		for (MethodDeclaration method : methodVisitor.getMethods()) {
			if(method.getName().getFullyQualifiedName().equals("testHelloWorld")){
				SimpleVarNameVisitor smVisitor = new SimpleVarNameVisitor();
				method.accept(smVisitor);
				HashSet<SimpleName> names = smVisitor.getNames();
				for(SimpleName name : names){
					if(name.getFullyQualifiedName().equals("b")){
						//System.out.println("internal  : "  + UtilAST.IsInvockedInternalMethod(name));
						assertFalse(UtilAST.IsInvockedInternalMethod(name));
						assertTrue(UtilAST.IsInvockedExternalMethod(name));
					}
				}
			}
		}
	}
}
