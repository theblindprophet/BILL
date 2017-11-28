package main.java.edu.sc.csce740.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import main.java.edu.sc.csce740.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import main.java.edu.sc.csce740.model.EditRecordException;

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
		
		for (User user : this.users) {
			for (StudentRecord sr : this.studentRecords) {
				if (user.getId().equals(sr.getStudent().getId())) {
					user.setRecord(sr);
				}
			}
		}
	}

	public ArrayList<StudentRecord> getStudentRecords() {
		return studentRecords;
	}

	public void setStudentRecords(ArrayList<StudentRecord> studentRecords) {
		this.studentRecords = studentRecords;
		
		for (User user : this.users) {
			for (StudentRecord sr : this.studentRecords) {
				if (user.getId().equals(sr.getStudent().getId())) {
					user.setRecord(sr);
				}
			}
		}
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {		
		this.currentUser = currentUser;
		
		for (StudentRecord sr : this.studentRecords) {
			if (sr.getStudent().getId().equals(this.currentUser.getId())) {
				this.currentUser.setRecord(sr);
			}
		}
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
			boolean found = false;
			for(int i=0; i<this.studentRecords.size(); i++) {
				if(this.studentRecords.get(i).getStudent().getId().equals(userId))
				{
					found = true;
					this.studentRecords.set(i, record);
				}
			}
			if(!found) {
				throw new InvalidUserIdException();
			}
		}
		catch(InvalidUserIdException e)
		{
			System.out.println("Invalid User Id to update record");
		}
		System.out.println(studentRecords.get(1).getStudent().getFirstname());
	}
	private String readFile(String fileName) throws Exception
	{
		return fileName;
		
	}
	
	private void writeFile(String fileName) throws Exception
	{
		Gson gson = new Gson();
		String json = gson.toJson(this.studentRecords);
		System.out.println(json);
		try {
			File file = new File(fileName);
			if (file.exists())
			{
			   file.delete();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
	
	public void writeRecord(String userId, StudentRecord record, Boolean permanent) throws EditRecordException, Exception
	{
		String isValidRecordError = AVPS.validateRecord(record);
		if(isValidRecordError == "")
		{
			if(permanent)
			{
				this.updateInternalRecord(userId, record);
				this.writeFile(this.recordsFile);
			}else{
				this.updateInternalRecord(userId, record);
			}
		} else {
			throw new EditRecordException(isValidRecordError);
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