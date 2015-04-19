package Agents;

import java.util.Iterator;
import java.util.List;

import Checkers.Action;
import Checkers.Color;
import Checkers.GameState;

public class MiniMaxAgent extends RandomAgent implements Agent{
	
	private Color plays;
	
	public MiniMaxAgent(){
		super();
	}
	
	public Action computeAction(GameState state){
		this.plays = state.getTurn();
		Result res = minimax(state, 4);
		return res.getAction();
		
	}
	
	private Result minimax(GameState state, int depth) {
		Result res = null;
		Action bestAction = null;
		
		if(depth == 0 || state.isWin() || state.isLose()) {
			return new Result( null, super.EvaluateState(state));
		}
		//maximize
		if(this.plays == state.getTurn()){
			int bestValue = -99999999;
			List<Action> actions = state.getActions();
			Iterator<Action> actionIterator = actions.iterator();
			while(actionIterator.hasNext()){
				Action action = actionIterator.next();
				GameState copy = new GameState(state);
				res = minimax(copy.getNextGameState(action), depth-1);
				if( res.getScore() > bestValue ){
					bestValue = res.getScore();
					bestAction = new Action(action);
				}
			}
			return new Result(bestAction, bestValue);
		}
		else{
			int bestValue = 99999999;
			List<Action> actions = state.getActions();
			Iterator<Action> actionIterator = actions.iterator();
			while(actionIterator.hasNext()){
				Action action = actionIterator.next();
				GameState copy = new GameState(state);
				res = minimax(copy.getNextGameState(action), depth-1);
				if( res.getScore() < bestValue ){
					bestValue = res.getScore();
					bestAction = new Action(action);
				}
			}
			return new Result(bestAction, bestValue);
		}
	}
	
	
	
	private class Result{
		public Action action;
		public int score;
		
		public Result(Action action, int score) {
			this.action = action;
			this.score = score;
		}
		public Action getAction() {
			return action;
		}

		public int getScore() {
			return score;
		}	
	}
	
	
}
