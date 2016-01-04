package edu.wm.Rules;


import java.util.HashSet;

import org.eclipse.jdt.core.dom.Annotation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

/**
 * Rule for test cases setting up
 * @author Boyang
 *
 */
public class RuleTestSetter  extends StereotypeRule {

	/**
	 * Creates a new RuleTestSetter using default values for data sets.
	 */
	public RuleTestSetter()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		HashSet<Annotation> annotations = mAnalyzer.getAnnotation();
		for(Annotation annotation : annotations){
			String annoationStr =  annotation.getTypeName().getFullyQualifiedName();
			if(annoationStr.toLowerCase().equals("before") || annoationStr.toLowerCase().equals("beforeclass")){
				return true;
			}
		}
		return false;

	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Setter;
	}

}
