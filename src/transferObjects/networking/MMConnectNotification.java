package transferObjects.networking;

public class MMConnectNotification
{
	private String ip;
	private String hostname;
	
	public MMConnectNotification()
	{
	}
	
	public MMConnectNotification(String ip)
	{
		this.setIp(ip);
	}
	
	public MMConnectNotification(String ip, String hostname)
	{
		this.setIp(ip);
		this.setHostname(hostname);
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getHostname()
	{
		return hostname;
	}

	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
}
