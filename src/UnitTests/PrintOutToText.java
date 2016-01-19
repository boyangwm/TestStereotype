package UnitTests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintOutToText {
	public static PrintStream outStream;
	
	public PrintOutToText(){
		try {
			outStream = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void print(String str){
		//System.setOut(out);
	}
	

}
