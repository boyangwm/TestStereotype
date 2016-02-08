package edu.wm.constants;

public enum TestStereotype {

	//category 1
	Initializer("TestInitializer",1, "This type test case sets the evironment of the test case.", 1),
	Cleaner("TestCleaner",2, "This type test case cleans the evironment of the test case.", 1),
	Exception("ExceptionVerifier",3, "This type test case verifies whether the test case throws expected exception.", 1),
	BooleanV("BooleanVerifier",4,  "This type of test case verifies boolean conditions.", 1),
	NullVerifier("NullVerifier",5, "This type of test case verifies null conditions", 1),
	Equality("EqualityVerifier",6, "This type of test case verifies whether the two arguments values are equal.", 1),
	Identity("IdentityVerifier",7, "This type of test case verifies whether two arguments reference the same object.", 1),
	Utility("UtilityVerifier", 8, "This type of test case verifies control of the test process.", 1),
	ConditionMatcher("ConditionMatcher",9, "This type of test case verifies matcher statement.", 1), //condition matcher
	Hybrid("HybridVerifier",10, "This type of test case verifies whether two arguments reference the same object.", 1),
	Assumption("AssumptionSetter",11, "This type of test case assumes some statements in the testing." , 1),
	Ignore("IgnoredMethod",12, "This type of test case is ignored.",1),
	Unclassified("Unclassified",13, "This type of test case is unclassified", 1),
	//category 2
	Iterative("IterativeVerifier", 14, "This type of test case verifies assertions iteratively", 2),
	Branch("BranchVerifier", 15, "This type of test case verifies assertions in a branch condition", 2),
	InternalCall("InternalCallVerifier", 16, "This type of test case verifies assertions related to internal function call", 2),
	ApiUtility("APIUtilityVerifier", 17,"This type of test case verifies assertions related to Java APIs", 2),
	PublicField("PublicFieldVerifier", 18, "This type of test case verifies assertions related to public fields", 2),
	Execution("ExecutionTester", 19, "This type of test case runs/tests internal functions.", 2),
	Empty("EmptyTester", 20, "This type of test case has no statement in the function.", 2),
	Logger("Logger",21, "This type of test case is a logger.", 2);

	private final String name;     
	private final String desc;   
	private final int category;
	private final int id;

	private TestStereotype(String name, int id,String desc, int category) {
		this.name = name;
		this.id = id;
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
	
	
	public int getId(){
		return this.id;
	}

}
