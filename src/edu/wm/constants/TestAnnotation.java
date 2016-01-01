package edu.wm.constants;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

public enum TestAnnotation {
	Before ("before"),
	BeforeClass ("beforeClass"),
	After ("after"),
	AfterClass ("afterclass"),
	Test ("test"),
	Ignore("ignore");

	//@Test(timeout=500)
	//@Test(expected=IllegalArgumentException.class)

	private final String name;       


	private TestAnnotation(String s) {
		name = s;
	}


	/**
	 * Return true if TestAnnotation contains the given name
	 * @param name
	 * @return
	 */
	public static boolean contains(HashSet<Annotation> annotations){
		for (TestAnnotation value : TestAnnotation.values()) {
			for(Annotation annotation : annotations){
				if(annotation.getTypeName().getFullyQualifiedName().toLowerCase().equals(value.name)){
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 
	 * @param otherName
	 * @return
	 */
	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName.toLowerCase());
	}


	public String toString() {
		return this.name;
	}

}