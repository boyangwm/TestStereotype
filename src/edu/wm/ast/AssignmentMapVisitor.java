package edu.wm.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class AssignmentMapVisitor extends ASTVisitor {
	public VariableAssignmentManager varAssignManager = new VariableAssignmentManager();


	public HashMap<MethodInvocation, HashSet<SimpleName>> AsserstionToRelatedSM = new HashMap<MethodInvocation, HashSet<SimpleName>>();

	public HashSet<MethodInvocation> internalCalls = new HashSet<MethodInvocation> ();



	/**
	 * Stores all assertions
	 */
	private ArrayList<MethodInvocation> assertions;


	/**
	 * Uses for assignment slicing
	 */
	private Stack<SimpleName> assignedVars = new Stack<SimpleName>();


	private Stack<Boolean> isAssigningVar = new  Stack<Boolean>();

	int lastLevel = 0;


	public AssignmentMapVisitor(){
		this.assertions = new ArrayList<MethodInvocation>();
	}


	public AssignmentMapVisitor(ArrayList<MethodInvocation> assertions){
		this.assertions = assertions;
	}



	public boolean visit(final SimpleName node) {

		//ignore useless key words
		IVariableBinding binding = UtilAST.getBinding(node);
	
		if(binding == null){
			return false;
		}

		//ignore field part
		if(UtilAST.IsField(node)){
			return true;
		}
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), node, lastLevel == assignedVars.size());
			isAssigningVar.pop();
			isAssigningVar.push(true);

		}
		lastLevel = assignedVars.size();
		return true;
	}



	public boolean visit(final Assignment node) {
		FirstSimpleNameVisitor visitor = new FirstSimpleNameVisitor();
		node.getLeftHandSide().accept(visitor);
		SimpleName leftSimpleName = visitor.getName();
		assignedVars.push(leftSimpleName);
		isAssigningVar.push(false);
		node.getRightHandSide().accept((ASTVisitor)this);
		return false;
	}


	public void endVisit(final Assignment node) {
		SimpleName popedVar = assignedVars.pop();
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), popedVar, false);
		}else{
			lastLevel = 0;
		}
		boolean isAssigned = isAssigningVar.pop();
		if(isAssigned == false){
			varAssignManager.ClearByVar(popedVar);
		}

	}



	/* 
	 * This method works for variable declaration.
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.VariableDeclarationFragment)
	 */
	public boolean visit(final VariableDeclarationFragment node) {
		SimpleName variable = node.getName();
		assignedVars.add(variable);
		isAssigningVar.push(false);
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
		isAssigningVar.pop();
		if(!assignedVars.empty()){
			varAssignManager.AddNewRelations(assignedVars.peek(), popedVar, false);
		}
	}



	/* 
	 * updates AsserstionToRelatedSM when visit MethodInvocation 	 
	 * */
	public boolean visit(final MethodInvocation node) {
		if(this.assertions.contains(node)){
			SimpleVarNameVisitor smVisitor = new SimpleVarNameVisitor();
			node.accept(smVisitor);
			HashSet<SimpleName> names = smVisitor.getNames();
			HashSet<SimpleName> relatedNameSet = new HashSet<SimpleName>();
			for(SimpleName name : names){
				relatedNameSet.addAll(varAssignManager.getAssigningVars(name));
			}
			AsserstionToRelatedSM.put(node, relatedNameSet);
		}
		if(UtilAST.isInternalCall(node)){
			internalCalls.add(node);
			
			//Self updated. such as, a.execution();
			if(node.getExpression() instanceof SimpleName){
				SimpleName theInvokedVar = (SimpleName)node.getExpression();
				varAssignManager.AddNewRelations(theInvokedVar, theInvokedVar, false);
			}
		}
		return true;
	}


}
