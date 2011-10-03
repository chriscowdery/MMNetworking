package transferObjects.networking;

import java.util.Date;

public class MMPeer
{
	private Date lastSeen;
	private String ip;
	private String hostname;
	
	public MMPeer()
	{
		this.lastSeen = new Date();
	}
	
	public MMPeer(MMConnectNotification notification)
	{
		this.lastSeen = new Date();
		
		this.ip = notification.getIp();
		this.hostname = notification.getHostname();
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public boolean equals(MMPeer peer)
	{
		return (peer.getIp().equals(this.ip));
	}
}
