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
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WildcardType;


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



	public static boolean IsInvockedMethod(final SimpleName node){
		ASTNode parent = node.getParent();
		return parent instanceof MethodInvocation;
	}



	public static boolean IsInvockedInternalMethod(final SimpleName node){
		//return !IsInvockedExternalMethod(node);
		ASTNode parent = node.getParent();
		if( parent instanceof MethodInvocation){
			MethodInvocation mInvoke = (MethodInvocation)parent;
			
			ITypeBinding bindingFunc = (ITypeBinding) mInvoke.getName().resolveTypeBinding();
			ITypeBinding binding = null;
			if(mInvoke.getExpression() != null){
				binding =  mInvoke.getExpression().resolveTypeBinding();
			}
			if(binding == null){
				//external functions
				return false;
			}else{
				//Java predefined functions 
				if(((ITypeBinding) bindingFunc).getQualifiedName().startsWith("java")){
					return false;
				}
				if(((ITypeBinding) binding).getQualifiedName().startsWith("java")){
					return false;
				}
				//Internal functions
				return true;
			}
		}else{
			return false;
		}
	}



	public static boolean IsInvockedExternalMethod(final SimpleName node){
		ASTNode parent = node.getParent();
		if( parent instanceof MethodInvocation){
			MethodInvocation mInvoke = (MethodInvocation)parent;
			ITypeBinding bindingFunc = (ITypeBinding) mInvoke.getName().resolveTypeBinding();
			ITypeBinding binding = null;
			if(mInvoke.getExpression() != null){
				binding =  mInvoke.getExpression().resolveTypeBinding();
			}
			if(binding == null){
				//external functions
				return true;
			}else{
				//Java predefined functions 
				if(((ITypeBinding) bindingFunc).getQualifiedName().startsWith("java")){
					return true;
				}
				if(((ITypeBinding) binding).getQualifiedName().startsWith("java")){
					return true;
				}
				//Internal functions
				return false;
			}
		}else{
			return false;
		}
	}



	public static boolean isInternalCall(MethodInvocation call){
		
		//ITypeBinding binding = (ITypeBinding) call.getName().resolveTypeBinding();    //orig
		ITypeBinding binding = null;
		if(call.getExpression() != null){
			binding =  call.getExpression().resolveTypeBinding();
		}
		//ICompilationUnit unit = (ICompilationUnit) binding.getJavaElement().getAncestor( IJavaElement.COMPILATION_UNIT );
		if(binding == null){
			//external functions
			//return false;
			return true;
		}else{
			//Java predefined functions 
			if(((ITypeBinding) binding).getQualifiedName().startsWith("java")){
				return false;
			}
			
			//Type t = UtilAST.typeFromBinding(call.getAST(), binding);
			//Internal functions
			return true;
		}
		
	}

	public static boolean IsQualifier(final SimpleName node){
		ASTNode parent = node.getParent();
		if(parent instanceof QualifiedName){
			QualifiedName qualifiedName = (QualifiedName)parent;
			if(qualifiedName.getQualifier() == node){
				return true;
			}
		}
		return false;
	}




	public static boolean IsField(final SimpleName node){
		ASTNode parent = node.getParent();
		if(parent instanceof QualifiedName){
			QualifiedName qualifiedName = (QualifiedName)parent;
			if(qualifiedName.getName() == node){
				return true;
			}
		}
		return false;
	}
	
	
	public static Type typeFromBinding(AST ast, ITypeBinding typeBinding) {
	    if( ast == null ) 
	        throw new NullPointerException("ast is null");
	    if( typeBinding == null )
	        throw new NullPointerException("typeBinding is null");

	    if( typeBinding.isPrimitive() ) {
	        return ast.newPrimitiveType(
	            PrimitiveType.toCode(typeBinding.getName()));
	    }

	    if( typeBinding.isCapture() ) {
	        ITypeBinding wildCard = typeBinding.getWildcard();
	        WildcardType capType = ast.newWildcardType();
	        ITypeBinding bound = wildCard.getBound();
	        if( bound != null ) {
	            capType.setBound(typeFromBinding(ast, wildCard.getBound()), wildCard.isUpperbound());
	        }
	        return capType;
	    }

	    if( typeBinding.isArray() ) {
	        Type elType = typeFromBinding(ast, typeBinding.getElementType());
	        return ast.newArrayType(elType, typeBinding.getDimensions());
	    }

	    if( typeBinding.isParameterizedType() ) {
	        ParameterizedType type = ast.newParameterizedType(
	            typeFromBinding(ast, typeBinding.getErasure()));

	        @SuppressWarnings("unchecked")
	        List<Type> newTypeArgs = type.typeArguments();
	        for( ITypeBinding typeArg : typeBinding.getTypeArguments() ) {
	            newTypeArgs.add(typeFromBinding(ast, typeArg));
	        }

	        return type;
	    }

	    // simple or raw type
	    String qualName = typeBinding.getQualifiedName();
	    if( "".equals(qualName) ) {
	        throw new IllegalArgumentException("No name for type binding.");
	    }
	    return ast.newSimpleType(ast.newName(qualName));
	}
	
}
