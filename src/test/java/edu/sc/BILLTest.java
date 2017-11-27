package test.java.edu.sc;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.Bill;
import main.java.edu.sc.csce740.model.StudentRecord;
import main.java.edu.sc.csce740.model.InvalidUserIdException;
import main.java.edu.sc.csce740.model.AdminRightsException;
import main.java.edu.sc.csce740.model.GetRecordException;
import main.java.edu.sc.csce740.model.EditRecordException;

public class BILLTest {
	
	static BILL testerClass;
	
	@Before
    public void beforeClass() {
        System.out.println("@BeforeClass");
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
		StudentRecord testStudent;
		try {
			testStudent = testerClass.getRecord("ggay");
			testStudent.getStudent().setFirstname("Apples");
			testStudent.getStudent().setPhone("222-222-2222");
			testStudent.getStudent().setAddressState("NC");
			testStudent.getTermBegan().setYear(2016);
			testStudent.setScholarship("SIMS");
			testStudent.getCourses()[1].setName("Apples Apples Apples");
			testStudent.setInternationalStatus("SPONSORED");
			testerClass.editRecord("ggay", testStudent, false);
		} catch(GetRecordException e) {
			fail(e.getMessage());
		} catch(Exception e) {
			fail(e.getMessage());
		}
		
		try {
			testStudent = testerClass.getRecord("ggay");
			assertEquals("Apples", testStudent.getStudent().getFirstname());
			assertEquals("222-222-2222", testStudent.getStudent().getPhone());
			assertEquals("NC", testStudent.getStudent().getAddressState());
			assertEquals(2016, testStudent.getTermBegan().getYear());
			assertEquals("SIMS", testStudent.getScholarship());
			assertEquals("Apples Apples Apples", testStudent.getCourses()[1].getName());
			assertEquals("SPONSORED", testStudent.getInternationalStatus());
		} catch(GetRecordException e) {
			fail(e.getMessage());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@After
    public void after() {
        System.out.println("@After");
    }

}
