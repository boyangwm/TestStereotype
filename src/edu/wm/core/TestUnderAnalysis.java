package edu.wm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;

import edu.wm.Rules.RuleCollector;
import edu.wm.ast.AssignmentMapVisitor;
import edu.wm.ast.UtilAST;
import edu.wm.constants.TestStereotype;

public class TestUnderAnalysis {

	public HashSet<TestStereotype> matchedRules = new HashSet<TestStereotype>(); //ruleCollector.ApplyRules(test);
	
	
	
	public HashSet<MethodInvocation> internalCalls;
	
	public HashSet<MethodInvocation> allCalls;
	

	/**
	 * assertion to related assigned variables. 
	 */
	public HashMap<MethodInvocation, HashSet<SimpleName>> asserstionToRelatedSM; 
	
	
	private boolean hasQualifier = false;

	private boolean hasInternalCall = false;
	
	private boolean hasExternalCall = false;
	
	private boolean isEmpty = false;
	
	
	
	
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
		this.isEmpty = 	(method.getBody().statements().size() == 0);
		
	}

	
	
	
	public void MergeInfo(AssignmentMapVisitor visitor){
		//record all the information
		this.asserstionToRelatedSM = visitor.AsserstionToRelatedSM;
		hasQualifier = false;
		hasInternalCall = false;
		hasExternalCall = false;
		for(Entry <MethodInvocation, HashSet<SimpleName>> entry : asserstionToRelatedSM.entrySet() ){
			 HashSet<SimpleName> nameSet = entry.getValue();
			 hasQualifier = hasQualifier || hasQualifiedVar(nameSet);
			 hasInternalCall =  hasInternalCall || hasInternalCall(nameSet);
			 hasExternalCall = hasExternalCall || hasExternalCall(nameSet);
		}
		this.internalCalls = visitor.internalCalls;
		this.allCalls = visitor.calls;
	}
	
	
	private boolean hasQualifiedVar(HashSet<SimpleName> nameSet){
		for(SimpleName name : nameSet){
			if(UtilAST.IsQualifier(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
	private boolean hasInternalCall(HashSet<SimpleName> nameSet){
		for(SimpleName name : nameSet){
			if(UtilAST.IsInvockedInternalMethod(name)){
				return true;
			}
		}
		return false;
	}
	
	
	
	private boolean hasExternalCall(HashSet<SimpleName> nameSet){
		for(SimpleName name : nameSet){
			if(UtilAST.IsInvockedExternalMethod(name)){
				return true;
			}
		}
		return false;
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

	/**
	 * @return the hasQualifier
	 */
	public boolean isHasQualifier() {
		return hasQualifier;
	}


	/**
	 * @return the hasInternalCall
	 */
	public boolean isHasInternalCall() {
		return hasInternalCall;
	}


	/**
	 * @return the hasExternalCall
	 */
	public boolean isHasExternalCall() {
		return hasExternalCall;
	}

	
	/**
	 * @return the isEmpty
	 */
	public boolean isEmpty() {
		return this.isEmpty;
	}

	
	


}
