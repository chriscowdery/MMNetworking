package client;

import transferObjects.gameplay.MMGuess;
import transferObjects.gameplay.MMGuessResponse;

public interface MMClientObservable
{
	public void recieveGuess(MMGuess guess);
	public void recieveGuessResponse(MMGuessResponse guessResponse);
}
