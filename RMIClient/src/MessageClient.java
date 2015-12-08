import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MessageClient {
	
	public static Scanner sc = new Scanner(System.in);

	static public class ThreadWrite extends Thread{

		public void run()
		{
			while (true)
			{
				renvoi = sc.nextLine().trim();
			}
		}
		
		private String renvoi = "";
		
		public String getRenvoi()
		{
			String tps = renvoi;
			renvoi = "";
			return tps;
		}
	}
	
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
			
			System.out.println("Rentrez votre nom d'utilisateur :");
			String name = "["+ sc.nextLine().trim() + "]";
			while(texte.userExist(name))
			{
				System.out.println("Nom deja pris, choisissez un autre :");
				name = "["+ sc.nextLine().trim() + "]";
			}
			texte.ajoutUser(name);
			System.out.println("Client ready");
			
			String[] histo = texte.getHistorique();
			for(int i=0; i<histo.length; i++)
			{
				if(!histo[i].equals(""))
				{
					System.out.println(histo[i]);
				}
				
			}
			
			/***********************************************/
			
			String envoi = "";
			String recu = "";
			ThreadWrite tw = new ThreadWrite();
			tw.start();
			
			while(true)
			{
				String recuTps = texte.get();
				if(!recu.equals(recuTps))
				{
					System.out.println(recuTps);
					recu = recuTps;
				}
				
				envoi = tw.getRenvoi();
				
				if(!envoi.equals(""))
				{
					texte.send(name + "\t" + envoi);
				}
				
			}
			
	
		} catch (Exception e) {
			System.err.println("Error on client: " + e); e.printStackTrace(); return;
		}
	}

} 