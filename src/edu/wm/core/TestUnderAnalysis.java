package edu.wm.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.Annotation;
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
	private HashSet<Annotation> annotations;


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


	public TestUnderAnalysis(MethodDeclaration method, HashSet<Annotation> annotations){
		this.method = method;
		this.annotations = annotations;
	}


	/**
	 * @return the annotation
	 */
	public HashSet<Annotation> getAnnotation() {
		return annotations;
	}

	/**
	 * @param annotation the annotation to set
	 */
	public void setAnnotation(HashSet<Annotation> annotations) {
		this.annotations = annotations;
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
