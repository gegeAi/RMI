import java.rmi.RemoteException;
import java.util.Vector;
import java.io.*;

public class TextImp implements Text {
	
	/**
	 * dernier message recu par le serveur
	 */
	private String lastText = "";
	
	/**
	 * historique fini de messagerie
	 */
	private String[] historique;
	
	/**
	 * la taille de l'historique
	 */
	private static int SIZE = 100;
	
	/**
	 * l'ensemble des clients connectes
	 */
	private Vector<ClientInt> clients;
	
	/**
	 * Construit l'objet remote qui servira de serveur
	 */
	TextImp()
	{
		try {
			clients = new Vector<ClientInt>();
			ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream(
											new File("historique.log")));
			
			historique = (String[]) ois.readObject();
			lastText = "";
			ois.close();
		
		} catch (Exception e) {
			historique = new String[SIZE];
			for(int i=0; i<historique.length; i++)
			{
				historique[i] = "";
			}
		}
	}
	
	@Override
	public void send(String message) throws RemoteException {
		
		lastText = message;
		editHistorique();
		String s = get();
		for(int i=0; i<clients.size(); i++)
		{
			if(!lastText.contains("[") && lastText.contains(clients.get(i).getUsername()))
			{
				clients.get(i).ecrit(s);
			}
			else
			{
			clients.get(i).ecrit(lastText);
			}
		}
		

	}
	
	/**
	 * met l'historique a jour et l'enregistre dans un fichier
	 */
	private void editHistorique() 
	{
		for(int i=0; i<historique.length-1; i++)
		{
			historique[i] = historique[i+1];
		}
		
		historique[SIZE-1] = lastText;
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream(
												new File("historique.log")));
			
			oos.writeObject(historique);
			
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void connect(ClientInt client) throws RemoteException 
	{
		clients.add(client);		
	}

	/**
	 * Transforme le tableau de String en une seule par concatenation
	 * @return historique
	 * 		le tableau de message historique transforme en String
	 
	 */
	public String get()  {
		String s = "";
		for(int i=0; i<historique.length; i++)
		{
			s += historique[i];
		}
		return s;
	}
	
	@Override
	public boolean isUsernameUsed(String userName, ClientInt client) throws RemoteException {
		for(int i=0; i<clients.size(); i++)
		{
			if(clients.get(i) != client && clients.get(i).cmp(userName))
			{
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void delete(String name) throws RemoteException {
		for(int i=0; i<clients.size(); i++)
		{
			if(clients.get(i).cmp(name))
			{
				clients.remove(i);
				break;
			}
		}
	}

}
