package main.java.edu.sc.csce740.model;

import java.util.ArrayList;

public class DHCS {

	private ArrayList<StudentRecord> studentRecords;
	private static User currentUser;
	
	public DHCS()
	{
		this.studentRecords = new ArrayList<StudentRecord>();
	}

	public ArrayList<StudentRecord> getStudentRecords() {
		return studentRecords;
	}

	public void setStudentRecords(ArrayList<StudentRecord> studentRecords) {
		this.studentRecords = studentRecords;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		DHCS.currentUser = currentUser;
	}

	public User getUser(String userId) {
		//WriteThis
		return null;
	}
	
	private void initialize()
	{
		
	}
	private void updateInternalRecord(StudentRecord record)
	{
		
	}
	private String readFile(String fileName) throws Exception
	{
		return fileName;
		
	}
	private void writeFile(String fileName, String data) throws Exception
	{
		
	}
	public void writeRecord(String userID, StudentRecord record) throws Exception
	{
		
	}
	public StudentRecord getRecord(String userID) throws Exception
	{
		return null;
		
	}
	public Transaction[] getCharges(String userId, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear)
	{
		return null;
		
	}

}