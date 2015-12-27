package edu.wm.Rules;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public abstract class StereotypeRule {

    /**
     *  Creates a new StereotypeRule using default values for data sets.
     */
    public StereotypeRule(){
        InitializeMembers();
    }

    /**
     * Determines whether the supplied AnalyzedElement matches the conditions of this rule.
     * @param mAnalyzer
     * @return
     */
    public boolean RuleMatchedClass(TestUnderAnalysis mAnalyzer) {
        return MakeClassification(mAnalyzer);
    }

    
    /**
     * Performs further rule testing beyond the RuleMatchedClass method.
     * @param mAnalyzer
     * @return
     */
    protected abstract boolean MakeClassification(TestUnderAnalysis mAnalyzer);

    
    /**
     * Returns the test stereotype
     * @return
     */
    public abstract TestStereotype GetMethodStereotype();
    
    
    /**
     * Initialize members (for extention)
     */
    private void InitializeMembers() {

    }
    
    

}
