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
		if(assignedVar == null){
			return;
		}
		if(theMap.containsKey(assignedVar)){
			if(refresh){
				//add up all variable 
				theMap.get(assignedVar).addAll(GenerateAssigningByVar(assigningVar));
			}else{
				//rewrite everything
				theMap.put(assignedVar, GenerateAssigningByVar(assigningVar));
			}
		}else{
			theMap.put(assignedVar, GenerateAssigningByVar(assigningVar));
		}
	}
	
	
	
	/**
	 * Clear assignment info for the variable of given name
	 * @param name
	 */
	public void ClearByVar(SimpleName name){
		IVariableBinding assignedVar = UtilAST.getBinding(name);
		if(theMap.containsKey(assignedVar)){
			theMap.put(assignedVar, new HashSet<SimpleName>());
		}
		
	}


	/**
	 * Get assigningVars and itself
	 * @param name
	 * @return
	 */
	private HashSet<SimpleName> GenerateAssigningByVar(SimpleName name){
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
	
	
	
	public HashSet<SimpleName> getAssigningVars(SimpleName name){
		IVariableBinding binding = UtilAST.getBinding(name);
		for (Entry<IVariableBinding, HashSet<SimpleName>> entry : theMap.entrySet()) {
			IVariableBinding key = entry.getKey();
			if(key != null && key.equals(binding)){
				return entry.getValue();
			}
		}
		return new HashSet<SimpleName>();
	}
}
