package edu.wm.ast;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class UtilAST {

	/**
	 * Get CompilationUnit by source string (file stream info)
	 * @param source
	 * @param projectPath
	 * @param unitName
	 * @return
	 */
	public static CompilationUnit getASTAndBindings(String source, String projectPath, String unitName) {
		String[] sources = { projectPath };
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setSource(source.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setEnvironment(null, sources, new String[] { "UTF-8" }, true);

		Hashtable<String, String> options = JavaCore.getDefaultOptions();
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
		options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);

		parser.setCompilerOptions(options);
		parser.setUnitName(unitName);

		return (CompilationUnit) parser.createAST(null);
	}




	/**
	 * Get method signature 
	 * @param method
	 * @return
	 */
	public static String getMethodSignature(MethodDeclaration method){
		StringBuilder sb = new StringBuilder();
		String className = "";

		//System.out.print("Method name: " + method.getName().getFullyQualifiedName());
		ASTNode parentClass = method;
		while(true){
			parentClass = parentClass.getParent();
			if(parentClass instanceof TypeDeclaration){
				ITypeBinding parentBind = ((TypeDeclaration)parentClass).resolveBinding();
				if(parentBind == null){
					className = ((TypeDeclaration)parentClass).getName().getFullyQualifiedName();
				}else{
					className = parentBind.getQualifiedName();
				}
				break;
			}else if(parentClass instanceof EnumDeclaration){
				ITypeBinding parentBind = ((EnumDeclaration)parentClass).resolveBinding();
				if(parentBind == null){
					className = ((TypeDeclaration)parentClass).getName().getFullyQualifiedName();
				}else{
					className = parentBind.getQualifiedName();
				}
				break;
			}
		}
		sb.append(className);
		sb.append("." + method.getName().getFullyQualifiedName());
		sb.append("(");
		List paras = method.parameters();
		if(paras != null){
			for(Object para : paras){
				if(para instanceof SingleVariableDeclaration){
					ITypeBinding typeBinding = ((SingleVariableDeclaration)para).getType().resolveBinding();
					sb.append(typeBinding.getQualifiedName());
				}else{
					//th
				}
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	
	/**
	 * Get IVariableBinding of a simpleName
	 * @param node
	 * @return
	 */
	public static IVariableBinding getBinding(final SimpleName node){
		IVariableBinding variableBinding = null;  
		if (node.resolveBinding() instanceof IVariableBinding) {
			return (IVariableBinding)node.resolveBinding();
		}else{
			return null;
		}
	}

}
