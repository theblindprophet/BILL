package main.java.edu.sc.csce740.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Billing {

	
	
	
	// Omitted fees:
	/*
	 * Gamecock gateway discount
	Optional athletic fee
	Dual enrollment fee
	Reinstatement fee
	Post office-related fees
	Orientation fee
	Parking
	*/

	
	
	
	/*
	 * User
	 * 
	 * 
	 * 	private String id = "";
	private String firstname = "";
	private String lastname = "";
	private String role = "";
	private String college = "";
	private StudentRecord record;
	 * 
	 * Ex:
	 * 
	 *     {
       "id": "mhunt",
       "firstName": "Michelle",
       "lastName": "Hunt",
       "role": "STUDENT",
       "college": "ARTS_AND_SCIENCES"
    },
    {
	 * 
	 * User.StudentRecord
	 * 
	 * 	private StudentDemographics student;
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
	 * 
	 * Ex:
	 * 
	 *     "student": {
      "id": "mhunt",
      "firstName": "Michelle",
      "lastName": "Hunt",
      "phone": "999-999-9999",
      "emailAddress": "mhunt@mailbox.sc.edu",
      "addressStreet": "221B Baker St.",
      "addressCity": "Pittsburgh",
      "addressState": "PA",
      "addressPostalCode": "26505"
    },
    "college": "ARTS_AND_SCIENCES",
    "termBegan": {
      "semester": "FALL",
      "year": 2016
    },
    "classStatus": "PHD",
    "gradAssistant": true,
    "international": false,
    "internationalStatus": "NONE",
    "resident": false,
    "activeDuty": false,
    "veteran": false,
    "freeTuition": false,
    "scholarship": "NONE",
    "studyAbroad": "NONE",
    "nationalStudentExchange": false,
    "outsideInsurance": false,
    "courses"
	 * 
	 * 
	 */
	
	
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
	
	
	public static Transaction[] calculateCharges(User user) {
		ArrayList<Transaction> chargeList = new ArrayList<Transaction>();
		
		chargeList.addAll(calculateCurrentCharges(user));
		chargeList.addAll(getPreviousCharges(user.getRecord()));
		
		return (Transaction[]) chargeList.toArray();
	}
	
	public static void applyPayment(StudentRecord record, double amount, String note) throws InvalidPaymentException
	{
		Transaction[] newTransactions = new Transaction[record.getTransactions().length+1];
		LocalDateTime today = LocalDateTime.now();
		int year = today.getYear();
		int month = today.getMonth().getValue();
		int day = today.getDayOfYear();
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
		final int month = c.get(Calendar.MONTH);
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
		
		
		//////////////////////////////////////////		
		// Fees related to class status:
		//////////////////////////////////////////		
		
		if (user.getClass().equals("PHD") || user.getClass().equals("MASTERS")) { 
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
								note = Fee.getFeeNote(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								break;								
							case "ATHLETIC":
								note = Fee.getFeeNote(EnumFee.UG_NONRESIDENT_ATHLETICS_TUITION);
								amount = Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_ATHLETICS_TUITION);
								break;								
							case "DEPARTMENTAL":
								note = Fee.getFeeNote(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								break;
							case "GENERAL":
								note = Fee.getFeeNote(EnumFee.UG_NONRESIDENT_GENERAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_GENERAL_TUITION);
								break;								
							case "SIMS":
								note = Fee.getFeeNote(EnumFee.UG_NONRESIDENT_SIMS_TUITION);
								amount = Fee.getFeeAmount(EnumFee.UG_NONRESIDENT_SIMS_TUITION);
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
								note = Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								break;								
							case "ATHLETIC":
								note = Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_ATHLETICS_TUITION);
								amount = Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_ATHLETICS_TUITION);
								break;								
							case "DEPARTMENTAL":
								note = Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION);
								break;
							case "GENERAL":
								note = Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION);
								break;								
							case "SIMS":
								note = Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION);
								amount = Fee.getFeeAmount(EnumFee.PT_UG_NONRESIDENT_SIMS_TUITION);
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
	
	
	
}
