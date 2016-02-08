package edu.wm.Rules;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleIgnoredMethod  extends StereotypeRule {

	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleIgnoredMethod()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		HashSet<Annotation> annotaions = mAnalyzer.getAnnotation();
		for(Annotation ann : annotaions){
			if(ann.getTypeName().getFullyQualifiedName().toLowerCase().equals("ignore"))
				return true;
		}
		return false;
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Ignore;
	}
}
