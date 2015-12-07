import java.rmi.*;


public interface Text extends Remote {

	public String get() throws RemoteException;
	public void send(String message) throws RemoteException;
	public String[] getHistorique() throws RemoteException;
	
}
