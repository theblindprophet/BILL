package test.java.edu.sc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.StudentRecord;
import main.java.edu.sc.csce740.model.StudentDemographics;
import main.java.edu.sc.csce740.model.Term;
import main.java.edu.sc.csce740.model.Course;
import main.java.edu.sc.csce740.model.InvalidUserIdException;
import main.java.edu.sc.csce740.model.AdminRightsException;
import main.java.edu.sc.csce740.model.Bill;
import main.java.edu.sc.csce740.model.GetRecordException;
import main.java.edu.sc.csce740.model.EditRecordException;
import main.java.edu.sc.csce740.model.InvalidPaymentException;

public class BILLTest {

	static BILL testerClass;

	/**
	 * Load users and students and logout before each test
	 */
	@Before
	public void before() {
		testerClass = new BILL();
		// Load files
		try {
			testerClass.loadUsers("src/test/resources/users.txt");
		} catch (FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
		try {
			testerClass.loadRecords("src/test/resources/students.txt");
		} catch (FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
		// Logout
		testerClass.logOut();
	}

	/**
	 * Test the users ability to log out
	 * 
	 * @result Account will be logged out without any errors
	 */
	@Test
	public void testLoginLogout() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
	}

	/**
	 * Test that log in will throw an exception for invalid user id
	 * 
	 * @result exception thrown
	 */
	@Test
	public void testLoginInvalid() {
		// Login
		try {
			testerClass.logIn("apples");
			fail("Fail: We should have not got to this point.");
		} catch (InvalidUserIdException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Test that log get user will work with no errors
	 * 
	 * @result user is returned correctly
	 */
	@Test
	public void testGetUser() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		String id = testerClass.getUser();
		assertEquals("mhunt", id);
	}

	/**
	 * Test that an admin can obtain correct user ids
	 * 
	 * @result correct student ids are returned
	 */
	@Test
	public void testGetStudentIDsAdmin() {
		// Login
		try {
			testerClass.logIn("rbob");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			List<String> studentIDs = testerClass.getStudentIDs();
			assertEquals("ggay", studentIDs.get(0));
		} catch (AdminRightsException e) {
			fail("Fail: AdminRightsException.");
		} catch (Exception e) {
			fail("Fail: Exception.");
		}
	}

	/**
	 * Test that a user that is not an admin cannot obtain a list of users
	 * 
	 * @result exception is thrown because user is not an admin
	 */
	@Test
	public void testGetStudentIDsNotAdmin() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.getStudentIDs();
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Test that a user with correct permissions can obtain a student record
	 * 
	 * @result student record is obtained with no errors
	 */
	@Test
	public void testGetRecord() {
		// Login
		try {
			testerClass.logIn("rbob");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			StudentRecord testStudent = testerClass.getRecord("ggay");
			assertEquals("Apples", testStudent.getStudent().getFirstname());
			assertEquals("ENGINEERING_AND_COMPUTING", testStudent.getCollege());
			assertEquals(2016, testStudent.getTermBegan().getYear());
		} catch (GetRecordException e) {
			fail("Fail: GetRecordException.");
		} catch (Exception e) {
			fail("Fail: Exception.");
		}
	}

	/**
	 * Test that a user without correct permissions cannot obtain a student
	 * record
	 * 
	 * @result exception is thrown because user is attempting to access another
	 *         users student record
	 */
	@Test
	public void testGetRecordNoPermissionStudent() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.getRecord("ggay");
			fail("Fail: We should have not got to this point.");
		} catch (GetRecordException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Test that a user without correct permissions cannot obtain a student
	 * record
	 * 
	 * @result exception is thrown because user is attempting to access another
	 *         users student record
	 */
	@Test
	public void testGetRecordNoPermissionAdmin() {
		// Login
		try {
			testerClass.logIn("rbob");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.getRecord("mhunt");
			fail("Fail: We should have not got to this point.");
		} catch (GetRecordException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Test that a record can be edited without it writing to file
	 * 
	 * @result student record written in memory but not to file
	 */
	@Test
	public void testEditRecordNotPermnanent() {
		this.testEditRecord(false, "rbob", false);
	}

	/**
	 * Test that a record can be edited and written to file
	 * 
	 * @result student record written in memory and also to file
	 */
	@Test
	public void testEditRecordPermnanent() {
		this.testEditRecord(true, "rbob", false);
	}

	/**
	 * Test that a record cannot be edited with invalid permissions
	 * 
	 * @result student record is not edited
	 */
	@Test
	public void testEditRecordInvalidPermission() {
		this.testEditRecord(false, "jgross", true);
	}

	/**
	 * Test that a record is updated with correct values
	 * 
	 * @result student record is updated with correct values
	 */
	@Test
	public void testEditRecordInvalidValues() {
		this.testEditRecordInvalid("state", "Edit Record error: Not valid address state");
		testerClass.logOut();
		this.testEditRecordInvalid("phone", "Edit Record error: phone does not match correct pattern");
		testerClass.logOut();
		this.testEditRecordInvalid("scholarship", "Edit Record error: Not valid scholarship");
	}

	/**
	 * This function is used for testing student record in various ways
	 * depending on the parameters Edit record
	 * 
	 * @param permanent
	 *            To write or not write to file
	 * @param user
	 *            User to edit record of
	 * @param shouldFail
	 *            If this test should fail
	 */
	public void testEditRecord(boolean permanent, String user, boolean shouldFail) {
		// Login
		try {
			testerClass.logIn(user);
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		StudentRecord testStudent = new StudentRecord();
		StudentDemographics testStudentStudent = new StudentDemographics();
		Term testStudentTermBegan = new Term();
		Course[] testStudentCourses = new Course[1];
		try {
			testStudentStudent.setId("ggay");
			testStudentStudent.setFirstname("Apples");
			testStudentStudent.setLastname("Limes");
			testStudentStudent.setEmailAddress("jamie@jamie.com");
			testStudentStudent.setAddressStreet("111 Main St");
			testStudentStudent.setAddressCity("Columbia");
			testStudentStudent.setAddressState("NC");
			testStudentStudent.setAddressPostalCode("29201");
			testStudentStudent.setPhone("222-222-2222");
			testStudent.setCollege("ENGINEERING_AND_COMPUTING");
			testStudentTermBegan.setSemester("FALL");
			testStudentTermBegan.setYear(2016);
			testStudent.setClassStatus("JUNIOR");
			testStudent.setStudyAbroad("NONE");
			testStudent.setInternationalStatus("NONE");
			testStudent.setScholarship("SIMS");
			testStudent.setInternationalStatus("SPONSORED");
			Course course1 = new Course();
			course1.setName("Apples Apples Apples");
			course1.setId("CSCE");
			course1.setNumCredits(3);
			testStudentCourses[0] = course1;

			testStudent.setStudent(testStudentStudent);
			testStudent.setTermBegan(testStudentTermBegan);
			testStudent.setCourses(testStudentCourses);
			testerClass.editRecord("ggay", testStudent, permanent);
			if (shouldFail) {
				fail("Fail: We should have not got to this point.");
			}
		} catch (AdminRightsException e) {
			if (!shouldFail) {
				fail("Fail: AdminRightsException.");
			}
		} catch (Exception e) {
			if (!shouldFail) {
				fail("Fail: Exception.");
			}
		}

		try {
			StudentRecord retrievedStudent = testerClass.getRecord("ggay");
			assertEquals("Apples", retrievedStudent.getStudent().getFirstname());
			assertEquals("222-222-2222", retrievedStudent.getStudent().getPhone());
			assertEquals("NC", retrievedStudent.getStudent().getAddressState());
			assertEquals(2016, retrievedStudent.getTermBegan().getYear());
			assertEquals("SIMS", retrievedStudent.getScholarship());
			assertEquals("Apples Apples Apples", retrievedStudent.getCourses()[0].getName());
			assertEquals("SPONSORED", retrievedStudent.getInternationalStatus());
		} catch (GetRecordException e) {
			if (!shouldFail) {
				fail("Fail: GetRecordException.");
			}
		} catch (Exception e) {
			if (!shouldFail) {
				fail("Fail: Exception.");
			}
		}
	}

	/**
	 * Edit record with invalid value
	 * 
	 * @param value
	 *            Field to edit
	 * @param error
	 *            The error it should throw
	 */
	public void testEditRecordInvalid(String value, String error) {
		// Login
		try {
			testerClass.logIn("rbob");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		StudentRecord testStudent = new StudentRecord();
		StudentDemographics testStudentStudent = new StudentDemographics();
		Term testStudentTermBegan = new Term();
		Course[] testStudentCourses = new Course[1];
		try {
			testStudentStudent.setId("ggay");
			testStudentStudent.setFirstname("Apples");
			testStudentStudent.setLastname("Limes");
			testStudentStudent.setEmailAddress("jamie@jamie.com");
			testStudentStudent.setAddressStreet("111 Main St");
			testStudentStudent.setAddressCity("Columbia");
			if (value == "state") {
				testStudentStudent.setAddressState("OP");
			} else {
				testStudentStudent.setAddressState("NC");
			}
			testStudentStudent.setAddressPostalCode("29201");
			if (value == "phone") {
				testStudentStudent.setPhone("222-2223-2222");
			} else {
				testStudentStudent.setPhone("222-222-2222");
			}
			testStudent.setCollege("ENGINEERING_AND_COMPUTING");
			testStudentTermBegan.setSemester("FALL");
			testStudentTermBegan.setYear(2016);
			testStudent.setClassStatus("JUNIOR");
			testStudent.setStudyAbroad("NONE");
			testStudent.setInternationalStatus("NONE");
			if (value == "scholarship") {
				testStudent.setScholarship("SIMS!");
			} else {
				testStudent.setScholarship("SIMS");
			}
			testStudent.setInternationalStatus("SPONSORED");
			Course course1 = new Course();
			course1.setName("Apples Apples Apples");
			course1.setId("CSCE");
			course1.setNumCredits(3);
			testStudentCourses[0] = course1;

			testStudent.setStudent(testStudentStudent);
			testStudent.setTermBegan(testStudentTermBegan);
			testStudent.setCourses(testStudentCourses);
			testerClass.editRecord("ggay", testStudent, false);
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {
			fail("Fail: AdminRightsException.");
		} catch (EditRecordException e) {
			assertEquals(error, e.getMessage());
		} catch (Exception e) {
			fail("Fail: Exception.");
		}
	}

	/**
	 * Apply a new payment to a user and generate the bill that logged in user
	 * has permission for
	 */
	@Test
	public void testApplyPaymentGenerateBill() {
		// Login
		try {
			testerClass.logIn("ggay");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.applyPayment("ggay", 5000, "Limes");
		} catch (AdminRightsException e) {
			fail("Fail: We should have not got to this point.");
		} catch (Exception e) {
			fail("Fail: We should have not got to this point.");
		}
		try {
			Gson gson = new Gson();
			String json = gson.toJson(testerClass.generateBill("ggay"));
			File file = new File("src/test/resources/bill.txt");
			if (file.exists()) {
				file.delete();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {
			fail("Fail: We should have not got to this point.");
		} catch (Exception e) {
			fail("Fail: We should have not got to this point.");
		}
	}

	/**
	 * Apply a payment to user that logged in user has no permission for
	 */
	@Test
	public void testApplyPaymentNoPermission() {
		// Login
		try {
			testerClass.logIn("ggay");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.applyPayment("jgross", 5000, "Apples");
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Apply invalid payment amount
	 */
	@Test
	public void testApplyPaymentInvalid() {
		// Login
		try {
			testerClass.logIn("ggay");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.applyPayment("jgross", -5000, "Apples");
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {

		} catch (InvalidPaymentException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * Generate bill for user that logged in user does not have permission for
	 */
	@Test
	public void testGenerateBillNoPermission() {
		// Login
		try {
			testerClass.logIn("ggay");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			Gson gson = new Gson();
			String json = gson.toJson(testerClass.generateBill("mhunt"));
			File file = new File("src/test/resources/bill.txt");
			if (file.exists()) {
				file.delete();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
			fail("Fail: We should have not got to this point.");
		} catch (IOException e) {

		} catch (AdminRightsException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * View charges for user that does have permission
	 */
	@Test
	public void testViewCharges() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			Bill charges = testerClass.viewCharges("mhunt", 10, 16, 2016, 5, 1, 2018);
			assertEquals(-2000, charges.getBalance(), 0.01);
			assertEquals("PHD", charges.getClassStatus());
			assertEquals(2000, charges.getTransactions()[0].getAmount(), 0.01);
		} catch (AdminRightsException e) {
			fail("Fail: AdminRightsException.");
		} catch (Exception e) {
			fail("Fail: Exception.");
		}
	}

	/**
	 * View charges for user that logged in student user does not have
	 * permission for
	 */
	@Test
	public void testViewChargesNoPermissionForStudent() {
		// Login
		try {
			testerClass.logIn("ggay");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		try {
			testerClass.viewCharges("mhunt", 10, 16, 2016, 5, 1, 2018);
			fail("Fail: We should have not got to this point.");
		} catch (AdminRightsException e) {

		} catch (Exception e) {

		}
	}
	
	@Test
	public void testGenerateBill() {
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch (InvalidUserIdException e) {
			fail("Fail: InvalidUserIdException.");
		}
		
		try {
			testerClass.generateBill("mhunt");
		} catch(Exception e) {
			fail("Fail: mhunt should be able to generate bill for mhunt.");
		}
	}

	@After
	public void after() {
	}

}
