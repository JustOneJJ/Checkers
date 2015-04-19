package Agents;

import Checkers.Action;
import Checkers.GameState;

public interface Agent {
	
	public Action computeAction(GameState state);
	
}
