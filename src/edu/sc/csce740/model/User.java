/**
 * 	User class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package edu.sc.csce740.model;

public class User {
	
	// Class variables
	private String id = "";
	private String firstname = "";
	private String lastname = "";
	private String role = "";
	private String college = "";
	private StudentRecord record;
	
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}

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
		return firstname;
	}

	/**
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	/**
	 * 
	 * @param recordString
	 */
	public void parseStudent(String recordString) {
		
	}

}
