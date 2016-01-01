package edu.wm.Rules;

import java.util.ArrayList;

public class JavaJunit4Collector extends RuleCollector {

	public JavaJunit4Collector()	{
		super();
	}

	
	/* (non-Javadoc)
	 * Initializes the rule list to a defined set of Rule objects.
	 */
	@Override
	protected void DefineRuleSet() {
		ArrayList<StereotypeRule> ruleList = new ArrayList<StereotypeRule>();
		ruleList.add(new RuleTestCleaner());
		ruleList.add(new RuleTestSetter());
		ruleList.add(new RuleExceptionVerifier());
		ruleList.add(new RuleConditionVerifier());
		ruleList.add(new RuleEqualityVerifier());
		ruleList.add(new RuleIdentityVerifier());
		ruleList.add(new RuleUtilityVerifier());
		
		
		ruleList.add(new RuleHybridVerifier()); //Should be the last one 
		this.rules = ruleList;
	}

}
