import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class Client extends JFrame implements KeyListener, ClientInt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextPane text;
	private JTextPane message;
	private String userName = null;
	private Text server;
	
	Client(String serv, String host)
	{
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			server = (Text) registry.lookup(serv);
			server.connect((ClientInt)
					UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setSize(300, 600);
		text = new JTextPane();
		text.setEditable(false);
		text.setSize(300, 400);
		text.setBackground(Color.LIGHT_GRAY);
		message = new JTextPane();
		message.setSize(300, 200);
		message.setLocation(0, 400);
		JPanel p = new JPanel();
		p.setLayout(null);
		p.add(text);
		p.add(message);
		//p.setLayout(new GridLayout(2,1));
		this.setContentPane(p);
			
		String s = "rentrez votre nom d'utilisateur :\n";
		text.setText(s);
			
		message.addKeyListener(this);
		this.setResizable(false);
	}
	
	public void finalize()
	{
		try {
			server.delete(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ecrit(String s) throws RemoteException
	{
		if(userName != null)
			text.setText(s);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(userName != null)
			{
				try {
					server.send("["+userName+"]\t"+message.getText()+"\n");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				message.setText("");
			}
			else
			{
				try {
					if(!server.isUsernameUsed(message.getText(), this))
					{
						userName = message.getText();
						text.setText("Bienvenue " + userName + " !\n\n"+server.get());
					}
					else
					{
						text.setText("UserName deja utilisé, veuillez réessayer.");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message.setText("");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public String getUsername() throws RemoteException {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean cmp(String userName) throws RemoteException {

		if(userName != null && this.userName != null)
			return this.userName.equals(userName);
		else
			return false;
	}

}
