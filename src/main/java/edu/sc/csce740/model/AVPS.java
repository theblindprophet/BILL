package main.java.edu.sc.csce740.model;


public class AVPS 
{
	

	public static boolean hasPermission(User requestee, Action action)
	{
		
		switch(action)
		{
			case LogOut:
				return hasPermission_LogOut(requestee);
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

	public static boolean hasPermission_LogIn() {
		if(DHCS.getCurrentUser() == null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_LogOut(User requestee) {
		if(DHCS.getCurrentUser() != null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_ApplyPayment(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_ViewCharges(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GenerateBill(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_EditRecord(User requestee) 
	{
		if(DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetRecord(User requestee) 
	{
		if((DHCS.getCurrentUser().getCollege().equals(requestee.getCollege()) && DHCS.getCurrentUser().getRole().equals("ADMIN")) || DHCS.getCurrentUser().getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetStudentIds(User requestee) 
	{
		if(DHCS.getCurrentUser().getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}
	
	
}