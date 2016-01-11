package UnitTests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class A {
	private class B{
		public int f = 2;
		public int foo(int p){
			return f = p;
		}
	}

	B b = new B(); 

	@Test 	
	public void testHelloWorld() { 
		int a = 1;
		b.f = 3;
		a = b.foo(a);
	}  
}