package edu.wm.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestHelloWorld {

	private HelloWorld h;

	@Before
	public void setUp() throws Exception 
	{
		h = new HelloWorld();
	}

	@Test
	public void testHelloEmpty() 
	{
		assertEquals(h.getName(),"");
		assertEquals(h.getMessage(),"Hello!");
	}

	@Test
	public void testHelloWorld() 
	{
		h.setName("World");
		Assert.assertEquals(h.getName(),"World");
		assertEquals(h.getMessage(),"Hello World!");
	}


	@Test(expected=IllegalArgumentException.class)
	public void testException(String input) {
		 System.out.println("@Test(expected) will check for specified exception during its run");
	}
}