package edu.wm.exception;

public class ProjectNotExistException extends Exception {
	
	
	/**
	 * Project location
	 */
	private String  projectLoc;
	
	
	public ProjectNotExistException(String s) {
		projectLoc = s;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString(){
		return ("Project does not exist : " + this.projectLoc) ;
	}

}
