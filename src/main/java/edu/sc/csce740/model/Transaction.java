/**
 * 	Transaction class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

/*
 * Transaction Class: used for representing the transaction data type
 */
public class Transaction {

	// Class variables
	private String type = "";
	private TransactionDate transactionDate;
	private double amount = 0.0;
	private String note = "";

	/**
	 * Default Constructor
	 */
	public Transaction() {
		// TODO Auto-generated constructor stub
		transactionDate = new TransactionDate();
	}

	/**
	 * Constructor
	 * 
	 * @param type:
	 *            String for type of transaction
	 * @param transMonth:
	 *            integer for the month of transaction
	 * @param transDay:
	 *            integer for the day of the transaction
	 * @param transYear:
	 *            integer for the year of the transaction
	 * @param amount:
	 *            double for the amount of the transaction
	 * @param note:
	 *            String for the description of the transaction
	 */
	public Transaction(String type, int transMonth, int transDay, int transYear, double amount, String note) {
		this();
		this.type = type;
		this.transactionDate.setMonth(transMonth);
		this.transactionDate.setDay(transDay);
		this.transactionDate.setYear(transYear);
		this.amount = amount;
		this.note = note;
	}

	/*************** GETTERS AND SETTERS ****************/

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
		return this.transactionDate.getMonth();
	}

	/**
	 * 
	 * @param transactionMonth
	 */
	public void setTransactionMonth(int transactionMonth) {
		this.transactionDate.setMonth(transactionMonth);
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionDay() {
		return this.transactionDate.getDay();
	}

	/**
	 * 
	 * @param transactionDay
	 */
	public void setTransactionDay(int transactionDay) {
		this.transactionDate.setDay(transactionDay);
	}

	/**
	 * 
	 * @return
	 */
	public int getTransactionYear() {
		return this.transactionDate.getYear();
	}

	/**
	 * 
	 * @param transactionYear
	 */
	public void setTransactionYear(int transactionYear) {
		this.transactionDate.setYear(transactionYear);
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

	/*
	 * TransactionDate class: used for representing Date data type
	 */
	public class TransactionDate {
		// class variables
		private int month = 0;
		private int day = 0;
		private int year = 0;

		/*************** GETTERS AND SETTERS ****************/

		/**
		 * 
		 * @retur day
		 */
		public int getDay() {
			return day;
		}

		/**
		 * 
		 * @param day
		 */
		public void setDay(int day) {
			this.day = day;
		}

		/**
		 * 
		 * @return month
		 */
		public int getMonth() {
			return month;
		}

		/**
		 * 
		 * @param month
		 */
		public void setMonth(int month) {
			this.month = month;
		}

		/**
		 * 
		 * @retur year
		 */
		public int getYear() {
			return year;
		}

		/**
		 * 
		 * @param year
		 */
		public void setYear(int year) {
			this.year = year;
		}
	}
}
