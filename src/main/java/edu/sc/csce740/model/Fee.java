package main.java.edu.sc.csce740.model;

/*
 * Fee class: used for represeting all the fees a student could have
 */
public final class Fee {
	/*
	 * FEE NOTES
	 */
	private final static String NOTE_UG_RESIDENT_TUITION = "UG_RESIDENT_TUITION";
	private final static String NOTE_UG_NONRESIDENT_TUITION = "UG_NONRESIDENT_TUITION";
	private final static String NOTE_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION = "UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION";
	private final static String NOTE_UG_NONRESIDENT_GENERAL_TUITION = "UG_NONRESIDENT_GENERAL_TUITION";
	private final static String NOTE_UG_NONRESIDENT_ATHLETICS_TUITION = "UG_NONRESIDENT_ATHLETICS_TUITION";
	private final static String NOTE_UG_NONRESIDENT_SIMS_TUITION = "UG_NONRESIDENT_SIMS_TUITION";
	private final static String NOTE_UG_MILITARY_TUITION = "UG_MILITARY_TUITION";
	private final static String NOTE_GRAD_RESIDENT_TUITION = "GRAD_RESIDENT_TUITION";
	private final static String NOTE_GRAD_NONRESIDENT_TUITION = "GRAD_NONRESIDENT_TUITION";
	private final static String NOTE_UG_RESIDENT_17_HOURS_ABOVE = "UG_RESIDENT_17_HOURS_ABOVE";
	private final static String NOTE_UG_NONRESIDENT_17_HOURS_ABOVE = "UG_NONRESIDENT_17_HOURS_ABOVE";
	private final static String NOTE_GRAD_RESIDENT_17_HOURS_ABOVE = "GRAD_RESIDENT_17_HOURS_ABOVE";
	private final static String NOTE_GRAD_NONRESIDENT_17_HOURS_ABOVE = "GRAD_NONRESIDENT_17_HOURS_ABOVE";
	private final static String NOTE_TECHNOLOGY_FEE = "TECHNOLOGY_FEE";
	private final static String NOTE_MATRICULATION = "MATRICULATION";
	private final static String NOTE_CAPSTONE_PER_SEMESTER = "CAPSTONE_PER_SEMESTER";
	private final static String NOTE_HEALTH_INSURANCE = "HEALTH_INSURANCE";
	private final static String NOTE_PT_UG_RESIDENT_TUITION = "PT_UG_RESIDENT_TUITION";
	private final static String NOTE_PT_UG_NONRESIDENT_TUITION = "PT_UG_NONRESIDENT_TUITION";
	private final static String NOTE_PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION = "PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION";
	private final static String NOTE_PT_UG_NONRESIDENT_GENERAL_TUITION = "PT_UG_NONRESIDENT_GENERAL_TUITION";
	private final static String NOTE_PT_UG_NONRESIDENT_ATHLETICS_TUITION = "PT_UG_NONRESIDENT_ATHLETICS_TUITION";
	private final static String NOTE_PT_UG_NONRESIDENT_SIMS_TUITION = "PT_UG_NONRESIDENT_SIMS_TUITION";
	private final static String NOTE_PT_UG_MILITARY_TUITION = "PT_UG_MILITARY_TUITION";
	private final static String NOTE_PT_GRAD_RESIDENT_TUITION = "PT_GRAD_RESIDENT_TUITION";
	private final static String NOTE_PT_GRAD_NONRESIDENT_TUITION = "PT_GRAD_NONRESIDENT_TUITION";
	private final static String NOTE_PT_GRAD_NONRESIDENT_ONLINE_TUITION = "PT_GRAD_NONRESIDENT_ONLINE_TUITION";
	private final static String NOTE_PT_TECHNOLOGY_FEE = "PT_TECHNOLOGY_FEE";
	private final static String NOTE_PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS = "PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS";
	private final static String NOTE_PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS = "PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS";
	private final static String NOTE_PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS = "PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS";
	private final static String NOTE_PT_UG_STUDENT_HEALTH_6_TO_11_HOURS = "PT_UG_STUDENT_HEALTH_6_TO_11_HOURS";
	private final static String NOTE_STUDY_ABROAD = "STUDY_ABROAD";
	private final static String NOTE_STUDY_ABROAD_COHORT = "STUDY_ABROAD_COHORT";
	private final static String NOTE_STUDY_ABROAD_EXCHANGE_DEPOSIT = "STUDY_ABROAD_EXCHANGE_DEPOSIT";
	private final static String NOTE_STUDY_ABROAD_INSURANCE = "STUDY_ABROAD_INSURANCE";
	private final static String NOTE_INTERNATIONAL_ENROLLMENT = "INTERNATIONAL_ENROLLMENT";
	private final static String NOTE_INTERNATIONAL_SHORT_TERM = "INTERNATIONAL_SHORT_TERM";
	private final static String NOTE_INTERNATIONAL_SPONSORED = "INTERNATIONAL_SPONSORED";
	private final static String NOTE_NATIONAL_STUDENT_EXCHANGE_ADMIN = "NATIONAL_STUDENT_EXCHANGE_ADMIN";

	/*
	 * FEE VALUES
	 */

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
	private final static double UG_RESIDENT_17_HOURS_ABOVE = 80; // UG:
																	// Resident,
																	// nonresident
																	// scholarship,
																	// active
																	// duty
																	// military
	private final static double UG_NONRESIDENT_17_HOURS_ABOVE = 208;
	private final static double GRAD_RESIDENT_17_HOURS_ABOVE = 80;
	private final static double GRAD_NONRESIDENT_17_HOURS_ABOVE = 170;
	private final static double TECHNOLOGY_FEE = 200;
	private final static double MATRICULATION = 80;
	private final static double CAPSTONE_PER_SEMESTER = 100; // TODO: footer
																// note
	private final static double HEALTH_INSURANCE = 2547; // Grad, international
															// students, or
															// others who opt in

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

	/**
	 * Function for getting the value of a fee
	 * 
	 * @param feeType:
	 *            the enumeration for the type of fee
	 * @return: double: the value of the desired fee
	 */
	public static double getFeeAmount(EnumFee feeType) {
		switch (feeType) {
		case UG_RESIDENT_TUITION:
			return UG_RESIDENT_TUITION;
		case UG_NONRESIDENT_TUITION:
			return UG_NONRESIDENT_TUITION;
		case UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION:
			return UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
		case UG_NONRESIDENT_GENERAL_TUITION:
			return UG_NONRESIDENT_GENERAL_TUITION;
		case UG_NONRESIDENT_ATHLETICS_TUITION:
			return UG_NONRESIDENT_ATHLETICS_TUITION;
		case UG_NONRESIDENT_SIMS_TUITION:
			return UG_NONRESIDENT_SIMS_TUITION;
		case UG_MILITARY_TUITION:
			return UG_MILITARY_TUITION;
		case GRAD_RESIDENT_TUITION:
			return GRAD_RESIDENT_TUITION;
		case GRAD_NONRESIDENT_TUITION:
			return GRAD_NONRESIDENT_TUITION;
		case UG_RESIDENT_17_HOURS_ABOVE:
			return UG_RESIDENT_17_HOURS_ABOVE;
		case UG_NONRESIDENT_17_HOURS_ABOVE:
			return UG_NONRESIDENT_17_HOURS_ABOVE;
		case GRAD_RESIDENT_17_HOURS_ABOVE:
			return GRAD_RESIDENT_17_HOURS_ABOVE;
		case GRAD_NONRESIDENT_17_HOURS_ABOVE:
			return GRAD_NONRESIDENT_17_HOURS_ABOVE;
		case TECHNOLOGY_FEE:
			return TECHNOLOGY_FEE;
		case MATRICULATION:
			return MATRICULATION;
		case CAPSTONE_PER_SEMESTER:
			return CAPSTONE_PER_SEMESTER;
		case HEALTH_INSURANCE:
			return HEALTH_INSURANCE;
		case PT_UG_RESIDENT_TUITION:
			return PT_UG_RESIDENT_TUITION;
		case PT_UG_NONRESIDENT_TUITION:
			return PT_UG_NONRESIDENT_TUITION;
		case PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION:
			return PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
		case PT_UG_NONRESIDENT_GENERAL_TUITION:
			return PT_UG_NONRESIDENT_GENERAL_TUITION;
		case PT_UG_NONRESIDENT_ATHLETICS_TUITION:
			return PT_UG_NONRESIDENT_ATHLETICS_TUITION;
		case PT_UG_NONRESIDENT_SIMS_TUITION:
			return PT_UG_NONRESIDENT_SIMS_TUITION;
		case PT_UG_MILITARY_TUITION:
			return PT_UG_MILITARY_TUITION;
		case PT_GRAD_RESIDENT_TUITION:
			return PT_GRAD_RESIDENT_TUITION;
		case PT_GRAD_NONRESIDENT_TUITION:
			return PT_GRAD_NONRESIDENT_TUITION;
		case PT_GRAD_NONRESIDENT_ONLINE_TUITION:
			return PT_GRAD_NONRESIDENT_ONLINE_TUITION;
		case PT_TECHNOLOGY_FEE:
			return PT_TECHNOLOGY_FEE;
		case PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS:
			return PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS;
		case PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS:
			return PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS;
		case PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS:
			return PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS;
		case PT_UG_STUDENT_HEALTH_6_TO_11_HOURS:
			return PT_UG_STUDENT_HEALTH_6_TO_11_HOURS;
		case STUDY_ABROAD:
			return STUDY_ABROAD;
		case STUDY_ABROAD_COHORT:
			return STUDY_ABROAD_COHORT;
		case STUDY_ABROAD_EXCHANGE_DEPOSIT:
			return STUDY_ABROAD_EXCHANGE_DEPOSIT;
		case STUDY_ABROAD_INSURANCE:
			return STUDY_ABROAD_INSURANCE;
		case INTERNATIONAL_ENROLLMENT:
			return INTERNATIONAL_ENROLLMENT;
		case INTERNATIONAL_SHORT_TERM:
			return INTERNATIONAL_SHORT_TERM;
		case INTERNATIONAL_SPONSORED:
			return INTERNATIONAL_SPONSORED;
		case NATIONAL_STUDENT_EXCHANGE_ADMIN:
			return NATIONAL_STUDENT_EXCHANGE_ADMIN;
		default:
			System.out.println("Invalid fee type supplied.");
			return 0.0;
		}
	}

	/**
	 * Function for getting the description of a fee
	 * 
	 * @param feeType:
	 *            the enumeration for the type of fee
	 * @return: String: the description of the desired fee
	 */
	public static String getFeeNote(EnumFee feeType) {
		switch (feeType) {
		case UG_RESIDENT_TUITION:
			return NOTE_UG_RESIDENT_TUITION;
		case UG_NONRESIDENT_TUITION:
			return NOTE_UG_NONRESIDENT_TUITION;
		case UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION:
			return NOTE_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
		case UG_NONRESIDENT_GENERAL_TUITION:
			return NOTE_UG_NONRESIDENT_GENERAL_TUITION;
		case UG_NONRESIDENT_ATHLETICS_TUITION:
			return NOTE_UG_NONRESIDENT_ATHLETICS_TUITION;
		case UG_NONRESIDENT_SIMS_TUITION:
			return NOTE_UG_NONRESIDENT_SIMS_TUITION;
		case UG_MILITARY_TUITION:
			return NOTE_UG_MILITARY_TUITION;
		case GRAD_RESIDENT_TUITION:
			return NOTE_GRAD_RESIDENT_TUITION;
		case GRAD_NONRESIDENT_TUITION:
			return NOTE_GRAD_NONRESIDENT_TUITION;
		case UG_RESIDENT_17_HOURS_ABOVE:
			return NOTE_UG_RESIDENT_17_HOURS_ABOVE;
		case UG_NONRESIDENT_17_HOURS_ABOVE:
			return NOTE_UG_NONRESIDENT_17_HOURS_ABOVE;
		case GRAD_RESIDENT_17_HOURS_ABOVE:
			return NOTE_GRAD_RESIDENT_17_HOURS_ABOVE;
		case GRAD_NONRESIDENT_17_HOURS_ABOVE:
			return NOTE_GRAD_NONRESIDENT_17_HOURS_ABOVE;
		case TECHNOLOGY_FEE:
			return NOTE_TECHNOLOGY_FEE;
		case MATRICULATION:
			return NOTE_MATRICULATION;
		case CAPSTONE_PER_SEMESTER:
			return NOTE_CAPSTONE_PER_SEMESTER;
		case HEALTH_INSURANCE:
			return NOTE_HEALTH_INSURANCE;
		case PT_UG_RESIDENT_TUITION:
			return NOTE_PT_UG_RESIDENT_TUITION;
		case PT_UG_NONRESIDENT_TUITION:
			return NOTE_PT_UG_NONRESIDENT_TUITION;
		case PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION:
			return NOTE_PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION;
		case PT_UG_NONRESIDENT_GENERAL_TUITION:
			return NOTE_PT_UG_NONRESIDENT_GENERAL_TUITION;
		case PT_UG_NONRESIDENT_ATHLETICS_TUITION:
			return NOTE_PT_UG_NONRESIDENT_ATHLETICS_TUITION;
		case PT_UG_NONRESIDENT_SIMS_TUITION:
			return NOTE_PT_UG_NONRESIDENT_SIMS_TUITION;
		case PT_UG_MILITARY_TUITION:
			return NOTE_PT_UG_MILITARY_TUITION;
		case PT_GRAD_RESIDENT_TUITION:
			return NOTE_PT_GRAD_RESIDENT_TUITION;
		case PT_GRAD_NONRESIDENT_TUITION:
			return NOTE_PT_GRAD_NONRESIDENT_TUITION;
		case PT_GRAD_NONRESIDENT_ONLINE_TUITION:
			return NOTE_PT_GRAD_NONRESIDENT_ONLINE_TUITION;
		case PT_TECHNOLOGY_FEE:
			return NOTE_PT_TECHNOLOGY_FEE;
		case PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS:
			return NOTE_PT_GRAD_ASSISTANT_HEALTH_CENTER_LESS;
		case PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS:
			return NOTE_PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS;
		case PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS:
			return NOTE_PT_GRAD__HEALTH_CENTER_6_TO_8_HOURS;
		case PT_UG_STUDENT_HEALTH_6_TO_11_HOURS:
			return NOTE_PT_UG_STUDENT_HEALTH_6_TO_11_HOURS;
		case STUDY_ABROAD:
			return NOTE_STUDY_ABROAD;
		case STUDY_ABROAD_COHORT:
			return NOTE_STUDY_ABROAD_COHORT;
		case STUDY_ABROAD_EXCHANGE_DEPOSIT:
			return NOTE_STUDY_ABROAD_EXCHANGE_DEPOSIT;
		case STUDY_ABROAD_INSURANCE:
			return NOTE_STUDY_ABROAD_INSURANCE;
		case INTERNATIONAL_ENROLLMENT:
			return NOTE_INTERNATIONAL_ENROLLMENT;
		case INTERNATIONAL_SHORT_TERM:
			return NOTE_INTERNATIONAL_SHORT_TERM;
		case INTERNATIONAL_SPONSORED:
			return NOTE_INTERNATIONAL_SPONSORED;
		case NATIONAL_STUDENT_EXCHANGE_ADMIN:
			return NOTE_NATIONAL_STUDENT_EXCHANGE_ADMIN;
		default:
			System.out.println("Invalid fee type supplied.");
			return "";
		}
	}
}
