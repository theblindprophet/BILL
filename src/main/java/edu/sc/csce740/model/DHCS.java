package main.java.edu.sc.csce740.model;

import java.util.ArrayList;

public class DHCS {

	private ArrayList<StudentRecord> studentRecords;
	private ArrayList<User> users;
	private static User currentUser;
	
	public DHCS()
	{
		this.studentRecords = new ArrayList<StudentRecord>();
		this.users = new ArrayList<User>();
		DHCS.currentUser = null;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
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
	public StudentRecord getRecord(String userId) throws GetRecordException
	{
		try{
			for(StudentRecord aRecord : studentRecords)
			{
				if(aRecord.getStudent().getId().equals(userId))
				{
					return aRecord;
				}
			}
			throw new GetRecordException();
		}
		catch(GetRecordException e)
		{
			System.out.println("No Record for this Id");
			return null;
		}

		
	}
	public Transaction[] getCharges(String userId, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear)
	{
		return null;
		
	}

}