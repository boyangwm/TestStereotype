package edu.wm.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;

public class AssertionInvocationVisitor extends ASTVisitor {
	List<MethodInvocation> methods = new ArrayList<MethodInvocation>();

	@Override
	public boolean visit(MethodInvocation node) {
		//System.out.println(node.getName().getFullyQualifiedName());
		if(Assertions.contains(node.getName().getFullyQualifiedName())){
			methods.add(node);
		}
		return true;
	}

	public List<MethodInvocation> getAssertions() {
		return methods;
	}
} 