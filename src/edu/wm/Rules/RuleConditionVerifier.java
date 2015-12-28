package edu.wm.Rules;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleConditionVerifier extends StereotypeRule {


	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleConditionVerifier()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		ArrayList<MethodInvocation> assertions = mAnalyzer.getAssertionStmts();
		int numOfMatches = 0;
		for(MethodInvocation assertion : assertions){
			String assertionName = getAssertionName(assertion);
			if(assertionName.equals(Assertions.assertTrue.name())){
				numOfMatches++;
			}else if(assertionName.equals(Assertions.assertFalse.name())){
				numOfMatches++;
			}else if(assertionName.equals(Assertions.assertNull.name())){
				numOfMatches++;
			}else if(assertionName.equals(Assertions.assertNotNull.name())){
				numOfMatches++;
			}
		}
		if(assertions.size() > 0 && numOfMatches == assertions.size()){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Condition;
	}

}
