import java.rmi.*;


public interface Text extends Remote {

	public void send(String message) throws RemoteException;
	public String[] getHistorique() throws RemoteException;
	public void connect(ClientInt client) throws RemoteException;
	public String get() throws RemoteException;
	public boolean isUsernameUsed(String s, ClientInt client) throws RemoteException;
	public void delete(ClientInt client) throws RemoteException;
	
}
