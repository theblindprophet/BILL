package main.java.edu.sc.csce740.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class AVPS 
{

	public static boolean hasPermission(User requestor, User requestee, Action action)
	{
		
		switch(action)
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

	private static boolean hasPermission_LogIn(User requestor) {
		if(requestor == null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_LogOut(User requestor) {
		if(requestor != null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_ApplyPayment(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId())))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_ViewCharges(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId())))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GenerateBill(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId())))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_EditRecord(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetRecord(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && ((requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId())))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetStudentId(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && requestor.getRole().equals("ADMIN") && requestor.getCollege().equals(requestee.getCollege()))
		{
			return true;
		}else{
			return false;
		}
	}
	
	public static String validateRecord(StudentRecord record)
	{
		StudentDemographics aStudent = record.getStudent();
		if(!isNotNull(aStudent.getId())) {
			return "Null Id";
		}
		if(!isNotNull(aStudent.getFirstname())) {
			return "Null firstname";
		}
		if(!isNotNull(aStudent.getLastname())) {
			return "Null lastname";
		}
		if(!isNotNull(aStudent.getId())) {
			return "Null Id";
		}
		String validPhone = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		if(!aStudent.getPhone().matches(validPhone)) {
			return "phone does not match correct pattern";
		}
		if(!isValidEmail(aStudent.getEmailAddress())) {
			return "Not valid email";
		}
		if(!isNotNull(aStudent.getAddressCity())) {
			return "Null address city";
		}
		if(!isValidState(aStudent.getAddressState())) {
			return "Not valid address state";
		}
		String validPostalCode = "^[0-9]{5}(?:-[0-9]{4})?$";
		if(!aStudent.getAddressPostalCode().matches(validPostalCode)) {
			return "postal code does not match correct pattern";
		}
		if(!isNotNull(aStudent.getAddressStreet())) {
			return "Null address street";
		}
		if(!isValidCollege(record.getCollege())) {
			return "Not valid college";
		}
		if(!isValidTerm(record.getTermBegan())) {
			return "Not valid term began";
		}
		if(!isValidStatus(record.getClassStatus())) {
			return "Not valid status";
		}
		if(!isValidInternationalStatus(record.getInternationalStatus())) {
			return "Not valid international status";
		}
		if(!isValidScholarship(record.getScholarship())) {
			return "Not valid scholarship";
		}
		if(!isValidStudyAbroad(record.getStudyAbroad())) {
			return "Not valid study abroad";
		}
		if(!isValidCourses(record.getCourses())) {
			return "Not valid courses";
		}
		if(record.getCapstoneEnrolled() != null && !isValidTerm(record.getCapstoneEnrolled())) {
			return "Not valid capstone enrolled";
		}
		if(record.getTransactions() != null && !isValidTransactions(record.getTransactions())) {
			return "Not valid transactions";
		}
		
		return "";
	}
	
	private static boolean isValidTransactions(Transaction[] transactions) 
	{
		String validYear = "^[1-9][0-9]{4}$";
		String validDay = "^(3[0-1]|2[0-9]|1[0-9]|0[1-9])$";
		String validMonth = "^(1[0-2]|0[1-9])$" ;
		for(int i = 0; i < transactions.length; i++)
		{
			if(!isNotNull(transactions[i].getNote()) || transactions[i].getAmount() < 0.0 ||
			   !(transactions[i].getType().equals("PAYMENT") || !transactions[i].getType().equals("CHARGE")) ||
			   !Integer.toString(transactions[i].getTransactionDay()).matches(validDay) || 
			   !Integer.toString(transactions[i].getTransactionMonth()).matches(validMonth) ||
			   !Integer.toString(transactions[i].getTransactionYear()).matches(validYear))
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean isValidTransaction(Transaction transaction) 
	{
		String validYear = "^[1-9][0-9]{4}$";
		String validDay = "^(3[0-1]|2[0-9]|1[0-9]|0[1-9])$";
		String validMonth = "^(1[0-2]|0[1-9])$" ;

		if(isNotNull(transaction.getNote()) || transaction.getAmount() >= 0.0 ||
		   (transaction.getType().equals("PAYMENT") || transaction.getType().equals("CHARGE")) ||
		   Integer.toString(transaction.getTransactionDay()).matches(validDay) || 
		   Integer.toString(transaction.getTransactionMonth()).matches(validMonth) ||
		   Integer.toString(transaction.getTransactionYear()).matches(validYear))
			return true;
		else
			return false;
	}
	
	private static boolean isValidCourses(Course[] courses) 
	{
		for(int i = 0; i < courses.length; i++)
		{
			if(!isNotNull(courses[i].getID()) || !isNotNull(courses[i].getName()) || courses[i].getNumCredits() < 0)
			{
				return false;
			}
		}
		return true;
	}

	private static boolean isValidStudyAbroad(String studyAbroad) {
		if((studyAbroad.equals("REGULAR") || studyAbroad.equals("COHORT") || 
		   studyAbroad.equals("NONE")) && isNotNull(studyAbroad)))
			return true;
		else
			return false;
	}

	private static boolean isValidScholarship(String scholarship) {
		if((scholarship.equals("WOODROW") || scholarship.equals("DEPARTMENTAL") || 
		   scholarship.equals("GENERAL") || scholarship.equals("ATHLETIC") ||
		   scholarship.equals("SIMS") || scholarship.equals("NONE")) && isNotNull(scholarship))
			return true;
		else
			return false;
	}

	private static boolean isValidInternationalStatus(String internationalStatus) 
	{
		if((internationalStatus.equals("SHORT_TERM") || internationalStatus.equals("SPONSORED") || 
		   internationalStatus.equals("NONE")) && isNotNull(internationalStatus))
			return true;
		else
			return false;
	}
	private static boolean isValidStatus(String classStatus) 
	{
		if((classStatus.equals("FRESHMAN") || classStatus.equals("SOPHMORE") || 
		   classStatus.equals("JUNIOR") || classStatus.equals("SENIOR") ||
		   classStatus.equals("MASTERS") || classStatus.equals("PHD") ||
		   classStatus.equals("GRADUATED")) && isNotNull(classStatus))
			return true;
		else
			return false;
	}

	private static boolean isValidTerm(Term termBegan) 
	{
		String validYear = "^[1-9][0-9]{3}$";
		if((termBegan.getSemester().equals("SPRING") || termBegan.getSemester().equals("SUMMER") || termBegan.getSemester().equals("FALL")) && isNotNull(termBegan.getSemester()))
		{
			
			if(Integer.toString(termBegan.getYear()).matches(validYear))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	private static boolean isValidState(String state) {
		String[] stateList = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL",
			"IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV",
			"NY","OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
		if(isNotNull(state) && Arrays.asList(stateList).contains(state)) {
			return true;
		}
		return false;
	}

	private static boolean isValidCollege(String college) 
	{
		if((college.equals("ARTS_AND_SCIENCES") || college.equals("ENGINEERING_AND_COMPUTING") || college.equals("GRADUATE_SCHOOL")) && isNotNull(college))
			return true;
		else
			return false;
	} 

	private static boolean isNotNull(String aString)
	{
		return aString != null && !aString.isEmpty();
	}
	
	private static boolean isValidEmail(String aString)
	{
		return aString != null && !aString.isEmpty() && aString.contains("@");
	}
}