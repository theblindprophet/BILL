package main.java.edu.sc.csce740.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Billing {
	/////////////////////////////
	// FULL TIME
	/////////////////////////////	
	
	// Undergrad Tuition
	private final static double UG_RESIDENT_TUITION = 5727;
	private final static double UG_NONRESIDENT_TUITION = 15441;
	private final static double UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION = 8502;
	private final static double UG_NONRESIDENT_GENERAL_TUITION = 5727;
	private final static double UG_NONRESIDENT_ATHLETICS_TUITION = 8502;
	private final static double UG_NONRESIDENT_SIMS_TUITION = 10965;
	private final static double UG_MILITARY_TUITION = 3351;
	
	// Graduate Tuition
	private final static double GRAD_RESIDENT_TUITION = 6399;
	private final static double GRAD_NONRESIDENT_TUITION = 13704;
	
	
	// Fees
	private final static double UG_RESIDENT_17_HOURS_ABOVE = 80; // UG: Resident, nonresident scholarship, active duty military
	private final static double UG_NONRESIDENT_17_HOURS_ABOVE = 208; 
	private final static double GRAD_RESIDENT_17_HOURS_ABOVE = 80; 
	private final static double GRAD_NONRESIDENT_17_HOURS_ABOVE = 170; 
	private final static double TECHNOLOGY_FEE = 200;
	private final static double MATRICULATION = 80;
	private final static double CAPSTONE_PER_SEMESTER = 100; // TODO: footer note
	private final static double HEALTH_INSURANCE = 2547; // Grad, international students, or others who opt in
	
	/////////////////////////////	
	// PART TIME
	/////////////////////////////	
	
	// Undergraduate Tuition
	private final static double PT_UG_RESIDENT_TUITION = 477.25;
	private final static double PT_UG_NONRESIDENT_TUITION = 1286.75;
	private final static double PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION = 708.5;
	private final static double PT_UG_NONRESIDENT_GENERAL_TUITION = 477.25;
	private final static double PT_UG_NONRESIDENT_ATHLETICS_TUITION = 708.5;
	private final static double PT_UG_NONRESIDENT_SIMS_TUITION = 913.75;
	private final static double PT_UG_MILITARY_TUITION = 279.25;
	
	// Graduate Tuition
	private final static double PT_GRAD_RESIDENT_TUITION = 533.25;
	private final static double PT_GRAD_NONRESIDENT_TUITION = 1142;
	private final static double PT_GRAD_NONRESIDENT_ONLINE_TUITION = 533.25;
	
	// Fees
	private final static double PT_TECHNOLOGY_FEE = 17;
	private final static double PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS = 178;
	private final static double PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS = 178;
	private final static double PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS = 119;
	private final static double PT_UG_STUDENT_HEALTH_6_TO_11_HOURS = 119;
	
	
	/////////////////////////////
	// Study Abroad 
	/////////////////////////////
	private final static double STUDY_ABROAD = 150;
	private final static double STUDY_ABROAD_COHORT = 300;
	private final static double STUDY_ABROAD_EXCHANGE_DEPOSIT = 500;
	private final static double STUDY_ABROAD_INSURANCE = 360; // Mandatory
	
	/////////////////////////////
	// International fees
	/////////////////////////////
	private final static double INTERNATIONAL_ENROLLMENT = 750; // One-time fee
	private final static double INTERNATIONAL_SHORT_TERM = 187.50; 
	private final static double INTERNATIONAL_SPONSORED = 250; 
	private final static double NATIONAL_STUDENT_EXCHANGE_ADMIN = 250;
	
	
	
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
			chargeList.add(new Transaction("CHARGE", month, day, year, TECHNOLOGY_FEE, "TECHNOLOGY FEE"));
		} else {
			chargeList.add(new Transaction("CHARGE", month, day, year, PT_TECHNOLOGY_FEE, "TECHNOLOGY FEE"));
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
					chargeList.add(new Transaction("CHARGE", month, day, year, GRAD_RESIDENT_TUITION, "GRADUATE - RESIDENT - TUITION"));
					
					// 17 hours/above charge
					if (numHours >= 17) {
						chargeList.add(new Transaction("CHARGE", month, day, year, GRAD_RESIDENT_17_HOURS_ABOVE, "GRADUATE - RESIDENT - 17 HOURS AND ABOVE"));
					}
				} else {
					// Non-resident
					chargeList.add(new Transaction("CHARGE", month, day, year, GRAD_NONRESIDENT_TUITION, "GRADUATE - NONRESIDENT - TUITION"));
					
					// 17 hours/above charge
					if (numHours >= 17) {
						chargeList.add(new Transaction("CHARGE", month, day, year, GRAD_NONRESIDENT_17_HOURS_ABOVE, "GRADUATE - NONRESIDENT - 17 HOURS AND ABOVE"));
					}
				}	
			} else {
				// Part time
				
				if (SR.isResident()) {
					// Resident
					chargeList.add(new Transaction("CHARGE", month, day, year, PT_GRAD_RESIDENT_TUITION, "GRADUATE - RESIDENT - TUITION"));
				} else {
					// Non-resident
					chargeList.add(new Transaction("CHARGE", month, day, year, PT_GRAD_NONRESIDENT_TUITION, "GRADUATE - NONRESIDENT - TUITION"));
				}
			}
			
			// TODO: Online courses????
		} else if (user.getClass().equals("FRESHMAN") || user.getClass().equals("SOPHOMORE") || user.getClass().equals("JUNIOR") || user.getClass().equals("SENIOR")) { // If undergrad student
			if (numHours >= 12) { 
				// Full time
				
				// 17 hours/above charge
				if (numHours >= 17) {
					if (SR.isActiveDuty() || (!SR.getScholarshop().equals("NONE") && !SR.getScholarshop().trim().equals("")) || SR.isResident()) {
						// Active duty, non resident scholarship, or resident
						chargeList.add(new Transaction("CHARGE", month, day, year, UG_RESIDENT_17_HOURS_ABOVE, "UNDERGRADUATE - RESIDENT, NONRESIDENT SCHOLARSHIP, ACTIVE DUTY MILITARY - 17 HOURS AND ABOVE"));
					} else if (!SR.isResident()) {
						// Non resident
						chargeList.add(new Transaction("CHARGE", month, day, year, UG_NONRESIDENT_17_HOURS_ABOVE, "UNDERGRADUATE - NONRESIDENT - 17 HOURS AND ABOVE"));
					}
				}
				
				if (SR.isActiveDuty()) {
					// Active duty 
					chargeList.add(new Transaction("CHARGE", month, day, year, UG_MILITARY_TUITION, "ACTIVE DUTY MILITARY UNDERGRADUATE - TUITION"));
				}
				else if (user.getRecord().isResident()) { 
					// In-state 
					chargeList.add(new Transaction("CHARGE", month, day, year, UG_RESIDENT_TUITION, "UNDERGRADUATE - RESIDENT - TUITION"));
				} else {
					if (!SR.getScholarshop().equals("NONE") && !SR.getScholarshop().trim().equals("")) {
						// Has scholarship
						String note = "";
						double amount = -1;
						
						switch (SR.getScholarshop()) {
							case "WOODROW":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - WOODROW & DEPARTMENTAL";
								amount = UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
								break;								
							case "ATHLETIC":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - ATHLETICS";
								amount = UG_NONRESIDENT_ATHLETICS_TUITION;
								break;								
							case "DEPARTMENTAL":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - WOODROW & DEPARTMENTAL";
								amount = UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
								break;
							case "GENERAL":
								note = "UNDERGRADUATE - NONRESIDENT SCHOLARSHIP - GENERAL UNIVERSITY";
								amount = UG_NONRESIDENT_GENERAL_TUITION;
								break;								
							case "SIMS":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - SIMS";
								amount = UG_NONRESIDENT_SIMS_TUITION;
								break;									
						}
						
						chargeList.add(new Transaction("CHARGE", month, day, year, amount, note));
					} else { 
						// No scholarship
						chargeList.add(new Transaction("CHARGE", month, day, year, UG_NONRESIDENT_TUITION, "UNDERGRADUATE - NONRESIDENT - TUITION"));
					}
				}
			} else {
				// [Undergrad] Part Time
				
				if (SR.isActiveDuty()) {
					// Active duty 
					chargeList.add(new Transaction("CHARGE", month, day, year, PT_UG_MILITARY_TUITION, "ACTIVE DUTY MILITARY UNDERGRADUATE - TUITION"));
				}
				else if (user.getRecord().isResident()) { 
					// In-state 
					chargeList.add(new Transaction("CHARGE", month, day, year, PT_UG_RESIDENT_TUITION, "UNDERGRADUATE - RESIDENT - TUITION"));
				} else {
					if (!SR.getScholarshop().equals("NONE") && !SR.getScholarshop().trim().equals("")) {
						// Has scholarship
						String note = "";
						double amount = -1;
						
						switch (SR.getScholarshop()) {
							case "WOODROW":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - WOODROW & DEPARTMENTAL";
								amount = PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
								break;								
							case "ATHLETIC":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - ATHLETICS";
								amount = PT_UG_NONRESIDENT_ATHLETICS_TUITION;
								break;								
							case "DEPARTMENTAL":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - WOODROW & DEPARTMENTAL";
								amount = PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
								break;
							case "GENERAL":
								note = "UNDERGRADUATE - NONRESIDENT SCHOLARSHIP - GENERAL UNIVERSITY";
								amount = PT_UG_NONRESIDENT_GENERAL_TUITION;
								break;								
							case "SIMS":
								note = "UNDERGRADUATE - NONRESIDENT - SCHOLARSHIP - SIMS";
								amount = PT_UG_NONRESIDENT_SIMS_TUITION;
								break;									
						}
						
						chargeList.add(new Transaction("CHARGE", month, day, year, amount, note));
					} else { 
						// No scholarship
						chargeList.add(new Transaction("CHARGE", month, day, year, PT_UG_NONRESIDENT_TUITION, "UNDERGRADUATE - NONRESIDENT - TUITION"));
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
				numHours += course.getNumHours();
			}
		}
		
		return numHours;
	}
}
