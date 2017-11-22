package test.java.edu.sc.csce740;

import main.java.edu.sc.csce740.BILL;

public class BILLTestMain {

	public BILLTestMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		BILL apples = new BILL();
		try {
			apples.loadRecords("students.txt");
			System.out.println("Success!");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
