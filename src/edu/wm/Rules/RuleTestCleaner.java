package edu.wm.Rules;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleTestCleaner extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleTestCleaner()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		HashSet<Annotation> annotations = mAnalyzer.getAnnotation();
		for(Annotation annotation : annotations){
			String annoationStr =  annotation.getTypeName().getFullyQualifiedName();
			if(annoationStr.toLowerCase().equals("after") || annoationStr.toLowerCase().equals("afterclass")){
				return true;
			}
		}
		return false;

	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Cleaner;
	}
}
