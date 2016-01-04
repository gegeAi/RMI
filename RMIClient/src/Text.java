import java.rmi.*;


public interface Text extends Remote {

	/**
	 * Renvoie un message d'un client vers tous les autres. Si c'est une premiere connexion,
	 * envoie aussi l'historique
	 * @param message
	 * 		le message envoye par un client, que le serveur va devoir transmettre
	
	 */
	public void send(String message) throws RemoteException;
	
	/** 
	 * ajoute un nouveau client a la conversation
	 * @param client 
	 * 		Un client a rajouter au systeme
	
	 */
	public void connect(ClientInt client) throws RemoteException;
	
	/**
	 * Verifie que le nom d'utilisateur qu'on cherche a utilise
	 * ne l'est pas deja	
	 * @param userName
	 * 		le nom d'utilisateur a comparer
	 * @param client
	 * 		le client dont on compare le nom
	 
	 */
	public boolean isUsernameUsed(String userName, ClientInt client) throws RemoteException;
	
	/**
	 * Supprime un client en fonction de son nom
	 * @param name
	 * 		le nom de l'utilisateur du client a supprimer
	 
	 */
	public void delete(String name) throws RemoteException;
}
