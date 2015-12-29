package edu.wm.Rules;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleHybridVerifier extends StereotypeRule{
	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleHybridVerifier()  {super(); }


	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		if(mAnalyzer.getMatchedRules().size() > 0){
			return true;
		}else{
			return false;
		}
		
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Hybrid;
	}

}
