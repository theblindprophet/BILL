/**
 * 	Term class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package edu.sc.csce740.model;

public class Term {

	// Class variables
	private String semester = "";
	private int year = 0;
	
	/**
	 * 
	 */
	public Term() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * 
	 * @param semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * 
	 * @return
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
