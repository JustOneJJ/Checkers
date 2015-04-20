package Agents;

import java.util.Iterator;
import java.util.List;

import Checkers.Action;
import Checkers.Board;
import Checkers.Color;
import Checkers.GameState;
import Checkers.Piece;

public class MiniMaxAgent extends RandomAgent implements Agent{
	
	private int depth;
	private Color plays;
	
	public MiniMaxAgent(int depth){
		super();
		this.depth = depth;
	}
	
	public Action computeAction(GameState state){
		
		this.plays = state.getTurn();
		Result res = minimax(state, this.depth);
		return res.getAction();
		
	}
	
	private Result minimax(GameState state, int depth) {
		Result res = null;
		Action bestAction = null;
		
		if(depth == 0 || state.isWin() || state.isLose()) {
			return new Result( null, this.EvaluateState(state));
		}
		//maximize
		if(this.plays == Color.WHITE){
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
	
	public int EvaluateState(GameState state){
		int score = 0;
		Board b = state.getBoard();
		List<Piece> white = b.getWhitepieces();
		Iterator<Piece> PieceIterator = white.iterator();
		while(PieceIterator.hasNext()){
			if(PieceIterator.next().isPromoted()){
				score += 3;
			}else{
				score +=1;
			}
		}
		
		List<Piece> black = b.getBlackpieces();
		PieceIterator = black.iterator();
		while(PieceIterator.hasNext()){
			if(PieceIterator.next().isPromoted()){
				score -= 3;
			}else{
				score -=1;
			}
		}
		return score;
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
