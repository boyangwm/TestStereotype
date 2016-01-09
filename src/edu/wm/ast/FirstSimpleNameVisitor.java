package edu.wm.ast;



import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

public class FirstSimpleNameVisitor extends ASTVisitor {
	SimpleName name = null;

	@Override
	public boolean visit(SimpleName node) {
		this.name = node;
		return false;
	}

	public SimpleName getName() {
		return this.name;
	}
} 