package edu.wm.core;

import java.util.ArrayList;
import java.util.List;

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
	private String annotation;


	/**
	 * All assertions in the current method
	 */
	private ArrayList<MethodInvocation> assertionStmts = new ArrayList<MethodInvocation>();




	ArrayList<TestStereotype> matchedRules = new ArrayList<TestStereotype>(); //ruleCollector.ApplyRules(test);
	
	
	
	/**
	 * @return the matchedRules
	 */
	public ArrayList<TestStereotype> getMatchedRules() {
		return matchedRules;
	}


	/**
	 * @param matchedRules the matchedRules to set
	 */
	public void setMatchedRules(ArrayList<TestStereotype> matchedRules) {
		this.matchedRules = matchedRules;
	}


	/**
	 * Apply collector and infer new types
	 * @param collector
	 */
	public void applyRuleCollector(RuleCollector collector){
		ArrayList<TestStereotype> newMatchedRules = collector.ApplyRules(this);
		this.matchedRules.addAll(newMatchedRules);
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
