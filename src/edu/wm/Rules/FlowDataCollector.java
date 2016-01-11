package edu.wm.Rules;

import java.util.ArrayList;

/**
 * @author Boyang
 *
 */
public class FlowDataCollector extends RuleCollector {

	public FlowDataCollector()	{
		super();
	}

	
	/* (non-Javadoc)
	 * Initializes the rule list to a defined set of Rule objects.
	 */
	@Override
	protected void DefineRuleSet() {
		ArrayList<StereotypeRule> ruleList = new ArrayList<StereotypeRule>();
		ruleList.add(new RuleIteritiveVerifier());
		ruleList.add(new RuleBranchVerifier());
		ruleList.add(new RuleExternalCallVerifier());
		ruleList.add(new RuleInternalCallVerifier());
		ruleList.add(new RuleQualifiedFieldVerifier());
		
		this.rules = ruleList;
		
	}

}
