package main.java.edu.sc.csce740.model;

@SuppressWarnings("serial")
/*
 * Class used for throwing an AdminRightsException
 */
public class AdminRightsException extends Exception {

	/*
	 * Default Constructor
	 */
	public AdminRightsException() {
	}
	
	public AdminRightsException(String message) {
		super("ACTION NOT ALLOWED. " + message);
	}	
}
