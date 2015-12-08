import java.rmi.RemoteException;
import java.io.*;
import java.util.ArrayList;

public class TextImp implements Text {
	
	
	private String lastText = "";
	private String[] historique;
	private static int SIZE = 10;
	private static ArrayList ListeClients;
	
	TextImp()
	{
		ListeClients = new ArrayList();
		try {
			
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
	public String get() throws RemoteException {

		return lastText;
	}

	@Override
	public void send(String message) throws RemoteException {
		
		lastText = message;
		editHistorique();

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

}
