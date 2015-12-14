import java.rmi.*;


public interface Text extends Remote {

	public void send(String message) throws RemoteException;
	public String[] getHistorique() throws RemoteException;
<<<<<<< HEAD
	public void connect(ClientInt client) throws RemoteException;
	public String get() throws RemoteException;
	public boolean isUsernameUsed(String s, ClientInt client) throws RemoteException;
	public void delete(ClientInt client) throws RemoteException;
	
=======
	public boolean userExist(String name) throws RemoteException;
	public void ajoutUser(String name) throws RemoteException;
>>>>>>> ee901fbc1945cbf08b1d1b8856973d1a5e6e4a1e
}
