package edu.wm.constants;

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
	public static boolean contains(String name){
		for (TestAnnotation annotation : TestAnnotation.values()) {
			if(name.toLowerCase().equals(annotation.name)){
				return true;
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