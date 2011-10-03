package transferObjects.gameplay;

import java.util.ArrayList;

public class MMGuess
{
	private ArrayList<MMPegForGuessColor> colors;
	
	public MMGuess()
	{
	}
	
	public MMGuess(ArrayList<MMPegForGuessColor> pegColors)
	{
		this.colors = pegColors;
	}
	
	public void setColors(ArrayList<MMPegForGuessColor> colors)
	{
		this.colors = colors;
	}
	
	public ArrayList<MMPegForGuessColor> getColors()
	{
		return this.colors;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[colors: ");
		
		for (MMPegForGuessColor color : colors)
		{
			sb.append(color);
			sb.append(" ");
		}
		
		sb.append("]");
		
		return sb.toString();
	}
	
}
