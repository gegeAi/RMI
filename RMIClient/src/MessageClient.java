import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class MessageClient {
	
<<<<<<< HEAD
=======
	static private JFrame fenetre;
	static private JTextArea conversation;
	static private JTextField chatZone;
	static private JButton sendButton;
	static private JScrollPane scroll;
	
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
	
	static public class InterfaceGraph extends Frame{
		
		public InterfaceGraph() { 
			
		}
		
	}
>>>>>>> ee901fbc1945cbf08b1d1b8856973d1a5e6e4a1e
	public static void main(String [] args) 
	{
		if (args.length < 1) 
		{ 
			System.out.println("Usage: java MessageClient <server host>"); 
			return; 
		}
		new InterfaceGraph();
		try 
		{
<<<<<<< HEAD
			 //if (System.getSecurityManager() == null) { System.setSecurityManager(new SecurityManager()); }
			
			String host = args[0];
			Client c = new Client("text", host);
			c.setVisible(true);
			System.out.println("Client ready");		
=======
			//if (System.getSecurityManager() == null) { System.setSecurityManager(new SecurityManager()); }
			
			fenetre = new JFrame("chat client");
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetre.setLayout(new FlowLayout());
			fenetre.setSize(300,320); 
			
			conversation = new JTextArea(15,15);
			conversation.setEditable(false);
			conversation.setLineWrap(false);
			chatZone = new JTextField(15);
			scroll = new JScrollPane(conversation);
			sendButton = new JButton("Send");
			
			fenetre.getContentPane().add(scroll);
			fenetre.getContentPane().add(chatZone);
			fenetre.getContentPane().add(sendButton);
			fenetre.setVisible(true);
			
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
>>>>>>> ee901fbc1945cbf08b1d1b8856973d1a5e6e4a1e
			
			/***********************************************/
				
		} catch (Exception e) {
			System.err.println("Error on client: " + e); e.printStackTrace(); return;
		}
	}

} 