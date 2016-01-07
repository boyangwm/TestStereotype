package edu.wm.Rules;

import java.util.ArrayList;

/**
 * 
 * @author Boyang
 *
 */
public class FlowCollector extends RuleCollector {

	public FlowCollector()	{
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
		
		this.rules = ruleList;
		
	}

}
