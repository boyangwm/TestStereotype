package edu.wm.Rules;

import org.eclipse.jdt.core.dom.MarkerAnnotation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class EvironmentCleaner extends StereotypeRule {
	/**
	 * Creates a new EvironmentSetter using default values for data sets.
	 */
	public EvironmentCleaner()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		MarkerAnnotation annotation = mAnalyzer.getAnnotation();
		String annoationStr =  annotation.getTypeName().getFullyQualifiedName();
		if(annoationStr.toLowerCase().equals("after") || annoationStr.toLowerCase().equals("afterclass")){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Cleaner;
	}
}
