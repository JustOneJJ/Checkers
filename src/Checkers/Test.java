package Checkers;

import Agents.*;

// I use this for on the fly testing
public class Test {
	
	public static void main(String[] args){
		
	//GameState state = new GameState();
	//Agent player1 = new MiniMaxAgent(4);
	//Agent player2 = new RandomAgent();
	//Agent player2 = new MiniMaxAgent(6);
	//System.out.println(state.boardString());
	
	//GameState copy = new GameState(state);
	//System.out.println(copy.getBoard().print());
	
	int score = 0;
	for (int i = 0; i<20; i++ ){
		System.out.println(i);
		GameState state = new GameState();
		Agent player1 = new MiniMaxAgent(2);
		Agent player2 = new RandomAgent();
		//Agent player2 = new MiniMaxAgent(10);
		while( !state.isWin() && !state.isLose()){
			Action a;
			if(state.getTurn() == Color.WHITE){
				GameState copy = new GameState(state);
				copy.computeActions();
				//System.out.println(copy == state);
				a = player1.computeAction(new GameState(state));
				state = state.getNextGameState(a);
			}else{
				a = player2.computeAction(new GameState(state));
				state = state.getNextGameState(a);
			}
			//System.out.println(state.boardString());
			//state.printStatus();
		}
		if(state.isLose()){
			System.out.println(state.getTurn() + " Lost");
			if (state.getTurn() == Color.BLACK){
				score++;
			}
		}
	}	
	System.out.println("White won : " + score + "/20 games");
	//Action a = player1.computeAction(new GameState(state));
	//state = state.getNextGameState(a);
	
	}
	
	
}
