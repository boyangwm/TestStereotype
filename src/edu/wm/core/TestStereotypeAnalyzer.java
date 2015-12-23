package edu.wm.core;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import edu.wm.ast.MethodDeclarationVisitor;
import edu.wm.exception.ProjectNotExistException;
import edu.wm.exception.ReadingFileException;

public class TestStereotypeAnalyzer {


	//The project location
	public String projectLoc = "";


	//All java files in the project 
	//public HashSet<String> javaFiles = new HashSet<String>();

	//Map of method signature to method defination
	public HashMap<String, MethodDeclaration> mapSignToMethod = new  HashMap<String, MethodDeclaration>();


	/**
	 * Analyze the given project
	 * @param projectLoc
	 * @throws Exception
	 */
	public void analyze(String projectLoc) throws Exception{
		loadFilesInfo(projectLoc);
		
		
	}


	//load all java files' info into the memory
	public void loadFilesInfo(String projectLoc) throws ProjectNotExistException, IOException{
		this.projectLoc = projectLoc;
		String currentFile = "";
		Collection<File> files = null;
		try{
			//iterate the project and find all java files 
			files = FileUtils.listFiles(new File(this.projectLoc), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		}catch(IllegalArgumentException ex){
			MyLog.LOGGER.log(Level.SEVERE, "Error occur in TestStereotypeAnalyzer.loadFilesInfo.", ex);
			throw new ProjectNotExistException(projectLoc);
		}

		for (File file : files) {
			if(file.getName().endsWith(".java")){
				currentFile = file.getAbsolutePath();
				String unitName = currentFile.substring(currentFile.lastIndexOf(File.separator)+1).replace(".java", "");
				//System.out.println(file.toString());
				String fileString = "";
				try{
					fileString = Util.readFileToString(currentFile);
				} catch(IOException ex){
					MyLog.LOGGER.log(Level.SEVERE, "Error occur in TestStereotypeAnalyzer.loadFilesInfo.", ex);
					throw ex;
				}

				//Get all method definition in the file 
				CompilationUnit cu = UtilAST.getASTAndBindings(fileString, projectLoc, unitName);
				MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
				cu.accept(visitor);
				for (MethodDeclaration method : visitor.getMethods()) {
					String sign = UtilAST.getMethodSignature(method);
					mapSignToMethod.put(sign, method);
				}
				cu.accept(new ASTVisitor(){
					@Override public boolean visit(MarkerAnnotation makerAnnotation) {
						System.out.println("maker:" + makerAnnotation.getTypeName());
						return false;
					}
				});
			}
		}
	}

}
