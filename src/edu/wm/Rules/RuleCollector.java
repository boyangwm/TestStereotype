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
    
    

	/**
	 * @param test
	 * @return
	 */
	public void ApplyRules(TestUnderAnalysis test) {
        if(this.rules == null) { DefineRuleSet(); }
        for(StereotypeRule rule : this.rules) {
            if(rule.RuleMatchedClass(test)) {
            	test.matchedRules.add(rule.GetMethodStereotype());
            }
        }
//        if(test.matchedRules.size() == 0) {
//        	test.matchedRules.add(TestStereotype.Unclassified);
//        }
	}


}
