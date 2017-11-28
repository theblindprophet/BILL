package main.java.edu.sc.csce740.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;

public class Billing {
	
	public static double calculateBalance(Transaction[] transactions)
	{
		double total = 0;
		for(int i = 0; i < transactions.length; i++)
		{
			if(transactions[i].getType().equals("PAYMENT"))
			{
				total += transactions[i].getAmount();
			}else{
				total -= transactions[i].getAmount();
			}
		}
		
		return total;
	}
	
	public static Bill getBill(User user) {
		return new Bill(user.getRecord().getStudent(), user.getCollege(), user.getRecord().getClassStatus(), calculateCharges(user));
	}
	
	public static Transaction[] calculateCharges(User user) {
		ArrayList<Transaction> chargeList = new ArrayList<Transaction>();
		Transaction[] userTrans = user.getRecord().getTransactions();
		
		chargeList.addAll(calculateCurrentCharges(user));
		
		for (Transaction transaction : userTrans) {
			chargeList.add(transaction);
		}
		
		return chargeList.toArray(new Transaction[chargeList.size()]);
	}
	
	public static void applyPayment(StudentRecord record, double amount, String note) throws InvalidPaymentException
	{
		Transaction[] newTransactions = new Transaction[record.getTransactions().length+1];
		LocalDateTime today = LocalDateTime.now();
		
		Calendar c = Calendar.getInstance();
		final int month = c.get(Calendar.MONTH) + 1;
		final int day = c.get(Calendar.DATE);
		final int year = c.get(Calendar.YEAR);
		
		Transaction newPayment = new Transaction("PAYMENT", month, day, year, amount, note);
		
		boolean isValidPayment = AVPS.isValidTransaction(newPayment);
		try
		{
			if(isValidPayment)
			{
				newTransactions[newTransactions.length -1] = newPayment;
				record.setTransactions(newTransactions);
			}
			else
			{
				throw new InvalidPaymentException();
			}
		}
		catch(InvalidPaymentException e)
		{
			System.out.println("This is a not a valid payment");;
		}
	}
	
	private static ArrayList<Transaction> calculateCurrentCharges(User user) {
		ArrayList<Transaction> chargeList = new ArrayList<Transaction>();
		int numHours = calculateClassHours(user);
		Calendar c = Calendar.getInstance();
		final int month = c.get(Calendar.MONTH) + 1;
		final int day = c.get(Calendar.DATE);
		final int year = c.get(Calendar.YEAR);
		StudentRecord SR = user.getRecord();
		
		//////////////////////////////////////////
		// Fees not related to class status:
		//////////////////////////////////////////
		
		// Technology Fee
		if (numHours >= 12) {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.TECHNOLOGY_FEE), Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE)));
		} else {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_TECHNOLOGY_FEE), Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE)));
		}
		
		// Matriculation fee - one time charge derived from lack of historical charges
		if (findHistoricalTransaction(SR.getTransactions(), EnumFee.MATRICULATION) != null) {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.MATRICULATION), Fee.getFeeNote(EnumFee.MATRICULATION)));
		}
		
		// Capstone fee - applies to first 2 years in Capstone program
		Term termNow = new Term();
		termNow.setYear(year);
		
		if (month <= 8) {
			termNow.setSemester("SPRING");
		} else {
			termNow.setSemester("FALL");
		}
		
		if (SR.getCapstoneEnrolled() != null && SR.getCapstoneEnrolled().termDifference(termNow) <= 3){ // If the current term is 3 or less semesters away from the enrollment date 
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.CAPSTONE_PER_SEMESTER), Fee.getFeeNote(EnumFee.CAPSTONE_PER_SEMESTER)));
		}
		
		// Health insurance - "For health insurance, a student could have an external insurance plan. The profile should have a flag for whether that is the case. If they do not, and it is the fall semester, you apply the fee."
		if (!SR.isOutsideInsurance() && termNow.getSemester().equals("FALL")) {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.HEALTH_INSURANCE), Fee.getFeeNote(EnumFee.HEALTH_INSURANCE)));
		}
		
		// Study Abroad
		if (SR.getStudyAbroad().equals("REGULAR") || SR.getStudyAbroad().equals("COHORT")) {
			// Is a study abroad student
			// So, we apply the insurance fee and exchange deposit fee
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.STUDY_ABROAD_INSURANCE), Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE)));
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT), Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT)));
		}
		// 		Study abroad fees (regular OR cohort)
		if (SR.getStudyAbroad().equals("REGULAR")) {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.STUDY_ABROAD), Fee.getFeeNote(EnumFee.STUDY_ABROAD)));
		} else if (SR.getStudyAbroad().equals("COHORT")) {
			chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.STUDY_ABROAD_COHORT), Fee.getFeeNote(EnumFee.STUDY_ABROAD_COHORT)));
		}
		
		// International fees
		if (SR.isInternational()) {
			
			// Sponsored or shortterm fees
			if (SR.getInternationalStatus().equals("SPONSORED")) {
				chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.INTERNATIONAL_SPONSORED), Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED)));
			} else if (SR.getInternationalStatus().equals("SHORTTERM")) {
				chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.INTERNATIONAL_SHORT_TERM), Fee.getFeeNote(EnumFee.INTERNATIONAL_SHORT_TERM)));
			}
			
			// One-time international enrollment fee
			if (findHistoricalTransaction(SR.getTransactions(), EnumFee.INTERNATIONAL_ENROLLMENT) == null) { 
				chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.INTERNATIONAL_ENROLLMENT), Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT)));
			}
			
			// POSSIBLE TODO: NATIONAL_STUDENT_EXCHANGE_ADMIN. However, Dr. Gay [non-explicitly] stated to ignore this fee https://dropbox.cse.sc.edu/mod/forum/discuss.php?d=139#p306
		}
		
		//////////////////////////////////////////		
		// Fees related to class status:
		//////////////////////////////////////////		
		
		if (user.getRecord().getClassStatus().equals("PHD") || user.getRecord().getClassStatus().equals("MASTERS")) { 
			// PHD or masters student
			
			if (numHours >= 12) { 
				// Full time
				
				if (SR.isResident()) {
					// Resident
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.GRAD_RESIDENT_TUITION), Fee.getFeeNote(EnumFee.GRAD_RESIDENT_TUITION)));
					
					// 17 hours/above charge
					if (numHours >= 17) {
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.GRAD_RESIDENT_17_HOURS_ABOVE), Fee.getFeeNote(EnumFee.GRAD_RESIDENT_17_HOURS_ABOVE)));
					}
				} else {
					// Non-resident
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.GRAD_NONRESIDENT_TUITION), Fee.getFeeNote(EnumFee.GRAD_NONRESIDENT_TUITION)));
					
					// 17 hours/above charge
					if (numHours >= 17) {
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.GRAD_NONRESIDENT_17_HOURS_ABOVE), Fee.getFeeNote(EnumFee.GRAD_NONRESIDENT_17_HOURS_ABOVE)));
					}
				}	
			} else {
				// Part time
				
				if (SR.isResident()) {
					// Resident
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD_RESIDENT_TUITION), Fee.getFeeNote(EnumFee.PT_GRAD_RESIDENT_TUITION)));
				} else {
					// Non-resident
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD_NONRESIDENT_TUITION), Fee.getFeeNote(EnumFee.PT_GRAD_NONRESIDENT_TUITION)));
					
					
					// Online tuition (if applicable)
					int numOnlineHours = calculateOnlineClassHours(user);
					if (numOnlineHours > 0) {
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD_NONRESIDENT_ONLINE_TUITION) * numOnlineHours, Fee.getFeeNote(EnumFee.PT_GRAD_NONRESIDENT_ONLINE_TUITION)));
					}
				}
				
				// Health center fees
				//		12 hour and less fee is mandatory for graduate ASSISTANTS
				if (user.getRecord().isGradAssistant()) {
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS), Fee.getFeeNote(EnumFee.PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS)));
				} else if (numHours >= 9 && numHours <= 11) {
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS), Fee.getFeeNote(EnumFee.PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS)));
				} else if (numHours >= 6 && numHours <= 8) {
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS), Fee.getFeeNote(EnumFee.PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS)));
				}
			}
			
			// TODO: Online courses????
		} else if (user.getClass().equals("FRESHMAN") || user.getClass().equals("SOPHOMORE") || user.getClass().equals("JUNIOR") || user.getClass().equals("SENIOR")) { // If undergrad student
			if (numHours >= 12) { 
				// Full time
				
				// 17 hours/above charge
				if (numHours >= 17) {
					if (SR.isActiveDuty() || (!SR.getScholarship().equals("NONE") && !SR.getScholarship().trim().equals("")) || SR.isResident()) {
						// Active duty, non resident scholarship, or resident
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_RESIDENT_17_HOURS_ABOVE), Fee.getFeeNote(EnumFee.UG_RESIDENT_17_HOURS_ABOVE)));
					} else if (!SR.isResident()) {
						// Non resident
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_17_HOURS_ABOVE), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_17_HOURS_ABOVE)));
					}
				}
				
				if (SR.isActiveDuty()) {
					// Active duty 
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_MILITARY_TUITION), Fee.getFeeNote(EnumFee.UG_MILITARY_TUITION)));
				}
				else if (user.getRecord().isResident()) { 
					// In-state 
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_RESIDENT_TUITION), Fee.getFeeNote(EnumFee.UG_RESIDENT_TUITION)));
				} else {
					if (!SR.getScholarship().equals("NONE") && !SR.getScholarship().trim().equals("")) {
						// Has scholarship
						String note = "";
						double amount = -1;
						
						switch (SR.getScholarship()) {
							case "WOODROW":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION)));
								break;								
							case "ATHLETIC":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_ATHLETICS_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_ATHLETICS_TUITION)));
								break;								
							case "DEPARTMENTAL":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION)));
								break;
							case "GENERAL":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_GENERAL_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_GENERAL_TUITION)));
								break;								
							case "SIMS":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_SIMS_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_GENERAL_TUITION)));
								break;									
						}
						
						chargeList.add(new Transaction("CHARGE", month, day, year, amount, note));
					} else { 
						// No scholarship
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_TUITION), Fee.getFeeNote(EnumFee.UG_NONRESIDENT_TUITION)));
					}
				}
			} else {
				// [Undergrad] Part Time
				
				// Health center 6 to 11 hour charge
				if (numHours >= 6 && numHours <= 11) {
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_STUDENT_HEALTH_6_TO_11_HOURS), Fee.getFeeNote(EnumFee.PT_UG_STUDENT_HEALTH_6_TO_11_HOURS)));
				}
				if (SR.isActiveDuty()) {
					// Active duty 
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_MILITARY_TUITION), Fee.getFeeNote(EnumFee.PT_UG_MILITARY_TUITION)));
				}
				else if (user.getRecord().isResident()) { 
					// In-state 
					chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_RESIDENT_TUITION), Fee.getFeeNote(EnumFee.PT_UG_RESIDENT_TUITION)));
				} else {
					// Out-of-state
					if (!SR.getScholarship().equals("NONE") && !SR.getScholarship().trim().equals("")) {
						// Has scholarship
						String note = "";
						double amount = -1;
						
						switch (SR.getScholarship()) {
							case "WOODROW":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION)));
								break;								
							case "ATHLETIC":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_ATHLETICS_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_ATHLETICS_TUITION)));
								break;								
							case "DEPARTMENTAL":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION)));
								break;
							case "GENERAL":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION)));
								break;								
							case "SIMS":
								chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_SIMS_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION)));
								break;									
						}
						
						chargeList.add(new Transaction("CHARGE", month, day, year, amount, note));
					} else { 
						// No scholarship
						chargeList.add(new Transaction("CHARGE", month, day, year, Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_TUITION), Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_TUITION)));
					}
				}				
			}
		} else if (user.getClass().equals("GRADUATED")) {
			// Don't add charges for a graduated student
			// (Do nothing)
		} else {
			System.out.println("Student is not PHD, MASTERS, FRESHMAN, SOPHOMORE, JUNIOR, or SENIOR");
			
		}

		return chargeList; 
	}
	
	private static ArrayList<Transaction> getPreviousCharges(StudentRecord sr) {
		
		
		return null; // TODO
	}
	
	private static int calculateClassHours(User user) {
		int numHours = 0;
		if (user != null && user.getRecord() != null && user.getRecord().getCourses() != null) {
			for (Course course : user.getRecord().getCourses()) {
				numHours += course.getNumCredits();
			}
		}
		
		return numHours;
	}
	
	private static int calculateOnlineClassHours(User user) {
		int numHours = 0;
		if (user != null && user.getRecord() != null && user.getRecord().getCourses() != null) {
			for (Course course : user.getRecord().getCourses()) {
				if (course.isOnline()) {
					numHours += course.getNumCredits();
				}
			}
		}
		
		return numHours;
	}
	
	private static Transaction findHistoricalTransaction(Transaction[] transactions, EnumFee feeType) {
		String feeNote = Fee.getFeeNote(feeType);
		
		for (Transaction transaction : transactions) {
			if (transaction.getNote() == feeNote) {
				return transaction;
			}
		}
		
		return null;
	}
	
}
