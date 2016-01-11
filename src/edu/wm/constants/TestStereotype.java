package edu.wm.constants;

public enum TestStereotype {

	//category 1
	Setter("TestSetter", "This type test case sets the evironment of the test case.", 1),
	Cleaner("TestCleaner", "This type test case cleans the evironment of the test case.", 1),
	Exception("ExceptionVerifier", "This type test case verifies whether the test case throws expected exception.", 1),
	Condition("ConditionVerifier", "This type of test case verifies boolean conditions.", 1),
	Equality("EqualityVerifier", "This type of test case verifies whether the two arguments’ values are equal.", 1),
	Identity("IdentityVerifier", "This type of test case verifies whether two arguments reference the same object.", 1),
	Utility("UtilityVerifier", "This type of test case verifies control of the test process.", 1),
	Hybrid("HybridVerifier", "This type of test case verifies whether two arguments reference the same object.", 1),
	Unclassified("UnclassifiedVerifier", "This type of test case verifies whether two arguments reference the same object.", 1),
	//category 2
	Iterative("IterativeVerifier", "This type of test case verifies assertions iteratively", 2),
	Branch("BranchVerifier", "This type of test case verifies assertions in a branch condition", 2),
	InternalCall("InternalCallVerifier", "This type of test case verifies assertions related to internal function call", 2),
	ExternalCall("ExternalCallVerifier", "This type of test case verifies assertions related to external function call", 2),
	QualifiedField("QualifiedFieldVerifier", "This type of test case verifies assertions related to qualified field", 2);
	
	

	private final String name;     
	private final String desc;   
	private final int category;

	private TestStereotype(String name, String desc, int category) {
		this.name = name;
		this.desc = desc;
		this.category = category;
	}

	
	public int getCategory(){
		return this.category;
	}

	public String toString() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}
}
