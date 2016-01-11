package edu.wm.Rules;


import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleQualifiedFieldVerifier extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleQualifiedFieldVerifier()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		return mAnalyzer.isHasQualifier();
	}

	

	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.QualifiedField;
	}

}
