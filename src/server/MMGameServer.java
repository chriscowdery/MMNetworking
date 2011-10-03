package server;

import java.util.ArrayList;

import transferObjects.MMJSONAdapter;
import transferObjects.gameplay.MMGuess;
import transferObjects.gameplay.MMGuessResponse;
import transferObjects.networking.MMConnectAcknowledgedNotification;
import transferObjects.networking.MMConnectNotification;
import transferObjects.networking.MMPeer;
import transferObjects.networking.MMTransferObject;
import transferObjects.networking.exceptions.MMNetworkingException;

public class MMGameServer
{
	private ArrayList<MMPeer> peers;
	private MMJSONAdapter jsonAdapter;
	
	public MMGameServer()
	{
		peers = new ArrayList<MMPeer>();
		jsonAdapter = new MMJSONAdapter();
	}
	
	public void receiveConnectNotification(MMConnectNotification notification)
	{
		MMPeer peer = new MMPeer(notification);
		boolean alreadyAdded = false;
		
		// Make sure the peer doesn't exist already
		for (MMPeer currentPeer : peers)
		{
			if (currentPeer.equals(peer))
			{
				System.out.println("MM Networking: Peer already registered. Not registering again.");
				alreadyAdded = true;
				break;
			}
		}
		
		if (!alreadyAdded)
		{
			// Although this has been designed (in theory) to support more than one peer.
			// This release will only support one peer.
			if (peers.size() >= 1)
			{
				peers.clear();
			}
			
			peers.add(peer);
			
			// Dispatch acknowledgment of connection
			pushConnectAcknolwegdedNotification(peer);
		}
	}
	
	public void pushConnectAcknolwegdedNotification(MMPeer peer)
	{
		MMConnectAcknowledgedNotification connNotification = new MMConnectAcknowledgedNotification();
		MMTransferObject to = jsonAdapter.convertToTransferObject(connNotification);
		
		try
		{
			pushTO(to);
		}
		catch (MMNetworkingException e)
		{
			System.err.println(e.getError());
			e.printStackTrace();
		}
	}	
	
	public void pushGuess(MMGuess guess) throws MMNetworkingException
	{
	
	}
	
	public void pushGuessResponse(MMGuessResponse guessResponse) throws MMNetworkingException
	{
		
	}
	
	/**
	 * 
	 * @param to
	 * @throws MMNetworkingException
	 */
	private void pushTO(MMTransferObject to) throws MMNetworkingException
	{
		if (peers.size() < 1)
		{
			throw new MMNetworkingException("You must have more than one peer in order to perform this operation.");
		}
		
	}
	
	
}
