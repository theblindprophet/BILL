package main.java.edu.sc.csce740.model;

@SuppressWarnings("serial")
/*
 * Class used for throwing an EditRecordException
 */
public class EditRecordException extends Exception {

	private String message;

	/*
	 * Default constructor
	 */
	public EditRecordException(String message) {
		this.message = message;
	}

	// Overrides Exception's getMessage()
	@Override
	public String getMessage() {
		return message;
	}
}
