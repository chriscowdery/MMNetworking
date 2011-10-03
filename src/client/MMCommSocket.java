package client;

import java.io.*;
import java.net.*;

import transferObjects.networking.exceptions.MMBadTransferObjectException;

class MMCommSocket implements Runnable
{
    private Socket server;
    private String line,input;
    private MMGameClient daemon;

    MMCommSocket(Socket server, MMGameClient daemon)
    {
    	this.server = server;
    	this.daemon = daemon;
    }

    public void run ()
    {
    	this.input = "";

		try
		{
			// Get input from the server
			DataInputStream in = new DataInputStream (server.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			
			while((line = bufferedReader.readLine()) != null)
			{
				input = input + line;
			}
			
			try
			{
				daemon.receiveAndParseNotification(input);
			}
			catch (MMBadTransferObjectException e)
			{
				System.err.println(e.getError());
				e.printStackTrace();
			}
			
			server.close();
		}
		catch (IOException e)
		{
			System.err.println("IOException on socket listen: " + e);
			e.printStackTrace();
		}
    }
}