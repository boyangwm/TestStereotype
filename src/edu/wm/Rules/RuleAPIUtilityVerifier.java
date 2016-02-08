package edu.wm.Rules;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleAPIUtilityVerifier  extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleAPIUtilityVerifier()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		return mAnalyzer.isHasExternalCall();
	}

	

	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.ApiUtility;
	}
}
