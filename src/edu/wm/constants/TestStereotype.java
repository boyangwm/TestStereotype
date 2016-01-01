package edu.wm.constants;

public enum TestStereotype {
	
	//category 1
	Setter("TestSetter", "This type test case sets the evironment of the test case."),
	Cleaner("TestCleaner", "This type test case cleans the evironment of the test case."),
	Exception("ExceptionVerifier", "This type test case verifies whether the test case throws expected exception."),
	Condition("ConditionVerifier", "This type of test case verifies boolean conditions."),
	Equality("EqualityVerifier", "This type of test case verifies whether the two arguments’ values are equal."),
	Identity("IdentityVerifier", "This type of test case verifies whether two arguments reference the same object."),
	Utility("UtilityVerifier", "This type of test case verifies control of the test process."),
	Hybrid("HybridVerifier", "This type of test case verifies whether two arguments reference the same object."),
	Unclassified("UnclassifiedVerifier", "This type of test case verifies whether two arguments reference the same object.");
	
//	assertTrue("asserttrue"),
//	assertFalse("assertfalse"),
//	fail("fail"),
//	assertEquals("assertequals"),
//	assertArrayEquals("assertarrayequals"),
//	assertNotNull("assertnotnull"),
//	assertNull("assertnull"),
//	assertSame("assertsame"),
//	assertThat("assertthat");

	//@Test(timeout=500)
	//@Test(expected=IllegalArgumentException.class)

	private final String name;     
	private final String desc;   

	
	private TestStereotype(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	
	public String toString() {
		return this.name;
	}


}
