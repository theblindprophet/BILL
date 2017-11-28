/**
 * 	User class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

/*
 * User Class: used for representing the user data type 
 */
public class User {

	// Class variables
	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String role = "";
	private String college = "";
	private StudentRecord record = null;

	/**
	 * Default Constructor
	 */
	public User() {
		this.record = new StudentRecord();
	}

	/*************** GETTERS AND SETTERS ****************/

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstname() {
		return firstName;
	}

	/**
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastname() {
		return lastName;
	}

	/**
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * 
	 * @return
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
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
	public StudentRecord getRecord() {
		return record;
	}

	/**
	 * 
	 * @param record
	 */
	public void setRecord(StudentRecord record) {
		this.record = record;
	}

	/**
	 * 
	 * @return
	 */
	public String getUser() {
		return "apples";
	}

}
