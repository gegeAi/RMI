
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface ClientInt extends Remote{

	public void ecrit(String s) throws RemoteException;
	public String getUsername() throws RemoteException;
	public boolean cmp(String userName) throws RemoteException;

}
