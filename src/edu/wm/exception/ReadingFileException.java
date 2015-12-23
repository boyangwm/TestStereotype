package edu.wm.exception;

public class ReadingFileException extends Exception{
	/**
	 * file location
	 */
	private String  filePath;


	public ReadingFileException(String s) {
		filePath = s;
	}


	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString(){
		return ("Process file error : " + this.filePath) ;
	}



}
