import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MessageClient {
	
	public static void main(String [] args) 
	{
		if (args.length < 1) 
		{ 
			System.out.println("Usage: java MessageClient <server host>"); 
			return; 
		}
		try 
		{
			 //if (System.getSecurityManager() == null) { System.setSecurityManager(new SecurityManager()); }
			
			String host = args[0];
			Client c = new Client("text", host);
			c.setVisible(true);
			System.out.println("Client ready");		
			
			/***********************************************/
				
		} catch (Exception e) {
			System.err.println("Error on client: " + e); e.printStackTrace(); return;
		}
	}

} 