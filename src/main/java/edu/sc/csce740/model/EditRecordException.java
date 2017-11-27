package main.java.edu.sc.csce740.model;

@SuppressWarnings("serial")
public class EditRecordException extends Exception{

	private String message;
		
	public EditRecordException(String message) {
		this.message = message;
	}
	
	// Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }
}
