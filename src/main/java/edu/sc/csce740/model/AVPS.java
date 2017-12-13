package main.java.edu.sc.csce740.model;

import java.util.Arrays;

public class AVPS {
	/**
	 * Function to delegate permissions for certain actions
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for an action
	 * @param requestee:
	 *            the user of whom an action will take place for
	 * @param action:
	 *            the action that permissions are being requested for
	 * @returns: boolean stating if permission is granted for an action
	 */
	public static boolean hasPermission(User requestor, User requestee, Action action) {

		switch (action)// based on which action is selected.
		{
		case LogIn:
			return hasPermission_LogIn(requestor);
		case LogOut:
			return hasPermission_LogOut(requestor);
		case GetStudentIds:
			return hasPermission_GetStudentId(requestor, requestee);
		case GetRecord:
			return hasPermission_GetRecord(requestor, requestee);
		case EditRecord:
			return hasPermission_EditRecord(requestor, requestee);
		case GenerateBill:
			return hasPermission_GenerateBill(requestor, requestee);
		case ViewCharges:
			return hasPermission_ViewCharges(requestor, requestee);
		case ApplyPayment:
			return hasPermission_ApplyPayment(requestor, requestee);
		default:
			return false;
		}

	}

	/**
	 * Function to delegate permissions for logging in
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for logging in
	 * @returns: boolean stating if permission is granted for logging in
	 */
	private static boolean hasPermission_LogIn(User requestor) {
		if (requestor == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for logging out
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for logging out
	 * @returns: boolean stating if permission is granted for logging out
	 */
	private static boolean hasPermission_LogOut(User requestor) {
		if (requestor != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for applying payments
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for apply payment
	 * @param requestee:
	 *            the user of whom an action will take place for apply payment
	 * @returns: boolean stating if permission is granted for apply payment
	 */
	private static boolean hasPermission_ApplyPayment(User requestor, User requestee) {
		if (requestor != null && requestee != null
				&& ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
						|| requestor.getId().equals(requestee.getId()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for view charges
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for view charges
	 * @param requestee:
	 *            the user of whom an action will take place for view charges
	 * @returns: boolean stating if permission is granted for view charges
	 */
	private static boolean hasPermission_ViewCharges(User requestor, User requestee) {
		if (requestor != null && requestee != null
				&& ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
						|| requestor.getId().equals(requestee.getId()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for generating a bill
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for generating a bill
	 * @param requestee:
	 *            the user of whom an action will take place for generating a
	 *            bill
	 * @returns: boolean stating if permission is granted for generating a bill
	 */
	private static boolean hasPermission_GenerateBill(User requestor, User requestee) {
		if (requestor != null && requestee != null
				&& ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
						|| requestor.getId().equals(requestee.getId()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for editing a record
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for editing a record
	 * @param requestee:
	 *            the user of whom an action will take place for editing a
	 *            record
	 * @returns: boolean stating if permission is granted for editing a record
	 */
	private static boolean hasPermission_EditRecord(User requestor, User requestee) {
		if (requestor != null && requestee != null && requestor.getCollege().equals(requestee.getCollege())
				&& requestor.getRole().equals("ADMIN")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for getting a record
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for getting a record
	 * @param requestee:
	 *            the user of whom an action will take place for getting a
	 *            record
	 * @returns: boolean stating if permission is granted for getting a record
	 */
	private static boolean hasPermission_GetRecord(User requestor, User requestee) {
		if (requestor != null && requestee != null
				&& ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
						|| requestor.getId().equals(requestee.getId()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to delegate permissions for getting a student ID
	 * 
	 * @param requestor:
	 *            the user which is requesting permissions for getting a student
	 *            ID
	 * @param requestee:
	 *            the user of whom an action will take place for getting a
	 *            student ID
	 * @returns: boolean stating if permission is granted for getting a student
	 *           ID
	 */
	private static boolean hasPermission_GetStudentId(User requestor, User requestee) {
		if (requestor != null && requestee != null && requestor.getRole().equals("ADMIN")
				&& requestor.getCollege().equals(requestee.getCollege())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to determine if a student record is valid
	 * 
	 * @param record:
	 *            a student record
	 * @returns: String that states what is invalid about the record. A blank
	 *           string means the record is valid.
	 */
	public static String validateRecord(StudentRecord record) {
		StudentDemographics aStudent = record.getStudent();
		if (!isNotNull(aStudent.getId())) {
			return "Null Id";
		}
		if (!isNotNull(aStudent.getFirstname())) {
			return "Null firstname";
		}
		if (!isNotNull(aStudent.getLastname())) {
			return "Null lastname";
		}
		if (!isNotNull(aStudent.getId())) {
			return "Null Id";
		}
		String validPhone = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		if (!aStudent.getPhone().matches(validPhone)) {
			return "phone does not match correct pattern";
		}
		if (!isValidEmail(aStudent.getEmailAddress())) {
			return "Not valid email";
		}
		if (!isNotNull(aStudent.getAddressCity())) {
			return "Null address city";
		}
		if (!isValidState(aStudent.getAddressState())) {
			return "Not valid address state";
		}
		String validPostalCode = "^[0-9]{5}(?:-[0-9]{4})?$";
		if (!aStudent.getAddressPostalCode().matches(validPostalCode)) {
			return "postal code does not match correct pattern";
		}
		if (!isNotNull(aStudent.getAddressStreet())) {
			return "Null address street";
		}
		if (!isValidCollege(record.getCollege())) {
			return "Not valid college";
		}
		if (!isValidTerm(record.getTermBegan())) {
			return "Not valid term began";
		}
		if (!isValidStatus(record.getClassStatus())) {
			return "Not valid status";
		}
		if (!isValidInternationalStatus(record.getInternationalStatus())) {
			return "Not valid international status";
		}
		if (!isValidScholarship(record.getScholarship())) {
			return "Not valid scholarship";
		}
		if (!isValidStudyAbroad(record.getStudyAbroad())) {
			return "Not valid study abroad";
		}
		if (!isValidCourses(record.getCourses())) {
			return "Not valid courses";
		}
		if (record.getCapstoneEnrolled() != null && !isValidTerm(record.getCapstoneEnrolled())) {
			return "Not valid capstone enrolled";
		}
		if (record.getTransactions() != null && !isValidTransactions(record.getTransactions())) {
			return "Not valid transactions";
		}

		return "";
	}

	/**
	 * Function to determine if an array of transactions are valid
	 * 
	 * @param transactions:
	 *            array of transactions to validate
	 * @returns: boolean that states if the transactions are valid
	 */
	private static boolean isValidTransactions(Transaction[] transactions) {

		for (int i = 0; i < transactions.length; i++) {
			if (!isValidTransaction(transactions[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function to determine if a transaction is valid
	 * 
	 * @param transaction:
	 *            transaction to validate
	 * @returns: boolean that states if the transaction is valid
	 */
	public static boolean isValidTransaction(Transaction transaction) {
		// regex for determining the format of the date
		String validYear = "^[1-9][0-9]{3}$";
		String validDay = "^(3[0-1]|2[0-9]|1[0-9]|0[1-9])$";
		String validMonth = "^(1[0-2]|0[1-9])$";

		if (isNotNull(transaction.getNote()) && transaction.getAmount() >= 0.0
				&& (transaction.getType().equals("PAYMENT") || transaction.getType().equals("CHARGE"))
				&& Integer.toString(transaction.getTransactionDay()).matches(validDay)
				&& Integer.toString(transaction.getTransactionMonth()).matches(validMonth)
				&& Integer.toString(transaction.getTransactionYear()).matches(validYear)) {
			return true;
		} else {
			System.out.println(transaction.getTransactionYear());
			return false;
		}
	}

	/**
	 * Function to determine if an array of courses are valid
	 * 
	 * @param courses:
	 *            array of courses to validate
	 * @returns: boolean that states if the courses are valid
	 */
	private static boolean isValidCourses(Course[] courses) {
		for (int i = 0; i < courses.length; i++) {
			if (!isNotNull(courses[i].getId()) || !isNotNull(courses[i].getName()) || courses[i].getNumCredits() < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function to determine if studyAbroad is valid
	 * 
	 * @param String:
	 *            studyAbroad to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidStudyAbroad(String studyAbroad) {
		if ((studyAbroad.equals("REGULAR") || studyAbroad.equals("COHORT") || studyAbroad.equals("NONE"))
				&& isNotNull(studyAbroad))
			return true;
		else
			return false;
	}

	/**
	 * Function to determine if Scholarship is valid
	 * 
	 * @param String:
	 *            scholarship to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidScholarship(String scholarship) {
		if ((scholarship.equals("WOODROW") || scholarship.equals("DEPARTMENTAL") || scholarship.equals("GENERAL")
				|| scholarship.equals("ATHLETIC") || scholarship.equals("SIMS") || scholarship.equals("NONE"))
				&& isNotNull(scholarship))
			return true;
		else
			return false;
	}

	/**
	 * Function to determine if international status is valid
	 * 
	 * @param String:
	 *            international status to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidInternationalStatus(String internationalStatus) {
		if ((internationalStatus.equals("SHORT_TERM") || internationalStatus.equals("SPONSORED")
				|| internationalStatus.equals("NONE")) && isNotNull(internationalStatus))
			return true;
		else
			return false;
	}

	/**
	 * Function to determine if class status is valid
	 * 
	 * @param String:
	 *            class status to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidStatus(String classStatus) {
		if ((classStatus.equals("FRESHMAN") || classStatus.equals("SOPHOMORE") || classStatus.equals("JUNIOR")
				|| classStatus.equals("SENIOR") || classStatus.equals("MASTERS") || classStatus.equals("PHD")
				|| classStatus.equals("GRADUATED")) && isNotNull(classStatus))
			return true;
		else
			return false;
	}

	/**
	 * Function to determine if term is valid
	 * 
	 * @param termBegan:
	 *            term to validate
	 * @returns: boolean that states if the term is valid
	 */
	private static boolean isValidTerm(Term termBegan) {
		String validYear = "^[1-9][0-9]{3}$";
		if ((termBegan.getSemester().equals("SPRING") || termBegan.getSemester().equals("SUMMER")
				|| termBegan.getSemester().equals("FALL")) && isNotNull(termBegan.getSemester())) {

			if (Integer.toString(termBegan.getYear()).matches(validYear))
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * Function to determine if state is valid
	 * 
	 * @param String:
	 *            state to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidState(String state) {
		String[] stateList = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID",
				"IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE",
				"NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT",
				"VA", "VI", "VT", "WA", "WI", "WV", "WY" };
		if (isNotNull(state) && Arrays.asList(stateList).contains(state)) {
			return true;
		}
		return false;
	}

	/**
	 * Function to determine if college is valid
	 * 
	 * @param String:
	 *            college to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidCollege(String college) {
		if ((college.equals("ARTS_AND_SCIENCES") || college.equals("ENGINEERING_AND_COMPUTING")
				|| college.equals("GRADUATE_SCHOOL")) && isNotNull(college))
			return true;
		else
			return false;
	}

	/**
	 * Function to determine if string is not null
	 * 
	 * @param aString:string
	 *            to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isNotNull(String aString) {
		return aString != null && !aString.isEmpty();
	}

	/**
	 * Function to determine if an email is valid
	 * 
	 * @param String:
	 *            aString to validate
	 * @returns: boolean that states if the string is valid
	 */
	private static boolean isValidEmail(String aString) {
		return aString != null && !aString.isEmpty() && aString.contains("@");
	}
}