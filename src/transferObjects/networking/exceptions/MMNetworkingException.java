package transferObjects.networking.exceptions;

public class MMNetworkingException extends Exception
{
	String extraData;
	
	public MMNetworkingException()
	{
		super();
		extraData = null;
	}
	
	public MMNetworkingException(String error)
	{
		super(error);
		this.extraData = error;
	}
	
	 public String getError()
	 {
	    String toReturn = "The network information cannot be processed.";
	    
	    if (extraData != null)
	    {
	    	toReturn += " The error was: " + extraData;
	    }
	    
	    return toReturn;
	 }
}
