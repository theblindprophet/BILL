/**
 * 	Course class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

public class Course {
	private String name;
	private String ID;
	private int numHours;
	private boolean online;
	
	public Course() {
		this.name = "";
		this.ID = "";
		this.numHours = -1;
		this.online = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getNumHours() {
		return numHours;
	}

	public void setNumHours(int numHours) {
		this.numHours = numHours;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}	
}
