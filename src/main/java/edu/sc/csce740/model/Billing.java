package main.java.edu.sc.csce740.model;

public class Billing {
	
	
	
	public static double calculateBalance(Transaction[] transactions)
	{
		double total = 0;
		for(int i = 0; i < transactions.length; i++)
		{
			if(transactions[i].getType().equals("PAYMENT"))
			{
				total += transactions[i].getAmount();
			}else{
				total -= transactions[i].getAmount();
			}
		}
		
		return total;
	}

}
