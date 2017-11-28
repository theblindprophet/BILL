package test.java.edu.sc;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import java.util.List;

import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.Bill;
import main.java.edu.sc.csce740.model.StudentRecord;
import main.java.edu.sc.csce740.model.StudentDemographics;
import main.java.edu.sc.csce740.model.Term;
import main.java.edu.sc.csce740.model.Course;
import main.java.edu.sc.csce740.model.InvalidUserIdException;
import main.java.edu.sc.csce740.model.AdminRightsException;
import main.java.edu.sc.csce740.model.GetRecordException;
import main.java.edu.sc.csce740.model.EditRecordException;

public class BILLTest {
	
	static BILL testerClass;
	
	@Before
    public void beforeClass() {
        testerClass = new BILL();
        // Load files
        try {
			testerClass.loadUsers("users.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
        try {
			testerClass.loadRecords("students.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
    }
	
	@Test
	public void testLoginLogout() {
		// Logout
		testerClass.logOut();
		// Login
        try {
        		testerClass.logIn("mhunt");
        } catch(InvalidUserIdException e) {
        		fail("yeah, this did not work.");
        }
	}
	
	@Test
	public void testLoginInvalid() {
		// Logout
		testerClass.logOut();
		// Login
        try {
        		testerClass.logIn("apples");
        		fail("yeah, this did not work.");
        } catch(InvalidUserIdException e) {
        		
        } catch(Exception e) {
        	
        }
	}
	
	@Test
	public void testGetUser() {
		// Logout
		testerClass.logOut();
		// Login
        try {
        		testerClass.logIn("mhunt");
        } catch(InvalidUserIdException e) {
        		fail("yeah, this did not work.");
        }
		String id = testerClass.getUser();
		System.out.println(id);
		assertEquals("mhunt", id);
	}
	
	@Test
	public void testGetStudentIDsAdmin() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			List<String> studentIDs = testerClass.getStudentIDs();
			assertEquals("ggay", studentIDs.get(0));
		} catch(AdminRightsException e) {
			fail("yeah, this did not work.");
		} catch(Exception e) {
			fail("yeah, this did not work.");
		}
	}
	
	@Test
	public void testGetStudentIDsNotAdmin() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			testerClass.getStudentIDs();
			fail("yeah, this did not work.");
		} catch(AdminRightsException e) {
			
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGetRecord() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			StudentRecord testStudent = testerClass.getRecord("ggay");
			assertEquals("Gregory", testStudent.getStudent().getFirstname());
			assertEquals("ENGINEERING_AND_COMPUTING", testStudent.getCollege());
			assertEquals(2017, testStudent.getTermBegan().getYear());
		} catch(GetRecordException e) {
			fail("yeah, this did not work.");
		} catch(Exception e) {
			fail("yeah, this did not work.");
		}
	}
	
	@Test
	public void testGetRecordIsNotAdmin() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("mhunt");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			testerClass.getRecord("ggay");
			fail("yeah, this did not work.");
		} catch(GetRecordException e) {
			
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGetRecordNoPermissionForStudent() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			testerClass.getRecord("mhunt");
			fail("yeah, this did not work.");
		} catch(GetRecordException e) {
			
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testEditRecordNotPermnanent() {
		// Logout
		testerClass.logOut();
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			fail(e.getMessage());
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
			course1.setID("CSCE");
			course1.setNumCredits(3);
			testStudentCourses[0] = course1;
			
			testStudent.setStudent(testStudentStudent);
			testStudent.setTermBegan(testStudentTermBegan);
			testStudent.setCourses(testStudentCourses);
			testerClass.editRecord("ggay", testStudent, false);
		} catch(Exception e) {
			fail(e.getMessage());
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
		} catch(GetRecordException e) {
			fail(e.getMessage());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGenerateBill() {
		System.out.println("\n\n\n************\nIn testGenerateBill\n************");
		
		try {
			testerClass.logIn("ggay");
			testerClass.applyPayment("ggay", 5000, "Titties");
			Gson gson = new Gson();
			
			String json = gson.toJson(testerClass.generateBill("ggay"));
			System.out.println(json);
		} catch (Exception e) {
			fail("testGenerateBill failed: " + e.getMessage());
		}
		
		System.out.println("************\nEnd testGenerateBill\n************\n");
	}
	
	@After
    public void after() {
    }

}
