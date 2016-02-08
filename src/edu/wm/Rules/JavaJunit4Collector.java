package edu.wm.Rules;

import java.util.ArrayList;

/**
 * This collector contains all rules (category 1) for Junit4 test cases. 
 * @author Boyang
 *
 */
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
		ruleList.add(new RuleTestInitializer());
		ruleList.add(new RuleExceptionVerifier());
		ruleList.add(new RuleBooleanVerifier());
		ruleList.add(new RuleNullVerifier());
		ruleList.add(new RuleEqualityVerifier());
		ruleList.add(new RuleIdentityVerifier());
		ruleList.add(new RuleUtilityVerifier());
		ruleList.add(new ConditionMatcherVerifier());
		ruleList.add(new RuleIgnoredMethod());
		ruleList.add(new RuleAssumptionSetter());
		
		ruleList.add(new RuleLogger());
		ruleList.add(new RuleHybridVerifier()); //Should be the last one 
		this.rules = ruleList;
	}

}
