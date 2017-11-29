package main.java.edu.sc.csce740.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import main.java.edu.sc.csce740.model.User;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import main.java.edu.sc.csce740.model.EditRecordException;

/*
 * Data Housing/Controller SubSystem Class: 
 * This class is used as the data housing for BILL. All data collected from text files is stored here. 
 * This class also handles the data manipulation and access for Bill.  
 */
public class DHCS {
	// class variables
	private ArrayList<StudentRecord> studentRecords;
	private ArrayList<User> users;
	private User currentUser;
	private String recordsFile;

	/*
	 * Default Constructor
	 */
	public DHCS() {
		this.studentRecords = new ArrayList<StudentRecord>();
		this.users = new ArrayList<User>();
		this.currentUser = null;
	}

	/*************** GETTERS AND SETTERS ****************/

	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * 
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;

		for (User user : this.users) {// link the user with the student record
			for (StudentRecord sr : this.studentRecords) {
				if (user.getId().equals(sr.getStudent().getId())) {
					user.setRecord(sr);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getRecordsFile() {
		return recordsFile;
	}

	/**
	 * 
	 * @param recordsFile
	 */
	public void setRecordsFile(String recordsFile) {
		this.recordsFile = recordsFile;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<StudentRecord> getStudentRecords() {
		return studentRecords;
	}

	/**
	 * 
	 * @param studentRecords
	 */
	public void setStudentRecords(ArrayList<StudentRecord> studentRecords) {
		this.studentRecords = studentRecords;

		for (User user : this.users) {// link the user with the student record
			for (StudentRecord sr : this.studentRecords) {
				if (user.getId().equals(sr.getStudent().getId())) {
					user.setRecord(sr);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * 
	 * @param setCurrentUser
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;

		for (StudentRecord sr : this.studentRecords) {// link the user with the
														// student record
			if (sr.getStudent().getId().equals(this.currentUser.getId())) {
				this.currentUser.setRecord(sr);
			}
		}
	}

	/**
	 * Function for getting a user from the list of users stored in DHCS
	 * 
	 * @param userId:
	 *            the string containing a userId
	 * @returns user: the user associated with the studentId
	 * @throws exception
	 *             is the user id is invalid
	 */
	public User getUser(String userId) throws InvalidUserIdException {
		try {
			for (User aUser : this.users) {
				if (aUser.getId().equals(userId)) {
					return aUser;
				}
			}
			throw new InvalidUserIdException();
		} catch (InvalidUserIdException e) {
			System.out.println("Invalid User Id: " + userId);
			return null;
		}
	}

	/**
	 * Function for updating the record of a student user. If the permanent flag
	 * is set, it will write the new records back to the records file
	 * 
	 * @param userId:
	 *            the string containing a userId
	 * @param record:
	 *            a Student Record
	 * @param permanet:
	 *            boolean to declare if the change is permanent
	 * @throws exception
	 *             if the record is invalid
	 */
	public void writeRecord(String userId, StudentRecord record, Boolean permanent)
			throws EditRecordException, Exception {
		String isValidRecordError = AVPS.validateRecord(record);
		if (isValidRecordError.equals("")) {
			if (permanent) {
				this.updateInternalRecord(userId, record);
				this.writeFile(this.recordsFile);
			} else {
				this.updateInternalRecord(userId, record);
			}
		} else {
			throw new EditRecordException(isValidRecordError);
		}
	}

	/**
	 * Function for getting the record of a student
	 * 
	 * @param userId:
	 *            the string containing a userId
	 * @return studentRecord associated with the userId
	 * @throws exception
	 *             if the record does not exist for the userId
	 */
	public StudentRecord getRecord(String userId) throws GetRecordException {
		try {
			for (StudentRecord aRecord : studentRecords) {
				if (aRecord.getStudent().getId().equals(userId)) {
					return aRecord;
				}
			}
			throw new GetRecordException();
		} catch (GetRecordException e) {
			System.out.println("No Record for " + userId);
			return null;
		}
	}

	/**
	 * Function for getting the transactions for a given user
	 * 
	 * @param startMonth
	 *            the month of the start date.
	 * @param startDay
	 *            the day of the start date.
	 * @param startYear
	 *            the year of the start date.
	 * @param endMonth
	 *            the month of the end date.
	 * @param endDay
	 *            the day of the end date.
	 * @param endYear
	 *            the year of the end date.
	 * @throws exception
	 *             if the record for that user doesn't not exist or if the date
	 *             is invalid
	 */
	public Transaction[] getCharges(String userId, int startMonth, int startDay, int startYear, int endMonth,
			int endDay, int endYear) throws ParseException, GetRecordException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = sdf.parse(endYear + "-" + endMonth + "-" + endDay);
		Date startDate = sdf.parse(startYear + "-" + startMonth + "-" + startDay);
		Transaction[] validTransArray = new Transaction[0];

		Transaction[] transList = this.getRecord(userId).getTransactions();
		ArrayList<Transaction> validTrans = new ArrayList<Transaction>();
		for (int i = 0; i < transList.length; i++) {
			Date transDate = sdf.parse(transList[i].getTransactionYear() + "-" + transList[i].getTransactionMonth()
					+ "-" + transList[i].getTransactionDay());

			if (transDate.compareTo(endDate) < 0 && transDate.compareTo(startDate) > 0) {
				validTrans.add(transList[i]);
			}
		}
		validTransArray = validTrans.toArray(validTransArray);
		return validTransArray;
	}

	/**
	 * Function for updating the internal record of a student user
	 * 
	 * @param userId:
	 *            the string containing a userId
	 * @param record:
	 *            a Student Record
	 */
	private void updateInternalRecord(String userId, StudentRecord record) {
		try {
			boolean found = false;
			for (int i = 0; i < this.studentRecords.size(); i++) {
				if (this.studentRecords.get(i).getStudent().getId().equals(userId)) {
					found = true;
					this.studentRecords.set(i, record);
				}
			}
			if (!found) {
				throw new InvalidUserIdException();
			}
		} catch (InvalidUserIdException e) {
			System.out.println("Invalid User Id (" + userId + ") to update record");
		}
	}

	/**
	 * Function for updating a file with the new student records list
	 * 
	 * @param fileName:
	 *            name of the file to write the records too
	 * @throws exception
	 *             if the file cannot be written too
	 */
	private void writeFile(String fileName) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(this.studentRecords);
		try {
			File file = new File(fileName);// make sure the file exists
			if (file.exists()) {
				file.delete();// delete the old records
			}
			FileWriter writer = new FileWriter(file);
			writer.write(json);// add the new records as json text
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}