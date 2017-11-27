package main.java.edu.sc.csce740.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_ViewCharges(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GenerateBill(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
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
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
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
	
	public static boolean validateRecord(StudentRecord record)
	{
		String validPhone = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";		
		
		StudentDemographics aStudent = record.getStudent();
		if(isNotNull(aStudent.getId()) && isNotNull(aStudent.getFirstname()) && 
		   isNotNull(aStudent.getLastname()) && aStudent.getPhone().matches(validPhone) && 
		   isValidEmail(aStudent.getEmailAddress()) &&  isNotNull(aStudent.getAddressCity()) &&
		   isNotNull(aStudent.getAddressState()) &&  isNotNull(aStudent.getAddressPostalCode()) &&
		   isNotNull(aStudent.getAddressStreet()) && isValidCollege(record.getCollege()) && 
		   isValidTerm(record.getTermBegan()) && isValidStatus(record.getClassStatus()) && 
		   isValidInternationalStatus(record.getInternationalStatus()) && isValidScholarship(record.getScholarship()) &&
		   isValidStudyAbroad(record.getStudyAbroad()) && isValidCourses(record.getCourses()) &&
		   isValidTerm(record.getCapstoneEnrolled()) && isValidTransactions(record.getTransactions()))
			return true;
		else
			return false;
	}
	
	private static boolean isValidTransactions(Transaction[] transactions) 
	{
		String validYear = "^[1-9][0-9]{4}$";
		String validDay = "^(3[0-1]|2[0-9]|1[0-9]|0[1-9])$";
		String validMonth = "^(1[0-2]|0[1-9])$" ;
		for(int i = 0; i < transactions.length; i++)
		{
			if(!isNotNull(transactions[i].getNote()) || transactions[i].getAmount() < 0.0 ||
			   !(transactions[i].getType().equals("PAYMENT") || transactions[i].getType().equals("CHARGE")) ||
			   !Integer.toString(transactions[i].getTransactionDay()).matches(validDay) || 
			   !Integer.toString(transactions[i].getTransactionMonth()).matches(validMonth) ||
			   !Integer.toString(transactions[i].getTransactionYear()).matches(validYear))
			{
				return false;
			}
		}
		return true;
	}
	
	private static boolean isValidCourses(Course[] courses) 
	{
		for(int i = 0; i < courses.length; i++)
		{
			if(!isNotNull(courses[i].getId()) || !isNotNull(courses[i].getName()) || courses[i].getNumCredits() < 0)
			{
				return false;
			}
		}
		return true;
	}

	private static boolean isValidStudyAbroad(String studyAbroad) {
		if(studyAbroad.equals("REGULAR") || studyAbroad.equals("COHORT") || 
		   studyAbroad.equals("NONE"))
			return true;
		else
			return false;
	}

	private static boolean isValidScholarship(String scholarship) {
		if(scholarship.equals("WOODROW") || scholarship.equals("DEPARTMENTAL") || 
		   scholarship.equals("GENERAL") || scholarship.equals("ATHLETIC") ||
		   scholarship.equals("SIMS") || scholarship.equals("NONE"))
			return true;
		else
			return false;
	}

	private static boolean isValidInternationalStatus(String internationalStatus) 
	{
		if(internationalStatus.equals("SHORT_TERM") || internationalStatus.equals("SPONSORED") || 
		   internationalStatus.equals("NONE"))
			return true;
		else
			return false;
	}
	private static boolean isValidStatus(String classStatus) 
	{
		if(classStatus.equals("FRESHMAN") || classStatus.equals("SOPHMORE") || 
		   classStatus.equals("JUNIOR") || classStatus.equals("SENIOR") ||
		   classStatus.equals("MASTERS") || classStatus.equals("PHD") ||
		   classStatus.equals("GRADUATED"))
			return true;
		else
			return false;
	}

	private static boolean isValidTerm(Term termBegan) 
	{
		String validYear = "^[1-9][0-9]{4}$";
		if(termBegan.getSemester().equals("SPRING") || termBegan.getSemester().equals("SUMMER") || termBegan.getSemester().equals("FALL"))
		{
			
			if(Integer.toString(termBegan.getYear()).matches(validYear))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	private static boolean isValidCollege(String college) 
	{
		if(college.equals("ARTS_AND_SCIENCES") || college.equals("ENGINEERING_AND_COMPUTING")  ||college.equals("GRADUATE_SCHOOL"))
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