package edu.wm.ast;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;

public class SimpleVarNameVisitor  extends ASTVisitor {
	HashSet<SimpleName> names = new HashSet<SimpleName>();

	@Override
	public boolean visit(SimpleName node) {
		
		IVariableBinding binding = UtilAST.getBinding(node);
		if(binding == null){
			return false;
		}
		if(UtilAST.IsField(node)){
			return true;
		}
		
		this.names.add(node);
		return false;
	}

	public HashSet<SimpleName> getNames() {
		return this.names;
	}
}
