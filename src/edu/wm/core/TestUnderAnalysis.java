package edu.wm.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.Rules.RuleCollector;
import edu.wm.constants.TestStereotype;

public class TestUnderAnalysis {

	/**
	 * AST node (MethodDeclaration)
	 */
	private MethodDeclaration method;

	/**
	 * Record the annotation of the test case
	 */
	private MarkerAnnotation annotation;


	/**
	 * All assertions in the current method
	 */
	private ArrayList<MethodInvocation> assertionStmts = new ArrayList<MethodInvocation>();




	public HashSet<TestStereotype> matchedRules = new HashSet<TestStereotype>(); //ruleCollector.ApplyRules(test);
	

	/**
	 * Apply collector and infer new types
	 * @param collector
	 */
	public void applyRuleCollector(RuleCollector collector){
		collector.ApplyRules(this);
		//this.matchedRules.addAll(newMatchedRules);
	}


	/**
	 * @return the assertionStmts
	 */
	public ArrayList<MethodInvocation> getAssertionStmts() {
		return assertionStmts;
	}


	/**
	 * @param assertionStmts the assertionStmts to set
	 */
	public void setAssertionStmts(ArrayList<MethodInvocation> assertionStmts) {
		this.assertionStmts = assertionStmts;
	}


	public TestUnderAnalysis(MethodDeclaration method, MarkerAnnotation annotation){
		this.method = method;
		this.annotation = annotation;
	}


	/**
	 * @return the annotation
	 */
	public MarkerAnnotation getAnnotation() {
		return annotation;
	}

	/**
	 * @param annotation the annotation to set
	 */
	public void setAnnotation(MarkerAnnotation annotation) {
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
