package edu.wm.ast;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MarkerAnnotationVisitor extends ASTVisitor {

	MarkerAnnotation annotation = null;
	@Override
	public boolean visit(MarkerAnnotation node) {
		annotation = node;
		return false;
	}

	public MarkerAnnotation getAnnotation() {
		return annotation;
	}
}
