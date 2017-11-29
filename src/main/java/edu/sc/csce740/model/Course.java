/**
 * 	Course class object
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

/*
 * Course Class: used to represent the course data type
 */
public class Course {
	private String name;
	private String id;
	private int numCredits;
	private boolean online;

	/*
	 * Default Constructor
	 */
	public Course() {
		this.name = "";
		this.id = "";
		this.numCredits = -1;
		this.online = false;
	}

	/*************** GETTERS AND SETTERS ****************/

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the numCredits
	 */
	public int getNumCredits() {
		return numCredits;
	}

	/**
	 * @param numCredits
	 *            the numCredits to set
	 */
	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}

	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online
	 *            the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * toString function
	 * 
	 * @return String: to String of course
	 */
	public String toString() {
		return name + "-" + id + "-" + numCredits + "-" + online;
	}
}
