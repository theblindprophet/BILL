package main.java.edu.sc.csce740.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DHCS {

	private ArrayList<StudentRecord> studentRecords;
	private ArrayList<User> users;
	private User currentUser;
	private String recordsFile;
	
	public String getRecordsFile() {
		return recordsFile;
	}

	public void setRecordsFile(String recordsFile) {
		this.recordsFile = recordsFile;
	}

	public DHCS()
	{
		this.studentRecords = new ArrayList<StudentRecord>();
		this.users = new ArrayList<User>();
		this.currentUser = null;
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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getUser(String userId) throws InvalidUserIdException
	{
		try
		{
			for(User aUser : this.users)
			{
				if(aUser.getId().equals(userId))
				{
					return aUser;
				}
			}
			throw new InvalidUserIdException();
		}
		catch(InvalidUserIdException e)
		{
			System.out.println("Invalid User Id");
			return null;
		}
	}
	
	private void initialize()
	{
		
	}
	
	private void updateInternalRecord(String userId, StudentRecord record)
	{
		try
		{
			for(StudentRecord aRecord : this.studentRecords)
			{
				if(aRecord.getStudent().getId().equals(userId))
				{
					aRecord = record;
				}
			}
			throw new InvalidUserIdException();
		}
		catch(InvalidUserIdException e)
		{
			System.out.println("Invalid User Id");
		}
	}
	private String readFile(String fileName) throws Exception
	{
		return fileName;
		
	}
	
	private void writeFile(String fileName, String data) throws Exception
	{
		
	}
	
	public void writeRecord(String userId, StudentRecord record, Boolean permanent) throws Exception
	{
		boolean isValidRecord = AVPS.validateRecord(record);
		if(isValidRecord)
		{
			if(permanent)
			{
				this.writeFile(this.recordsFile, record.toString());
				this.updateInternalRecord(userId, record);
				
			}else{
				this.updateInternalRecord(userId, record);
			}
		}
	}
	
	public StudentRecord getRecord(String userId) throws GetRecordException
	{
		try
		{
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
	public Transaction[] getCharges(String userId, int startMonth, int startDay, int startYear, int endMonth, int endDay, int endYear) throws ParseException, GetRecordException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = sdf.parse(endYear + "-" + endMonth + "-" + endDay);
		Date startDate = sdf.parse(startYear + "-" + startMonth + "-" + startDay);
		Transaction[] validTransArray = new Transaction[0];
		
		Transaction[] transList = this.getRecord(userId).getTransactions();
		ArrayList<Transaction> validTrans = new ArrayList<Transaction>();
		for(int i = 0; i < transList.length; i++)
		{
			Date transDate = sdf.parse(transList[i].getTransactionYear() + "-" + transList[i].getTransactionMonth() 
										+ "-" + transList[i].getTransactionDay());
			
			if(transDate.compareTo(endDate) < 0 && transDate.compareTo(startDate) > 0)
			{
				validTrans.add(transList[i]);
			}
		}		
		validTransArray = validTrans.toArray(validTransArray);
		return validTransArray;
		
	}
	
	

}