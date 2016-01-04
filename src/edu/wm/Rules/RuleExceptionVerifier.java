package edu.wm.Rules;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;

import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

/**
 * Rule for test cases checking Exceptions 
 * @author Boyang
 *
 */
public class RuleExceptionVerifier extends StereotypeRule{
	/**
	 * Creates a new RuleExceptionVerifier using default values for data sets.
	 */
	public RuleExceptionVerifier()  {super(); }

	
	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		HashSet<Annotation> annotations = mAnalyzer.getAnnotation();
		for(Annotation annotation : annotations){
			String annoationStr =  annotation.getTypeName().getFullyQualifiedName();
			if(annoationStr.toLowerCase().equals("test") && annotation.isNormalAnnotation()){

				NormalAnnotation nannotation = (NormalAnnotation)annotation;
				List<MemberValuePair> valuePairs = nannotation.values();
				for(MemberValuePair pair : valuePairs){
					Expression exp = pair.getValue();
					if(exp.resolveTypeBinding().getName().contains("Exception")){
						return true;
					}
					//.getSuperclass();
				}
			}
		}
		return false;
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Exception;
	}
}
