package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import server.MMGameServer;
import transferObjects.MMJSONAdapter;
import transferObjects.gameplay.MMGuess;
import transferObjects.gameplay.MMGuessResponse;
import transferObjects.networking.MMConnectAcknowledgedNotification;
import transferObjects.networking.MMConnectNotification;
import transferObjects.networking.MMPeer;
import transferObjects.networking.MMTransferObject;
import transferObjects.networking.exceptions.MMBadTransferObjectException;
import common.MMNetworkingConstants;

public class MMGameClient
{
	private ArrayList<MMClientObservable> registeredListeners;
	
	private MMJSONAdapter jsonAdapter;
	private MMGameServer mmServer;

	public MMGameClient(MMGameServer mmServer)
	{
		int i = 0;
		try
		{
			ServerSocket listener = new ServerSocket(MMNetworkingConstants.MM_PORT);
			Socket server;

			while((i++ < MMNetworkingConstants.MM_MAX_NUM_CONNECTIONS) || (MMNetworkingConstants.MM_MAX_NUM_CONNECTIONS == 0))
			{
				server = listener.accept();
				MMCommSocket conn_c = new MMCommSocket(server, this);

				Thread t = new Thread(conn_c);
				t.start();
			}
		}
		catch (IOException ioe)
		{
			System.err.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}

		this.jsonAdapter = new MMJSONAdapter();
		this.mmServer = mmServer;
	}

	public void registerListener(MMClientObservable client)
	{
		registeredListeners.add(client);
	}

	public void unregisterListener(MMClientObservable client)
	{
		registeredListeners.remove(client);
	}
	
	private void notifyListenersOfGuess(MMGuess guess)
	{
		for (MMClientObservable listener : registeredListeners)
		{
			listener.recieveGuess(guess);
		}
	}
	
	private void notifyListenersOfGuessResponse(MMGuessResponse guessResponse)
	{
		for (MMClientObservable listener : registeredListeners)
		{
			listener.recieveGuessResponse(guessResponse);
		}
	}

	protected void receiveAndParseNotification(String json) throws MMBadTransferObjectException
	{
		// First, break down the JSON'd TO into a MMTransferObject so class information can be extracted.
		MMTransferObject transferObject = jsonAdapter.buildTransferObjectFromJSON(json);


		// Next figure out what the TO contains so we can handle it accordingly
		String type = transferObject.getClassName();

		if (type.equals(MMGuess.class.getName()))
		{
			MMGuess guess = jsonAdapter.fromJSON(transferObject.getData(), MMGuess.class);
			notifyListenersOfGuess(guess);
		}
		else if (type.equals(MMGuessResponse.class.getName()))
		{
			MMGuessResponse response = jsonAdapter.fromJSON(transferObject.getData(), MMGuessResponse.class);
			notifyListenersOfGuessResponse(response);
		}
		else if (type.equals(MMConnectNotification.class.getName()))
		{
			MMConnectNotification notification = jsonAdapter.fromJSON(transferObject.getData(), MMConnectNotification.class);
			mmServer.receiveConnectNotification(notification);			
		}
		else if (type.equals(MMConnectAcknowledgedNotification.class.getName()))
		{
			
		}
		else
		{
			throw new MMBadTransferObjectException("MM Networking stack error: Unknown type specified! Type was:" + type);
		}
	}
}
