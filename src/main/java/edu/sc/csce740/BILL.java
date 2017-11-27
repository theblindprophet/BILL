package main.java.edu.sc.csce740;

import java.util.ArrayList;

/**
 * BILL
 * Implementation for the BILL top-level API.
 * Authors: Jamie Gross
 * Last Modified: 11/14/2017
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * - Neither the name of the University of Minnesota nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.List;

import main.java.edu.sc.csce740.model.AVPS;
import main.java.edu.sc.csce740.model.Action;
import main.java.edu.sc.csce740.model.AdminRightsException;
import main.java.edu.sc.csce740.model.Bill;
import main.java.edu.sc.csce740.model.Billing;
import main.java.edu.sc.csce740.model.DHCS;
import main.java.edu.sc.csce740.model.GetRecordException;
import main.java.edu.sc.csce740.model.InvalidUserIdException;
import main.java.edu.sc.csce740.model.StudentRecord;
import main.java.edu.sc.csce740.model.Transaction;
import main.java.edu.sc.csce740.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * Your implementation of this interface must be named BILL
 */
public class BILL implements BILLIntf {
	
	// Global variables
	private DHCS _DHCS= new DHCS();

    /**
     * Loads the list of system usernames and permissions.
     * @param usersFile the filename of the users file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
    public void loadUsers(String usersFile) throws FileNotFoundException, NullPointerException {
    	ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(usersFile).getFile());
			_DHCS.setUsers(new Gson().fromJson(
	    			new FileReader(file), new TypeToken<List<User>>(){}.getType()
		    		)) ;
    		
    }

    /**
     * Loads the list of system transcripts.
     * @param recordsFile the filename of the transcripts file.
     * @throws Exception for I/O errors.  SEE NOTE IN CLASS HEADER.
     */
    public void loadRecords(String recordsFile) throws FileNotFoundException, NullPointerException {
    		ClassLoader classLoader = getClass().getClassLoader();
    		File file = new File(classLoader.getResource(recordsFile).getFile());
        	_DHCS.setStudentRecords(new Gson().fromJson(
        			new FileReader(file), new TypeToken<List<StudentRecord>>(){}.getType()
        		));  
        		
    }

    /**
     * Sets the user id of the user currently using the system.
     * @param userId  the id of the user to log in.
     * @throws Exception  if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
    public void logIn(String userId) throws InvalidUserIdException {
    	Action logInAction = Action.LogIn;
    	
    	try{
    		User newUser = _DHCS.getUser(userId);
    		
	    	if(AVPS.hasPermission(_DHCS.getCurrentUser(), null, logInAction) && newUser != null)
	    	{
	        	_DHCS.setCurrentUser(newUser);
	    	}else{
	    		throw new InvalidUserIdException();
	    	}
    	}
    	catch(InvalidUserIdException e)
    	{
    		System.out.println(userId + " , is not a valid username");
    	}
    	catch(Exception e) {
    		System.out.println("Exception in logIn: " + e.getMessage());
    	}
    }

    /**
     * Closes the current session, logs the user out, and clears any session data.
     * @throws Exception  if the user id is invalid.  SEE NOTE IN CLASS HEADER.
     */
    public void logOut(){
    	Action logOutAction = Action.LogOut;

    	try{
	    	if(AVPS.hasPermission(_DHCS.getCurrentUser(), null, logOutAction))
	    	{
	    		_DHCS.setCurrentUser(null);
	    	}else{
	    		throw new InvalidUserIdException();
	    	}
    	}
    	catch(InvalidUserIdException e)
    	{
    		System.out.println("No user logged in");
    	}
    	catch(Exception e) {
    		System.out.println("Exception in logOut: " + e.getMessage());
    	}
    }

    /**
     * Gets the user id of the user currently using the system.
     * @return  the user id of the user currently using the system.
     */
    public String getUser() {
		return _DHCS.getCurrentUser().getId();
    }

    /**
     * Gets a list of the userIds of the students that an admin can view.
     * @return a list containing the userId of for each student in the
     *      college belonging to the current user
     * @throws Exception is the current user is not an admin.
     */
    public List<String> getStudentIDs() throws AdminRightsException, Exception {		
    		Action getStudentIdsAction = Action.GetStudentIds;
        	try{
        		User currUser = _DHCS.getCurrentUser();
        		
        		if (currUser != null && currUser.getRole().equals("ADMIN")) {
    	    		ArrayList<User> userList = _DHCS.getUsers();
    	    		ArrayList<String> userIdList = new ArrayList<String>();
    	    		for(User aUser : userList)
    	    		{
    	    			if(AVPS.hasPermission(currUser, aUser, getStudentIdsAction))
    	    			{
    	    				userIdList.add(aUser.getId());
    	    			}
    	    		}
    	        	return userIdList;
        		} else {
    	    		throw new AdminRightsException();
        		}
        	}
        	catch(AdminRightsException e)
        	{
        		System.out.println("User is not an admin");
        	}
        	catch(Exception e) {
        		System.out.println("Exception in getStudentIDs: " + e.getMessage());
        	}
        	
        	return null;
    }

    /**
     * Gets the raw student record data for a given userId.
     * @param userId  the identifier of the student.
     * @return the student record data.
     * @throws Exception if the form data could not be retrieved. SEE NOTE IN
     *      CLASS HEADER.
     */
    public StudentRecord getRecord(String userId) throws Exception {
    	Action getRecordAction = Action.GetRecord;
    	
    	try{
	    	if(AVPS.hasPermission(_DHCS.getCurrentUser(), _DHCS.getUser(userId), getRecordAction))
	    	{
	    		return _DHCS.getRecord(userId);
	    	}else{
	    		throw new GetRecordException();
	    	}
    	}
    	catch(GetRecordException e)
    	{
    		System.out.println("User does not have correct privledges to get record");
    	}
    	catch(Exception e) {
    		System.out.println("Exception in getRecord: " + e.getMessage());
    	}
    	
    	return null;
    }

    /**
     * Saves a new set of student data to the records data.
     * @param userId the student ID to overwrite.
     * @param transcript  the new student record
     * @param permanent  a status flag indicating whether (if false) to make a
     * temporary edit to the in-memory structure or (if true) a permanent edit.
     * @throws Exception if the transcript data could not be saved or failed
     * a validity check.  SEE NOTE IN CLASS HEADER.
     */
    public void editRecord(String userId, StudentRecord record, Boolean permanent)
            throws Exception {
    	Action editRecordAction = Action.EditRecord;
    	
    	try{
	    	if(AVPS.hasPermission(_DHCS.getCurrentUser(), _DHCS.getUser(userId), editRecordAction))
	    	{
	    		_DHCS.writeRecord(userId, record, permanent);
	    	}else{
	    		throw new AdminRightsException();
	    	}
    	}
    	catch(AdminRightsException e)
    	{
    		System.out.println("User is not a valid Admin for this student");
    	}
    	catch(Exception e) {
    		System.out.println("Exception in getRecord: " + e.getMessage());
    	}
    }

    /**
     * Generates current bill.
     * @param userId the student to generate the bill for.
     * @returns the student's bill in a data class matching the I/O file.
     * @throws Exception  if the bill could not be generated.
     * SEE NOTE IN CLASS HEADER.
     */
    public Bill generateBill(String userId) throws Exception {
    		Bill bill = new Bill();
    		return bill;
    }

    /**
     * Generates a list of transactions for a chosen period.
     * @param userId the student to generate the list for.
     * @param startMonth the month of the start date.
     * @param startDay the day of the start date.
     * @param startYear the year of the start date.
     * @param endMonth the month of the end date.
     * @param endDay the day of the end date.
     * @param endYear the year of the end date.
     * @returns the student's bill in a data class matching the I/O file.
     * @throws Exception  if the bill could not be generated.
     * SEE NOTE IN CLASS HEADER.
     */
    public Bill viewCharges(String userId, int startMonth, int startDay, int startYear,
                            int endMonth, int endDay, int endYear) throws Exception {
		
		Action viewChargesAction = Action.ViewCharges;
    	try{
	    	if(AVPS.hasPermission(_DHCS.getCurrentUser(), _DHCS.getUser(userId), viewChargesAction))
	    	{
	    		StudentRecord record = _DHCS.getRecord(userId);
	    		Transaction[] transArray = _DHCS.getCharges(userId, startMonth, startDay, startYear, endMonth, endDay, endYear);
	        	Bill charges = new Bill(record.getStudent(), record.getCollege(), record.getClassStatus(), Billing.calculateBalance(transArray), transArray);
	    
	    		return charges;
	    	}else{
	    		throw new AdminRightsException();
	    	}
    	}
    	catch(AdminRightsException e)
    	{
    		System.out.println("User is not a valid Admin for this student");
    		return null;
    	}
    }

    /**
     * Makes a payment for the student
     * @param userId  the student to make a payment for.
     * @param amount  amount to apply to the balance.
     * @param note  a string indicating the reason for the payment
     * @throws Exception if the payment fails a validity check
     * or fails to save to file.
     * SEE NOTE IN CLASS HEADER.
     */
    public void applyPayment(String userId, double amount, String note)
            throws Exception {
    	
    }

}
