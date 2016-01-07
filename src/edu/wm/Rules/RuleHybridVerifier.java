package edu.wm.Rules;


import java.util.HashSet;

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
		//if(mAnalyzer.matchedRules.size() > 1){
		//mAnalyzer.matchedRules.clear();
		if(NumOfCategoryOne(mAnalyzer.matchedRules) > 1){
			return true;
		}else{
			return false;
		}

	}


	private int NumOfCategoryOne(HashSet<TestStereotype> rules){
		int ret = 0;
		for(TestStereotype rule : rules){
			if(rule.GetCategory() == 1){
				ret++;
			}
		}
		return ret;

	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Hybrid;
	}

}
