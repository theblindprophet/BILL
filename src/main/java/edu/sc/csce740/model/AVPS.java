package main.java.edu.sc.csce740.model;


public class AVPS 
{
	
	
	public AVPS()
	{
		
	}
	
	public User logIn(String userId)
	{
		DHCS controller = new DHCS();
		User loggedInUser = controller.getUser(userId);
		DHCS.setCurrentUser(loggedInUser);
		return loggedInUser;
	}
	
	public void logOut()
	{
		DHCS.setCurrentUser(null);
	}
	
	public boolean hasPermission(User requestee, Action action)
	{
		
		switch(action)
		{
			case GetStudentIds:
				return hasPermission_GetStudentIds(requestee);
			case GetRecord:
				return hasPermission_GetRecord(requestee);
			case EditRecord:
				return hasPermission_EditRecord(requestee);
			case GenerateBill:
				return hasPermission_GenerateBill(requestee);
			case ViewCharges:
				return hasPermission_ViewCharges(requestee);
			case ApplyPayment:
				return hasPermission_ApplyPayment(requestee);
		}
		return false;
	}

	private boolean hasPermission_ApplyPayment(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean hasPermission_ViewCharges(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean hasPermission_GenerateBill(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean hasPermission_EditRecord(User requestee) 
	{
		if(DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean hasPermission_GetRecord(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private boolean hasPermission_GetStudentIds(User requestee) 
	{
		if(DHCS.getCurrentUser().getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}
	
	
}