package edu.wm.core;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class TestUnderAnalysis {
	
	/**
	 * AST node (MethodDeclaration)
	 */
	private MethodDeclaration method;
	
	/**
	 * Record the annotation of the test case
	 */
	private String annotation;
	

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
