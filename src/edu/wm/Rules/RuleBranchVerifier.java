package edu.wm.Rules;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;




/**
 * Rule for branch verifier
 * @author Boyang
 *
 */
public class RuleBranchVerifier extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleBranchVerifier()  {super(); }



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
			if(parent instanceof SwitchCase || parent instanceof SwitchStatement || parent instanceof IfStatement){
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
		return TestStereotype.Branch;
	}
}
