package edu.wm.Rules;

import java.util.ArrayList;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleCollector {


	/**
	 * A set of defined rules
	 */
	public ArrayList<StereotypeRule> rules;
	
	
	
	
    /**
     * Initializes the list of rules. 
     */
    protected void DefineRuleSet() {
        this.rules = new ArrayList<StereotypeRule>();
    }
    
    

	public ArrayList<TestStereotype> ApplyRules(TestUnderAnalysis test) {
		ArrayList<TestStereotype> matchedStereotypes = new ArrayList<TestStereotype>();
        if(this.rules == null) { DefineRuleSet(); }
        for(StereotypeRule rule : this.rules) {
            if(rule.RuleMatchedClass(test)) {
            	matchedStereotypes.add(rule.GetMethodStereotype());
            }
        }
        if(matchedStereotypes.size() == 0) {
        	matchedStereotypes.add(TestStereotype.Unclassified);
        }
        return matchedStereotypes;

	}


}
