package edu.wm.Rules;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.Annotation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleExecutionTester  extends StereotypeRule {
	/**
	 * Creates a new RuleTestCleaner using default values for data sets.
	 */
	public RuleExecutionTester()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		if(mAnalyzer.getAssertionStmts().size() == 0 && mAnalyzer.internalCalls.size() > 0){
			HashSet<Annotation> annotations = mAnalyzer.getAnnotation();
			for(Annotation annotation : annotations){
				String annoationStr =  annotation.getTypeName().getFullyQualifiedName();
				//should not be a test case setter
				if(annoationStr.toLowerCase().equals("before") || annoationStr.toLowerCase().equals("beforeclass")|| 
						annoationStr.toLowerCase().equals("after")|| annoationStr.toLowerCase().equals("afterclass")){
					return false;
				}
			}
			//return false;
			return true;
		}else{
			return false;
		}
	}



	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Execution;
	}
}
