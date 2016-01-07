package edu.wm.ast;

import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class AssignmentMapVisitor extends ASTVisitor {
	public VariableAssignmentManager varAssignManager = new VariableAssignmentManager();



	Stack<SimpleName> assignedVars = new Stack<SimpleName>();

	int lastLevel = 0;

	public boolean visit(final SimpleName node) {
		IVariableBinding binding = UtilAST.getBinding(node);
		if(binding == null){
			return false;
		}
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), node, lastLevel == assignedVars.size());
		}
		lastLevel = assignedVars.size();
		return true;
	}




	public boolean visit(final Assignment node) {
		SimpleNameVisitor visitor = new SimpleNameVisitor();
		node.getLeftHandSide().accept(visitor);
		SimpleName leftSimpleName = visitor.getName();
		assignedVars.add(leftSimpleName);
		node.getRightHandSide().accept((ASTVisitor)this);
		return false;
	}


	public void endVisit(final Assignment node) {
		SimpleName popedVar = assignedVars.pop();
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), popedVar, false);
		}
	}



	/* 
	 * This method works for variable declaration.
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.VariableDeclarationFragment)
	 */
	public boolean visit(final VariableDeclarationFragment node) {
		SimpleName variable = node.getName();
		assignedVars.add(variable);
		if(node.getInitializer() != null){
			node.getInitializer().accept((ASTVisitor)this);
		}
		return false;
	}



	/* (non-Javadoc)
	 * The end of variable declaration. Similar to assignment.
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#endVisit(org.eclipse.jdt.core.dom.VariableDeclarationFragment)
	 */
	public void endVisit(final VariableDeclarationFragment node) {
		SimpleName popedVar = assignedVars.pop();
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), popedVar, false);
		}
	}

}
