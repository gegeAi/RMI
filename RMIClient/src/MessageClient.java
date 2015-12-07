import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
			Registry registry = LocateRegistry.getRegistry(host);
			Text texte = (Text) registry.lookup("text");
			
			System.out.println("Client ready");
			
			String[] histo = texte.getHistorique();
			for(int i=0; i<histo.length; i++)
			{
				if(!histo[i].equals(""))
				{
					System.out.println(histo[i]);
				}
				
			}
			
	
		} catch (Exception e) {
			System.err.println("Error on client: " + e); e.printStackTrace(); return;
		}
	}
} 