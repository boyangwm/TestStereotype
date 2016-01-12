package edu.wm.Rules;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class ExecutionTester  extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public ExecutionTester()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		if(mAnalyzer.getAssertionStmts().size() == 0 && mAnalyzer.internalCalls.size() > 0)
			return true;
		else
			return false;
	}

	

	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Execution;
	}
}
