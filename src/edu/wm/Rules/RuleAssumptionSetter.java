package edu.wm.Rules;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleAssumptionSetter extends StereotypeRule {

	/**
	 * Creates a new RuleAssumptionTester using default values for data sets.
	 */
	public RuleAssumptionSetter()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		HashSet<MethodInvocation> allCalls = mAnalyzer.allCalls;
		for(MethodInvocation call : allCalls){
			String name = call.getName().toString().toLowerCase();
			if(name.equals("assumethat") || name.equals("assumetrue")){
				return true;
			}
		}
		return false;
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Assumption;
	}

}
