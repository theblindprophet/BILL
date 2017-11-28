/**
 * 	Student demographics class object
 * 	Authors: Jamie Gross
 *  Last Modified: 16 November 2017
 */

package main.java.edu.sc.csce740.model;

/**
 * StudentDemographics class: class for representing the student demographics
 * data type
 */
public class StudentDemographics {

	// Class variables
	private String id = "";
	private String firstName = "";
	private String lastName = "";
	private String phone = "";
	private String emailAddress = "";
	private String addressStreet = "";
	private String addressCity = "";
	private String addressState = "";
	private String addressPostalCode = "";

	/**
	 * Default Constructor
	 */
	public StudentDemographics() {
		// TODO Auto-generated constructor stub
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
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * 
	 * @param addressStreet
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddressCity() {
		return addressCity;
	}

	/**
	 * 
	 * @param addressCity
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddressState() {
		return addressState;
	}

	/**
	 * 
	 * @param addressState
	 */
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddressPostalCode() {
		return addressPostalCode;
	}

	/**
	 * 
	 * @param addressPostalCode
	 */
	public void setAddressPostalCode(String addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

}
