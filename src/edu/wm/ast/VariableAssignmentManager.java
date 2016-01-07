package edu.wm.ast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;

public class VariableAssignmentManager {


	/**
	 * Manage assignment relations on fly 
	 */
	HashMap<IVariableBinding, HashSet<SimpleName>> theMap= new HashMap<IVariableBinding, HashSet<SimpleName> >();


	/**
	 * @param assignedVar
	 * @param assigningVar
	 * @param refresh
	 */
	public void AddNewRelations(SimpleName assignedVarSN, SimpleName assigningVar, boolean refresh) {
		IVariableBinding assignedVar = UtilAST.getBinding(assignedVarSN);
		if(theMap.containsKey(assignedVar)){
			if(refresh){
				//add up all variable 
				theMap.get(assignedVar).addAll(getAssigningVars(assigningVar));
			}else{
				//rewrite everything
				theMap.put(assignedVar, getAssigningVars(assigningVar));
			}
		}else{
			theMap.put(assignedVar, getAssigningVars(assigningVar));
		}
	}


	public HashSet<SimpleName> getAssigningVars(SimpleName name){
		IVariableBinding binding = UtilAST.getBinding(name);
		HashSet<SimpleName> tempSet = new  HashSet<SimpleName>();
		tempSet.add(name);
		if(this.theMap.containsKey(binding)){
			tempSet.addAll(this.theMap.get(binding));
		}
		return tempSet;
	}




	public HashSet<SimpleName> getAssigningVars(String name){
		for (Entry<IVariableBinding, HashSet<SimpleName>> entry : theMap.entrySet()) {
			String key = entry.getKey().getName();
			if(key.equals(name)){
				return entry.getValue();
			}
		}
		return new HashSet<SimpleName>();
	}
}
