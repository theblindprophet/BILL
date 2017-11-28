
/**
 * 	Term class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

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

	public int termDifference(Term term) {
		int termDiff = 0;
		String currSemester = this.semester;
		int currYear = this.year;

		if (!term.getSemester().equals("") && !this.semester.equals("")) {
			while (currYear != term.getYear() && !currSemester.equals(term.getSemester())) {

				if (currYear < term.getYear()) { // If term difference is
													// positive
					termDiff++;
					if (currSemester.equals("FALL")) {
						currSemester = "SPRING";
						currYear++;
					} else if (currSemester.equals("SPRING")) {
						currSemester = "FALL";
					}
				} else { // If term difference is negative
					termDiff--;

					if (currSemester.equals("FALL")) {
						currSemester = "SPRING";
					} else if (currSemester.equals("SPRING")) {
						currSemester = "FALL";
						currYear--;
					}
				}
			}
		}

		return termDiff;
	}

	public String toString() {
		return semester + "-" + year;
	}
}
