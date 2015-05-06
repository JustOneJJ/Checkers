package MVC;

import Agents.*;
import Checkers.Action;
import Checkers.Color;
import Checkers.Coordinate;
import Checkers.GameState;

public class Model extends java.util.Observable {

	//private Board b = new Board();
	private GameState state = new GameState();
	private Agent agent = new MiniMaxAgent(6);
	
	public void botVSbot(){
		Agent a1 = new MiniMaxAgent(5);
		Agent a2 = new RandomAgent();
		while(!state.isLose() && !state.isWin() ){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Action a;
			if (this.state.getTurn() == Color.WHITE){
				a = a1.computeAction(this.state);
			}else{
				a = a2.computeAction(this.state);
			}
			this.state = this.state.getNextGameState(a);
			this.state.printStatus();
			setChanged();
			notifyObservers(getBoardString());
		}
	}

	public void move(Coordinate from, Coordinate to){
		Action a = new Action(from, to);
		if (this.state.hasAction(a)){
		this.state = this.state.getNextGameState(a);
		this.state.printStatus();
		setChanged();
		notifyObservers(getBoardString());
		if(!this.state.isMoveAgain()){
			moveAgent();
			while(this.state.isMoveAgain()){
				moveAgent();
			}
		}
		}
	}
	
	public void moveAgent(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Action a = this.agent.computeAction(this.state);
		this.state = this.state.getNextGameState(a);
		this.state.printStatus();
		setChanged();
		notifyObservers(getBoardString());
	}
	
	public void newGame(){
		this.state = new GameState();
		setChanged();
		notifyObservers(getBoardString());
	}
	
	public void setAgent(Agent a){
		this.agent = a;
	}
	
	public String getBoardString(){
		return state.boardString();
	}
	
	public boolean isEmpty(Coordinate c){
		return this.state.isEmpty(c);
	}
}