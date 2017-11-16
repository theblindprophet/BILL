/**
 * 	Bill class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package edu.sc.csce740.model;

public class Bill {
	
	// Class variables
	private StudentDemographics student;
	private String college = "";
	private String classStatus = "";
	private Double balance = 0.0;
	private Transaction[] transactions;

	/**
	 * 
	 */
	public Bill() {
		// TODO Auto-generated constructor stub
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
	public Double getBalance() {
		return balance;
	}
	
	/**
	 * 
	 * @param balance
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
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
	
	/**
	 * 
	 * @param startMonth
	 * @param startYear
	 * @param endMonth
	 * @param endYear
	 * @return
	 */
	public Transaction[] getPaymentHistory(int startMonth, int startYear, 
										  int endMonth, int endYear) {
		return this.transactions;
	}
	
	/**
	 * 
	 * @param amount
	 * @param note
	 */
	public void applyPayment(double amount, String note) {
		
	}

}
