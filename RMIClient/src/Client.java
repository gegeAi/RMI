import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class Client extends JFrame implements KeyListener, WindowListener, ClientInt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * champ de texte pour recevoir les messages
	 */
	private JTextPane text;
	
	/**
	 * champ de texte pour envoyer un message
	 */
	private JTextPane message;
	
	/**
	 * nom d'utilisateur, complete par le premier envoi de message
	 */
	private String userName = null;
	
	/**
	 * le serveur, pour communiquer
	 */
	private Text server;
	
	/**
	 * element permettant le scrolling de text
	 */
	private JScrollPane pane;
	
	/**
	 * 
	 * @param serv
	 * 		le nom du serveur tel qu'il est enregistre dans le registre
	 * @param host
	 * 		l'adresse du serveur
	 */
	Client(String serv, String host)
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			server = (Text) registry.lookup(serv);
			server.connect((ClientInt)
					UnicastRemoteObject.exportObject(this, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setSize(300, 600);
		
		text = new JTextPane();
		text.setBackground(Color.LIGHT_GRAY);
		text.setEditable(false);
		pane = new JScrollPane(text);
		pane.setVisible(true);
		pane.setSize(300,500);
		pane.setAutoscrolls(true);
		message = new JTextPane();
		message.setSize(300, 100);
		message.setLocation(0, 500);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(pane);
		p.add(message);
		this.setContentPane(p);
			
		String s = "rentrez votre nom d'utilisateur :\n";
		text.setText(s);
			
		message.addKeyListener(this);
		this.setResizable(false);
		this.addWindowListener(this);
	}
	
	public void ecrit(String s) throws RemoteException
	{	
		if(userName != null)
		{
			String deja = text.getText();
			text.setText(deja + s);
		}
		
		text.setCaretPosition(text.getStyledDocument().getLength());
		
	}
	
	/**
	 * methode etant appele par le champs message,
	 * du a l'ecoute de celui-ci par le client.
	 * Si l'on appui sur enter, on valide et envoie un message au serveur (donc aux autres clients)
	 * @param arg0 
	 * 		l'objet ayant appele la methode (ici message) 
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(userName != null)
			{
				try {
					server.send("["+userName+"]\t"+message.getText());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				message.setText("");
				
			}
			else
			{
				try {
					String tpsUserName = message.getText().substring(0, message.getText().length()-2);
					if(!server.isUsernameUsed(tpsUserName, this))
					{
						userName = tpsUserName;
						server.send(userName + " vient de se connecter.\n");
					}
					else
					{
						text.setText("UserName deja utilisé, veuillez réessayer.");
						userName = null;
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				message.setText("");
			}
		}
	}

	@Override
	public String getUsername() throws RemoteException {
		return userName;
	}

	@Override
	public boolean cmp(String userName) throws RemoteException {

		if(userName != null && this.userName != null)
			return this.userName.equals(userName);
		else
			return false;
	}

	/**
	 * action a realiser a la fermeture de la fenetre.
	 * Ici, on demande au serveur de s'enlever de la liste des clients avant fermeture.
	 * @param e
	 * 		l'objet ayant appele la methode (ici, la fenetre client)
	
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		try {
			server.delete(userName);
			if(userName != null)
				server.send(userName + " s'est deconnecté.\n");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

}
