package edu.wm.constants;

public enum Assertions {

	//reference http://junit.sourceforge.net/javadoc/org/junit/Assert.html
	assertTrue("asserttrue"),
	assertFalse("assertfalse"),
	fail("fail"),
	assertEquals("assertequals"),
	assertArrayEquals("assertarrayequals"),
	assertNotNull("assertnotnull"),
	assertNull("assertnull"),
	assertSame("assertsame"),
	assertThat("assertthat");
	//@Test(timeout=500)
	//@Test(expected=IllegalArgumentException.class)

	
	// NUnit http://www.nunit.org/
	//	IsTrue("istrue"), 
	//	IsFalse("isfalse"), 
	//	IsNull("isnull"), 
	//	IsNotNull("isnotnull"), 
	//	IsNaN("isnan"), 
	//	IsEmpty("isempty"),
	//	IsNotEmpty("isnotempty"),
	//	AreEqual("areequal"),
	//	AreNotEqual("arenotequal"),
	//	AreSame("aresame"), 
	//	AreNotSame("arenotsame"),
	//	Contains("contains"),
	//	Greater("greater"),
	//	Less("less"),
	//	IsInstanceOfType("isinstanceoftype"), 
	//	IsNotInstanceOfType("isnotinstanceoftype"), 
	//	IsAssignbleFrom("isassignblefrom"), 
	//	IsNotAssignbleFrom("isnotassignblefrom"),
	//	Fail("fail"),
	//	Ignore("ignore"),
	//	StartsWith("startwith"), 
	//	EndsWith("endswith"), 
	//	AreEqualIgnoringCase("areequalignoringcase");

	private final String name;       


	private Assertions(String s) {
		name = s;
	}


	/**
	 * Return true if Assertions contains the given name
	 * @param name
	 * @return
	 */
	public static boolean contains(String name){
		for (Assertions annotation : Assertions.values()) {
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
