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

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
    
}