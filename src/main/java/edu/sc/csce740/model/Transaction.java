/**
 * 	Transaction class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

public class Transaction {

	// Class variables
	private String type = "";
	private int transactionMonth = 0;
	private int transactionDay = 0;
	private int transactionYear = 0;
	private double amount = 0.0;
	private String note = "";
	
	/**
	 * 
	 */
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(String type, int transMonth, int transDay, int transYear, double amount, String note) {
		this.type = type;
		this.transactionMonth = transMonth;
		this.transactionDay = transDay;
		this.transactionYear = transYear;
		this.amount = amount;
		this.note = note;
	}
	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionMonth() {
		return transactionMonth;
	}

	/**
	 * 
	 * @param transactionMonth
	 */
	public void setTransactionMonth(int transactionMonth) {
		this.transactionMonth = transactionMonth;
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionDay() {
		return transactionDay;
	}

	/**
	 * 
	 * @param transactionDay
	 */
	public void setTransactionDay(int transactionDay) {
		this.transactionDay = transactionDay;
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionYear() {
		return transactionYear;
	}

	/**
	 * 
	 * @param transactionYear
	 */
	public void setTransactionYear(int transactionYear) {
		this.transactionYear = transactionYear;
	}

	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}
	public String toString()
	{
		return type + "-" + transactionMonth + "-" + transactionYear + "-" + transactionYear + "-" + amount + "-" + note;
	}

}
