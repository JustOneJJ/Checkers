package MVC;

import Checkers.Action;
import Checkers.Coordinate;
import Checkers.GameState;

public class Model {

	//private Board b = new Board();
	private GameState state = new GameState();

	public void move(Coordinate from, Coordinate to){
		Action a = new Action(from, to);
		this.state = this.state.getNextGameState(a);
		this.state.printStatus();
	}
	
	public String getBoardString(){
		return state.boardString();
	}
	
	public boolean isEmpty(Coordinate c){
		return this.state.isEmpty(c);
	}
}