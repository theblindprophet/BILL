package test.java.edu.sc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.Bill;
import main.java.edu.sc.csce740.model.Billing;
import main.java.edu.sc.csce740.model.EnumFee;
import main.java.edu.sc.csce740.model.InvalidUserIdException;
import main.java.edu.sc.csce740.model.Transaction;
import main.java.edu.sc.csce740.model.Fee;

public class BILLTest_GenerateBill {
	private static BILL _BILL;
	private static final String USERS_FILE = "src/test/resources/users.txt";
	private static final String STUDENTS_FILE = "src/test/resources/students.txt";
	private static double DELTA = 0.001; // Accuracy of dollar amount comparisons...we want accuracy all the way down to the penny, so we add a degree of accuracy.
	
	/**
	 * Load users and students and logout before each test
	 */
	@Before
	public void before() {
		_BILL = new BILL();
		
		// Load files
		try {
			_BILL.loadUsers(USERS_FILE);
		} catch (FileNotFoundException | NullPointerException e) {
			fail("Error loading users from " + USERS_FILE + ". " + e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			_BILL.loadRecords(STUDENTS_FILE);
		} catch (FileNotFoundException | NullPointerException e) {
			fail("Error loading student records from " + STUDENTS_FILE + ". " + e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		_BILL.logOut();
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_grad1() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("grad1");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.GRAD_RESIDENT_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.MATRICULATION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.HEALTH_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.GRAD_RESIDENT_17_HOURS_ABOVE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_COHORT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_grad2() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("grad2");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_GRAD_NONRESIDENT_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_GRAD_NONRESIDENT_ONLINE_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_GRAD__HEALTH_CENTER_9_TO_11_HOURS));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}	
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad1() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad1");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_RESIDENT_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		

	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad2() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad2");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.UG_NONRESIDENT_17_HOURS_ABOVE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.MATRICULATION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.HEALTH_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_COHORT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}	
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad3() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad3");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad4() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad4");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_ATHLETICS_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad5() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad5");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}	
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad6() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad6");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_GENERAL_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}	
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad7() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad7");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_UG_NONRESIDENT_SIMS_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.PT_TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad8() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad8");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.UG_NONRESIDENT_SIMS_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad9() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad9");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.UG_NONRESIDENT_GENERAL_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad10() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad10");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.UG_NONRESIDENT_WOODROW_DEPARTMENTAL_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalculateCurrentCharges_ugrad12() {
		// Attempt to get Bill.
		Bill bill = attemptGenerateBill("ugrad12");
		if (bill == null) fail();
		
		// Build list of expected charges for grad1
		ArrayList<String> expFeeNotes = new ArrayList<String>();
		expFeeNotes.add(Fee.getFeeNote(EnumFee.UG_NONRESIDENT_ATHLETICS_TUITION));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.TECHNOLOGY_FEE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_INSURANCE));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD_EXCHANGE_DEPOSIT));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.STUDY_ABROAD));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_SPONSORED));
		expFeeNotes.add(Fee.getFeeNote(EnumFee.INTERNATIONAL_ENROLLMENT));
		
		String missingCharges = containsCharges(expFeeNotes, bill.getTransactions());
		System.out.println(bill.toString());
		if (!missingCharges.equals("")) {
			fail("\nMissing the following charges:\n" + missingCharges);
		}
	}		
	
	
	private Bill attemptGenerateBill(String userID) {
		Bill retrievedBill = null;
		
		try {
			_BILL.logIn(userID);
			retrievedBill = _BILL.generateBill(userID);
		} catch (InvalidUserIdException e) {
			System.out.println("Error logging-in user '" + userID + ": " + e.getMessage());
		} catch(Exception e) {
			System.out.println("generateBill(\"" + userID + "\") has failed. " + e.getMessage());
		}
		
		return retrievedBill;
	}
	
	/**
	 * Tests Billing.calculateBalance with inputs that should yield a 0 balance.
	 */
	@Test
	public void testCalculateBalance_ZeroBalance() {
		Transaction[] trans = new Transaction[4];
		
		Transaction trans1 = new Transaction();
		trans1.setAmount(1000);
		trans1.setType("CHARGE");
		
		Transaction trans2 = new Transaction();
		trans2.setAmount(2000);
		trans2.setType("PAYMENT");
		
		Transaction trans3 = new Transaction();
		trans3.setAmount(2000);
		trans3.setType("CHARGE");
		
		Transaction trans4 = new Transaction();
		trans4.setAmount(1000);
		trans4.setType("PAYMENT");
		
		trans[0] = trans1;
		trans[1] = trans2;
		trans[2] = trans3;
		trans[3] = trans4;
		
		try {
			double bal = Billing.calculateBalance(trans);
			assertEquals(0, bal, DELTA);
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Tests Billing.calculateBalance with inputs that should yield a positive balance.
	 */
	@Test
	public void testCalculateBalance_PositiveBalance() {
		Transaction[] trans = new Transaction[3];
		
		Transaction trans1 = new Transaction();
		trans1.setAmount(1000);
		trans1.setType("CHARGE");
		
		Transaction trans2 = new Transaction();
		trans2.setAmount(2000);
		trans2.setType("CHARGE");
		
		Transaction trans3 = new Transaction();
		trans3.setAmount(1000);
		trans3.setType("PAYMENT");
		
		trans[0] = trans1;
		trans[1] = trans2;
		trans[2] = trans3;
		
		try {
			double bal = Billing.calculateBalance(trans);
			assertEquals("Expected balance: 2000. Actual balance: " + bal, 2000, bal, DELTA);
		} catch (Exception e) {
			fail();
		}
	}
	
	
	/**
	 * Tests Billing.calculateBalance with inputs that should yield a negative balance.
	 */	
	@Test
	public void testCalculateBalance_NegativeBalance() {
		Transaction[] trans = new Transaction[3];
		
		Transaction trans1 = new Transaction();
		trans1.setAmount(1000);
		trans1.setType("CHARGE");
		
		Transaction trans2 = new Transaction();
		trans2.setAmount(2000);
		trans2.setType("CHARGE");
		
		Transaction trans3 = new Transaction();
		trans3.setAmount(3000.50);
		trans3.setType("PAYMENT");
		
		trans[0] = trans1;
		trans[1] = trans2;
		trans[2] = trans3;
		
		try {
			double bal = Billing.calculateBalance(trans);
			assertEquals("Expected balance: -0.5. Actual balance: " + bal, -0.5, bal, DELTA);
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Tests Billing.calculateBalance with null values in the transaction array input
	 */		
	@Test
	public void testCalculateBalance_NullValue() {
		Transaction[] trans = new Transaction[1];
		try {
			double bal = Billing.calculateBalance(trans);
			assertEquals("Expected balance: 0.0. Actual balance: " + bal, 0.0, bal, DELTA);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}	
	
	/**
	 * Tests Billing.calculateBalance with a transaction array with both null and non-null values
	 */		
	@Test
	public void testCalculateBalance_NullThenNonnull() {
		Transaction[] trans = new Transaction[2];
		
		Transaction trans1 = new Transaction();
		trans1.setAmount(1000);
		trans1.setType("CHARGE");
		
		trans[1] = trans1;
		
		try {
			double bal = Billing.calculateBalance(trans);
			assertEquals("Expected balance: 1000. Actual balance: " + bal, 1000.0, bal, DELTA);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	private String containsCharges(ArrayList<String> expectedEnumFeeNotes, Transaction[] actualChrgs) {
		String missingCharges = "";
		ArrayList<String> actualChrgsFeeNotes = new ArrayList<String>();
		
		for (Transaction trans : actualChrgs) {
			actualChrgsFeeNotes.add(trans.getNote());
		}
		
		for (String expNote : expectedEnumFeeNotes) {
			if (!actualChrgsFeeNotes.contains(expNote)) { 
				missingCharges += "\n" + expNote;
			}
		}
		
		return missingCharges;
	}
}
