package edu.wm.ast;


import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;

public class MarkerAnnotationVisitor extends ASTVisitor {

	HashSet<Annotation> annotations = new HashSet<Annotation>();
	@Override
	public boolean visit(MarkerAnnotation node) {
		annotations.add(node);
		return false;
	}
	
	
	
	@Override
	public boolean visit(NormalAnnotation node) {
		annotations.add(node);
		return false;
	}

	
	
	@Override
	public boolean visit(SingleMemberAnnotation node) {
		annotations.add(node);
		return false;
	}
	
	

	public HashSet<Annotation> getAnnotation() {
		return annotations;
	}
}
