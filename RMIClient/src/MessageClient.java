public class MessageClient {
	
	public static void main(String [] args) 
	{
		if (args.length < 1) 
		{ 
			System.out.println("Usage: java MessageClient <server host>"); 
			return; 
		}
			
		String host = args[0];
		Client c = new Client("text", host);
		c.setVisible(true);		
		System.out.println("Client ready");		
				
	}

} 