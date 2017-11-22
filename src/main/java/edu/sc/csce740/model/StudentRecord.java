/**
 * 	Student record class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

public class StudentRecord {
	
	// Class variables
	private StudentDemographics student;
	private String college = "";
	private Term termBegan;
	private String classStatus = "";
	private boolean gradAssistant = false;
	private boolean international = false;
	private String internationalStatus = "";
	private boolean resident = false;
	private boolean activeDuty = false;
	private boolean veteran = false;
	private boolean freeTuition = false;
	private String scholarshop = "";
	private String studyAbroad = "";
	private boolean nationalStudentExchange = false;
	private boolean outsideInsurance = false;
	private Course[] courses;
	private Transaction[] transactions;

	/**
	 * 
	 */
	public StudentRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public StudentDemographics getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 */
	public void setStudent(StudentDemographics student) {
		this.student = student;
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
	public Term getTermBegan() {
		return termBegan;
	}

	/**
	 * 
	 * @param termBegan
	 */
	public void setTermBegan(Term termBegan) {
		this.termBegan = termBegan;
	}

	/**
	 * 
	 * @return
	 */
	public String getClassStatus() {
		return classStatus;
	}

	/**
	 * 
	 * @param classStatus
	 */
	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isGradAssistant() {
		return gradAssistant;
	}

	/**
	 * 
	 * @param gradAssistant
	 */
	public void setGradAssistant(boolean gradAssistant) {
		this.gradAssistant = gradAssistant;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isInternational() {
		return international;
	}

	/**
	 * 
	 * @param international
	 */
	public void setInternational(boolean international) {
		this.international = international;
	}

	/**
	 * 
	 * @return
	 */
	public String getInternationalStatus() {
		return internationalStatus;
	}

	/**
	 * 
	 * @param internationalStatus
	 */
	public void setInternationalStatus(String internationalStatus) {
		this.internationalStatus = internationalStatus;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isResident() {
		return resident;
	}

	/**
	 * 
	 * @param resident
	 */
	public void setResident(boolean resident) {
		this.resident = resident;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActiveDuty() {
		return activeDuty;
	}

	/**
	 * 
	 * @param activeDuty
	 */
	public void setActiveDuty(boolean activeDuty) {
		this.activeDuty = activeDuty;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVeteran() {
		return veteran;
	}

	/**
	 * 
	 * @param veteran
	 */
	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFreeTuition() {
		return freeTuition;
	}

	/**
	 * 
	 * @param freeTuition
	 */
	public void setFreeTuition(boolean freeTuition) {
		this.freeTuition = freeTuition;
	}

	/**
	 * 
	 * @return
	 */
	public String getScholarshop() {
		return scholarshop;
	}

	/**
	 * 
	 * @param scholarshop
	 */
	public void setScholarshop(String scholarshop) {
		this.scholarshop = scholarshop;
	}

	/**
	 * 
	 * @return
	 */
	public String getStudyAbroad() {
		return studyAbroad;
	}

	/**
	 * 
	 * @param studyAbroad
	 */
	public void setStudyAbroad(String studyAbroad) {
		this.studyAbroad = studyAbroad;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNationalStudentExchange() {
		return nationalStudentExchange;
	}

	/**
	 * 
	 * @param nationalStudentExchange
	 */
	public void setNationalStudentExchange(boolean nationalStudentExchange) {
		this.nationalStudentExchange = nationalStudentExchange;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOutsideInsurance() {
		return outsideInsurance;
	}

	/**
	 * 
	 * @param outsideInsurance
	 */
	public void setOutsideInsurance(boolean outsideInsurance) {
		this.outsideInsurance = outsideInsurance;
	}

	/**
	 * 
	 * @return
	 */
	public Course[] getCourses() {
		return courses;
	}

	/**
	 * 
	 * @param courses
	 */
	public void setCourses(Course[] courses) {
		this.courses = courses;
	}

	/**
	 * 
	 * @return
	 */
	public Transaction[] getTransactions() {
		return transactions;
	}

	/**
	 * 
	 * @param transactions
	 */
	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * 
	 * @param startMonth
	 * @param startDay
	 * @param startYear
	 * @param endMonth
	 * @param endDay
	 * @param endYear
	 * @return
	 */
	public Transaction[] getTransactionPeriod(int startMonth, int startDay, int startYear,
											 int endMonth, int endDay, int endYear) {
		return this.transactions;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "apples";
	}

}
