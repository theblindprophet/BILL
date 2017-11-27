package main.java.edu.sc.csce740.model;


public class AVPS 
{

	public static boolean hasPermission(User requestor, User requestee, Action action)
	{
		
		switch(action)
		{
			case LogIn:
				return hasPermission_LogIn(requestor);
			case LogOut:
				return hasPermission_LogOut(requestor);
			case GetStudentIds:
				return hasPermission_GetStudentId(requestor, requestee);
			case GetRecord:
				return hasPermission_GetRecord(requestor, requestee);
			case EditRecord:
				return hasPermission_EditRecord(requestor, requestee);
			case GenerateBill:
				return hasPermission_GenerateBill(requestor, requestee);
			case ViewCharges:
				return hasPermission_ViewCharges(requestor, requestee);
			case ApplyPayment:
				return hasPermission_ApplyPayment(requestor, requestee);
			default:
				return false;
		}
		
	}

	private static boolean hasPermission_LogIn(User requestor) {
		if(requestor == null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_LogOut(User requestor) {
		if(requestor != null)
		{
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasPermission_ApplyPayment(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_ViewCharges(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GenerateBill(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_EditRecord(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN"))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetRecord(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && (requestor.getCollege().equals(requestee.getCollege()) && requestor.getRole().equals("ADMIN")) || requestor.getId().equals(requestee.getId()))
		{
			return true;
		}else{
			return false;
		}
	}

	private static boolean hasPermission_GetStudentId(User requestor, User requestee) 
	{
		if(requestor != null && requestee != null && requestor.getRole().equals("ADMIN") && requestor.getCollege().equals(requestee.getCollege()))
		{
			return true;
		}else{
			return false;
		}
	}
}