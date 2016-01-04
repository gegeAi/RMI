
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface ClientInt extends Remote{

	/**
	 * ajoute s a la fin du champ text,
	 * + scrolling automatique des que l'on recoit un message
	 * @param s 
	 * 		le message a ajouter 
	 
	 */
	public void ecrit(String s) throws RemoteException;
	
	/**
	 * recupere le nom d'utilisateur, pour que le serveur puisse faire
	 * des comparaisons
	 * @return userName
	 * 		le nom d'utilisateur du client
	 
	 */
	public String getUsername() throws RemoteException;
	
	/**
	 * compare userName au nom d'utilisateur du client
	 * renvoi vrai si les deux sont egaux
	 * @param userName
	 * 		le nom a comparer avec le nom d'utilisateur
	 * @return 
	 * 		vrai si les deux noms sont des chaines de carateres identiques
	 
	 */
	public boolean cmp(String userName) throws RemoteException;

}
