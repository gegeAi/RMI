import java.rmi.server.*;
import java.rmi.registry.*; 
public class MessageServer {

	public static void main(String[] args) {
		try 
		{
			// Create a Hello remote object
			LocateRegistry.createRegistry(1099);
			TextImp text = new TextImp ();

			Text text_stub = (Text)
					UnicastRemoteObject.exportObject(text, 0);

			// Register the remote object in RMI
			// registry with a given identifier
			Registry registry= LocateRegistry.getRegistry();

			registry.bind("text", text_stub);

			System.out.println ("Server ready");

		} 
		catch (Exception e) 
		{

			System.err.println("Error on server :"+ e) ;
			e.printStackTrace();
			return;
		}
	} 

}
