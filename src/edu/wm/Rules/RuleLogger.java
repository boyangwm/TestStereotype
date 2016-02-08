package edu.wm.Rules;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;

public class RuleLogger extends StereotypeRule {
	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleLogger()  {super(); }



	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {
		if(mAnalyzer.matchedRules.size() == 0){
			//Logger or println
			HashSet<MethodInvocation> calls = mAnalyzer.allCalls;
			for(MethodInvocation call : calls){
				if(IsLogger(call)){
					return true;
				}
			}

			//not pass the check
			return false;
		}
		return false;
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Logger;
	}



	private boolean IsLogger(MethodInvocation call){
		IMethodBinding binding = call.resolveMethodBinding();
		 if(binding == null)
	     {                    
	        return false;
	     }
	     else
	     {
	         if(binding.getDeclaringClass().getQualifiedName().contains("PrintStream")){
	        	 return true;
	         }
	         if(binding.getDeclaringClass().getQualifiedName().contains("Logger")){
	        	 return true;
	         }
	     }
		 return false;
//		ITypeBinding bindingFunc = (ITypeBinding) call.getName().resolveTypeBinding();
//		if(bindingFunc == null){
//			return false;
//		}else{
//			//Java predefined functions 
//			if(((ITypeBinding) bindingFunc).getQualifiedName().contains("System.out")){
//				return true;
//			}
//			if(((ITypeBinding) bindingFunc).getQualifiedName().contains("Logger")){
//				return true;
//			}
//			//Internal functions
//			return false;
//		}
	}
}

