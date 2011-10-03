package transferObjects.networking.exceptions;

public class MMBadTransferObjectException extends Exception
{
	String extraData;
	
	public MMBadTransferObjectException()
	{
		super();
		extraData = null;
	}
	
	public MMBadTransferObjectException(String error)
	{
		super(error);
		this.extraData = error;
	}
	
	 public String getError()
	 {
	    String toReturn = "The Transfer Object is malformed and cannot be prepared for transfer.";
	    
	    if (extraData != null)
	    {
	    	toReturn += " Additional information: " + extraData;
	    }
	    
	    return toReturn;
	 }
}
