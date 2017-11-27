/**
 * 	Course class object
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

public class Course {
	private String name;
	private String id;
	private int numCredits;
	private boolean online;
	
	public Course() {
		this.name = "";
		this.id = "";
		this.numCredits = -1;
		this.online = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public int getNumCredits() {
		return numCredits;
	}

	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}	
	
	public String toString()
	{
		return name + "-" + id + "-" + numCredits + "-" + online;
	}
}
