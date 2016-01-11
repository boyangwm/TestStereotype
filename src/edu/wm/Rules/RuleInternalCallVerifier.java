package edu.wm.Rules;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleInternalCallVerifier  extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleInternalCallVerifier()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		return mAnalyzer.isHasInternalCall();
	}

	

	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.InternalCall;
	}

}
