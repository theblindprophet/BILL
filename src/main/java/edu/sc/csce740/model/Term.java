
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
			while (currYear != term.getYear()) {

				if (currYear < term.getYear()) { // Pos difference
					termDiff+=3;
					currYear++;
				} else { // If term difference is negative
					termDiff-=3;
					currYear--;
				}
			}
			if (currSemester.equals("FALL") && term.getSemester().equals("SPRING")) {
				termDiff-=2;
			} else if (currSemester.equals("FALL") && term.getSemester().equals("SUMMER")) {
				termDiff-=1;
			} else if (currSemester.equals("SPRING") && term.getSemester().equals("SUMMER")) {
				termDiff+=1;
			} else if (currSemester.equals("SPRING") && term.getSemester().equals("FALL")) {
				termDiff+=2;
			} else if (currSemester.equals("SUMMER") && term.getSemester().equals("FALL")) {
				termDiff+=1;
			} else if (currSemester.equals("SUMMER") && term.getSemester().equals("SPRING")) {
				termDiff-=1;
			}
		}

		return termDiff;
	}

	public String toString() {
		return semester + "-" + year;
	}
}
