/**
 * 	Bill class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;
import com.google.gson.Gson;

public class Bill {
	
	// Class variables
	private StudentDemographics student;
	private String college = "";
	private String classStatus = "";
	private Transaction[] transactions;
	
	public Bill(StudentDemographics student, String college, String classStatus, Transaction[] transactions){
		// TODO Auto-generated constructor stub
		this.student = student;
		this.college = college;
		this.classStatus = classStatus;
		this.transactions = transactions;
	}
	
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
	}


}
