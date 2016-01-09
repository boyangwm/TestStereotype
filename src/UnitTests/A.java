package UnitTests;


import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class A {
	private class B{
		public int f = 2;
		public void foo(int p){
			f = p;
		}
	}

	B b = new B(); 

	@Test 	
	public void testHelloWorld() { 
		int a = 1;
		b.f = 3;
		b.foo(a);
	}  
}