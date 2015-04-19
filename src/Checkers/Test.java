package Checkers;

import Agents.*;

// I use this for on the fly testing
public class Test {
	
	public static void main(String[] args) {
		
	GameState state = new GameState();
	Agent player = new MiniMaxAgent();
	
	Action a = player.computeAction(state);
	System.out.println(a.print());
	
	
	}
	
	
}
