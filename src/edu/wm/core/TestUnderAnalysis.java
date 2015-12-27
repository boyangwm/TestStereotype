package edu.wm.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class TestUnderAnalysis {
	
	/**
	 * AST node (MethodDeclaration)
	 */
	private MethodDeclaration method;
	
	/**
	 * Record the annotation of the test case
	 */
	private String annotation;
	
	
	/**
	 * All assertions in the current method
	 */
	private List<MethodInvocation > assertionStmts = new ArrayList<MethodInvocation>();

	
	/**
	 * @return the assertionStmts
	 */
	public List<MethodInvocation> getAssertionStmts() {
		return assertionStmts;
	}


	/**
	 * @param assertionStmts the assertionStmts to set
	 */
	public void setAssertionStmts(List<MethodInvocation> assertionStmts) {
		this.assertionStmts = assertionStmts;
	}


	public TestUnderAnalysis(MethodDeclaration method, String annotation){
		this.method = method;
		this.annotation = annotation;
	}
	
	
	/**
	 * @return the annotation
	 */
	public String getAnnotation() {
		return annotation;
	}

	/**
	 * @param annotation the annotation to set
	 */
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}


	/**
	 * @return the method
	 */
	public MethodDeclaration getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(MethodDeclaration method) {
		this.method = method;
	}

	
	

}
