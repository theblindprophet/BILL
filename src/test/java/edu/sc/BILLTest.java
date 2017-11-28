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
    public void before() {
        testerClass = new BILL();
        // Load files
        try {
			testerClass.loadUsers("src/test/resources/users.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
        try {
			testerClass.loadRecords("src/test/resources/students.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
        // Logout
     	testerClass.logOut();
    }
	
	@Test
	public void testLoginLogout() {
		// Login
        try {
        		testerClass.logIn("mhunt");
        } catch(InvalidUserIdException e) {
        		fail("yeah, this did not work.");
        }
	}
	
	@Test
	public void testLoginInvalid() {
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
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			fail("yeah, this did not work.");
		}
		try {
			StudentRecord testStudent = testerClass.getRecord("ggay");
			assertEquals("Apples", testStudent.getStudent().getFirstname());
			assertEquals("ENGINEERING_AND_COMPUTING", testStudent.getCollege());
			assertEquals(2016, testStudent.getTermBegan().getYear());
		} catch(GetRecordException e) {
			fail("yeah, this did not work.");
		} catch(Exception e) {
			fail("yeah, this did not work.");
		}
	}
	
	@Test
	public void testGetRecordIsNotAdmin() {
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
		this.testEditRecord(false, "rbob", false);
	}
	@Test
	public void testEditRecordPermnanent() {
		this.testEditRecord(true, "rbob", false);
	}
	@Test
	public void testEditRecordInvalidPermission() {
		this.testEditRecord(false, "jgross", true);
	}
	@Test
	public void testEditRecordInvalidValues() {
		this.testEditRecordInvalid("state", "Edit Record error: Not valid address state");
		testerClass.logOut();
		this.testEditRecordInvalid("phone", "Edit Record error: phone does not match correct pattern");
		testerClass.logOut();
		this.testEditRecordInvalid("scholarship", "Edit Record error: Not valid scholarship");
	}
	
	public void testEditRecord(boolean permanent, String user, boolean shouldFail) {
		// Login
		try {
			testerClass.logIn(user);
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
			testerClass.editRecord("ggay", testStudent, permanent);
			if(shouldFail) {
				fail("yeah, this did not work.");
			}
		} catch(AdminRightsException e) {
			if(!shouldFail) {
				fail(e.getMessage());
			}
		} catch(Exception e) {
			if(!shouldFail) {
				fail(e.getMessage());
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
		} catch(GetRecordException e) {
			if(!shouldFail) {
				fail(e.getMessage());
			}
		} catch(Exception e) {
			if(!shouldFail) {
				fail(e.getMessage());
			}
		}
	}
	
	public void testEditRecordInvalid(String value, String error) {
		// Login
		try {
			testerClass.logIn("rbob");
		} catch(InvalidUserIdException e) {
			e.printStackTrace();
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
			if(value == "state") {
				testStudentStudent.setAddressState("OP");
			} else {
				testStudentStudent.setAddressState("NC");
			}
			testStudentStudent.setAddressPostalCode("29201");
			if(value == "phone") {
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
			if(value == "scholarship") {
				testStudent.setScholarship("SIMS!");
			} else {
				testStudent.setScholarship("SIMS");
			}
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
			fail("yeah, this did not work.");
		} catch(AdminRightsException e) {
			
		} catch(EditRecordException e) {
			assertEquals(error, e.getMessage());
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGenerateBill() {
		try {
			testerClass.logIn("ggay");
			testerClass.applyPayment("ggay", 5000, "Titties");
			Gson gson = new Gson();
			
			String json = gson.toJson(testerClass.generateBill("ggay"));
		} catch (Exception e) {
			fail("testGenerateBill failed: " + e.getMessage());
		}
	}
	
	@After
    public void after() {
    }

}
