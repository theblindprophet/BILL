package test.java.edu.sc.csce740;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import main.java.edu.sc.csce740.BILL;
import main.java.edu.sc.csce740.model.Bill;

public class BILLTest {
	
	static BILL testerClass;
	
	@BeforeAll
    public static void beforeClass() {
        System.out.println("@BeforeClass");
        testerClass = new BILL();
    }

	@Test
	void testLoadUsers() {
		try {
			testerClass.loadUsers("users.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
	}
	
	@Test
	void testLoadRecords() {
		try {
			testerClass.loadRecords("students.txt");
		} catch(FileNotFoundException | NullPointerException e) {
			fail("yeah, this did not work.");
		}
	}
	
	@AfterAll
    public static void after() {
        System.out.println("@After");
    }

}
