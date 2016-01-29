package edu.wm.Rules;

import java.util.ArrayList;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleEmptyTester  extends StereotypeRule {

	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleEmptyTester()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		return mAnalyzer.isEmpty();
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Empty;
	}
}

