package transferObjects;

import transferObjects.networking.MMTransferObject;

import com.google.gson.Gson;

public class MMJSONAdapter
{
	private Gson gson;
	
	public MMJSONAdapter()
	{
		gson = new Gson();
	}
	
	public MMTransferObject convertToTransferObject(Object obj)
	{
		String data = gson.toJson(obj);
		
		MMTransferObject toReturn = new MMTransferObject();
		toReturn.setClassName(obj.getClass().getName());
		toReturn.setData(data);

		return toReturn;
	}
	
	public Object convertFromTransferObject(MMTransferObject transferObject)
	{
 		Object toReturn = null;
		
		try
		{
			@SuppressWarnings("rawtypes") // this is intentional and necessary to decouple the parser from the object transferred.
			Class classType = Class.forName(transferObject.getClassName());
			
			toReturn = gson.fromJson(transferObject.getData(), classType);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	public MMTransferObject buildTransferObjectFromJSON(String json)
	{
		return gson.fromJson(json, MMTransferObject.class);
	}
	
	public <T> T fromJSON(String json, Class<T> type)
	{
		return gson.fromJson(json, type);
	}
	
}
