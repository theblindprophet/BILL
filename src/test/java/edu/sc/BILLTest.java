package test.java.edu.sc;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.Bill;


public class BILLTest {
	
	static BILL testerClass;
	
	@Before
    public void beforeClass() {
        System.out.println("@BeforeClass");
        testerClass = new BILL();
    }

	@Test
	public void testLoadUsers() {
		try {
			testerClass.loadUsers("users.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
	}
	
	@Test
	public void testLoadRecords() {
		try {
			testerClass.loadRecords("students.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
	}
	
	@After
    public void after() {
        System.out.println("@After");
    }

}
