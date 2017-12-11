/**
 * 	Bill class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/*
 * Bill Class: class for representing the Bill data type
 */
public class Bill {

	// Class variables
	private StudentDemographics student;
	private String college = "";
	private String classStatus = "";
	private Transaction[] transactions;
	private double balance = 0.0;

	/*
	 * Constructor
	 * 
	 * @param student: StudentDemographic that contains information about the
	 * student user
	 * 
	 * @param college: String for the college of the user
	 * 
	 * @param classStatus: String for the class status of the user
	 * 
	 * @param transactions: transaction array containing all of the student
	 * users transactions
	 */
	public Bill(StudentDemographics student, String college, String classStatus, Transaction[] transactions) {
		// TODO Auto-generated constructor stub
		this.student = student;
		this.college = college;
		this.classStatus = classStatus;
		this.transactions = transactions;
		this.balance = Billing.calculateBalance(transactions);
	}

	/*************** GETTERS AND SETTERS ****************/

	/**
	 * 
	 * @return
	 */
	public double getBalance() {
		return Billing.calculateBalance(this.transactions);
	}

	/**
	 * 
	 * @return
	 */
	public StudentDemographics getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 */
	public void setStudent(StudentDemographics student) {
		this.student = student;
	}

	/**
	 * 
	 * @return
	 */
	public String getCollege() {
		return college;
	}

	/**
	 * 
	 * @param college
	 */
	public void setCollege(String college) {
		this.college = college;
	}

	/**
	 * 
	 * @return
	 */
	public String getClassStatus() {
		return classStatus;
	}

	/**
	 * 
	 * @param classStatus
	 */
	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

	/**
	 * 
	 * @return
	 */
	public Transaction[] getTransactions() {
		return transactions;
	}

	/**
	 * 
	 * @param transactions
	 */
	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
		this.balance = Billing.calculateBalance(transactions);
	}
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(gson.toJson(this));
		return gson.toJson(je);
	}
}
