package edu.wm.Rules;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.WhileStatement;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleIteritiveVerifier extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleIteritiveVerifier()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		
		ArrayList<MethodInvocation> assertions = mAnalyzer.getAssertionStmts();
		
		for(MethodInvocation assertion : assertions){
			if(CheckParentLoop(assertion))
				return true;
		}
		return false;
	}

	
	private boolean CheckParentLoop(ASTNode node){
		ASTNode parent = node;
		while(true){
			parent = parent.getParent();
			if(parent instanceof ForStatement || parent instanceof DoStatement || parent instanceof WhileStatement || parent instanceof EnhancedForStatement){
				return true;
			}
			if(parent instanceof MethodDeclaration){
				break;
			}
		}
		return false;
	}

	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Iterative;
	}
}
