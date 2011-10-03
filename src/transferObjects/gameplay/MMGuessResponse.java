package transferObjects.gameplay;

import java.util.ArrayList;

import com.google.gson.Gson;

public class MMGuessResponse
{
	private ArrayList<MMPegForGuessResponseColor> colors;
	
	/**
	 * Once created, Guesses are immutable.
	 * @param pegColors
	 */
	public MMGuessResponse(ArrayList<MMPegForGuessResponseColor> pegColors)
	{
		this.colors = pegColors;
	}
	
	public ArrayList<MMPegForGuessResponseColor> getColors()
	{
		return this.colors;
	}
	
	public String toJSON()
	{
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
}
