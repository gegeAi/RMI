import java.rmi.RemoteException;
import java.util.Vector;
import java.io.*;
import java.util.ArrayList;

public class TextImp implements Text {
	
	
	private String lastText = "";
	private String[] historique;
	private static int SIZE = 10;
<<<<<<< HEAD
	private Vector<ClientInt> clients;
=======
	private static ArrayList ListeClients;
>>>>>>> ee901fbc1945cbf08b1d1b8856973d1a5e6e4a1e
	
	TextImp()
	{
		ListeClients = new ArrayList();
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
	public boolean userExist(String name) throws RemoteException {
		
		return ListeClients.contains(name);

	}
	
	@Override
	public void ajoutUser(String name) throws RemoteException
	{
		ListeClients.add(name);
	}

	@Override
	public void send(String message) throws RemoteException {
		
		lastText = message;
		editHistorique();
		String s = get();
		for(int i=0; i<clients.size(); i++)
		{
			clients.get(i).ecrit(s);
		}

	}
	


	@Override
	public String[] getHistorique() throws RemoteException {
		// TODO Auto-generated method stub
		return historique;
	}
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void connect(ClientInt client) throws RemoteException 
	{
		clients.add(client);
		
	}

	@Override
	public String get() throws RemoteException {
		String s = "";
		for(int i=0; i<historique.length; i++)
		{
			s += historique[i];
		}
		return s;
	}

	@Override
	public boolean isUsernameUsed(String s, ClientInt client) throws RemoteException {
		for(int i=0; i<clients.size(); i++)
		{
			if(clients.get(i) != client && clients.get(i).cmp(s))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void delete(ClientInt client) throws RemoteException {
		clients.remove(client);
	}

}
