package edu.wm.ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationVisitor extends ASTVisitor {
  List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();

  @Override
  public boolean visit(MethodDeclaration node) {
    methods.add(node);
    return true;
  }

  public List<MethodDeclaration> getMethods() {
    return methods;
  }
} 