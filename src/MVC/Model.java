package MVC;

import Agents.*;
import Checkers.Action;
import Checkers.Coordinate;
import Checkers.GameState;

public class Model extends java.util.Observable {

	//private Board b = new Board();
	private GameState state = new GameState();

	public void move(Coordinate from, Coordinate to){
		Action a = new Action(from, to);
		this.state = this.state.getNextGameState(a);
		this.state.printStatus();
		setChanged();
		notifyObservers(getBoardString());
		
		moveAgent();
	}
	
	public void moveAgent(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Agent agent = new RandomAgent();
		Agent agent = new MiniMaxAgent(6);
		Action a = agent.computeAction(this.state);
		this.state = this.state.getNextGameState(a);
		this.state.printStatus();
		setChanged();
		notifyObservers(getBoardString());
	}
	
	public String getBoardString(){
		return state.boardString();
	}
	
	public boolean isEmpty(Coordinate c){
		return this.state.isEmpty(c);
	}
}