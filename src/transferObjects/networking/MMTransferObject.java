package transferObjects.networking;

public class MMTransferObject
{
	private String data;
	private String className;
	
	public MMTransferObject()
	{
		this.setData(null);
		this.setClassName(null);
	}
	
	public MMTransferObject(String data, String className)
	{
		this.setData(data);
		this.setClassName(className);
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}
	
	
}
