package Checkers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameState {
	
	private Board board = new Board();
	private Color turn;
	private List<Action> actions = new ArrayList<Action>();
	private List<Action> takingActions = new ArrayList<Action>();
	private boolean moveAgain;

	public GameState(){
		this.board = new Board();
		this.turn = Color.WHITE;
		this.computeActions();
	}
	
	public GameState(GameState other) {
		this.board = new Board(other.board);
		this.turn = other.turn;
		this.computeActions();
	}
	
	public int StateScore() {
		int piecesDif = this.board.getWhitepieces().size() - 
					 this.board.getBlackpieces().size();
		return piecesDif;
	}
	
	public void printStatus() {
		System.out.println("Turn: " + this.turn);
		System.out.println("Score: " + this.StateScore());
		System.out.println("White Pieces: " + this.board.getWhitepieces().size());
		System.out.println("Black Pieces: " + this.board.getBlackpieces().size());
		this.printActions();
	}
	
	public void computeActions(){
		this.actions.clear();
		this.takingActions.clear();
		this.forEachPiece(new AddTakingActions());
		if (this.actions.size() == 0){
			this.forEachPiece(new AddActions());
		}
		}
		
	public boolean hasAction(Action a) {
		return this.actions.contains(a);
	}
	
	public boolean isEmpty(Coordinate c){
		return this.board.isEmpty(c);
	}
	
	public GameState getNextGameState(Action a) {
		boolean took = false;
		GameState next = new GameState(this);
		next.moveAgain = false;
		if(this.hasAction(a)) {
			if (this.takingActions.contains(a)){
				Coordinate src = a.getSource();
				Coordinate dest = a.getDestination();
				Coordinate mid = new Coordinate( (src.x()+dest.x())/2, (src.y()+dest.y())/2 );
				next.board.removePiece(mid);
				took = true;
			}
			Piece p = next.board.takePiece(a.getSource());
			//System.out.println(p == null);
			//System.out.println(a.print());
			p.setCoordinate(a.getDestination());
			next.board.placePice(p);
			GameState temp = new GameState(next);
			
			if(took && temp.isCoordinateInTakingActions(a.getDestination() )){
				next.turn = (next.turn == Color.WHITE) ? Color.WHITE : Color.BLACK;
				next.setMoveAgain(true);
				
			}else{
				next.turn = (next.turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
			}
		}
		next.computeActions();
		//next.printActions();
		return next;
	}
	
	public String boardString(){
		return this.board.print();
	}
	
	public List<Action> getActions() {
		return this.actions;
	}
	
	public void printActions(){
		Iterator<Action> actionIterator = this.actions.iterator();
		while(actionIterator.hasNext()){
			System.out.println(actionIterator.next().print());
		}
	}
	
	public void forEachPiece(CallBack callback){
		Iterator<Piece> PieceIterator = this.board.getWhitepieces().iterator();
		while(PieceIterator.hasNext()) {
			callback.methodToCallBack(PieceIterator.next(), this);
		}
		PieceIterator = this.board.getBlackpieces().iterator();
		while(PieceIterator.hasNext()) {
			callback.methodToCallBack(PieceIterator.next(), this);
		}
	}
	
	interface CallBack {
	    void methodToCallBack(Piece p, GameState state);
	}

	class AddActions implements CallBack {
		
		private Coordinate[] dest = new Coordinate[4];
		
	    public void methodToCallBack(Piece p, GameState state) {
	    	if (p.getColor() != state.turn)
	    		return;
			Coordinate src = p.getCoordinate();
			if( (p.getColor() == Color.WHITE && state.turn == Color.WHITE)||p.isPromoted() ){
				dest[0] = new Coordinate(src.x() - 1, src.y() - 1 );
				dest[1] = new Coordinate(src.x() + 1, src.y() - 1 );
				for(int i = 0; i<2; i++){
					if (dest[i].isValid() && state.board.isEmpty(dest[i])){
						Action a = new Action(new Coordinate(src), new Coordinate(dest[i]));
						state.actions.add(a);
					}	
				}
			}
			if( (p.getColor() == Color.BLACK && state.turn == Color.BLACK) || p.isPromoted() ){
				dest[2] = new Coordinate(src.x() - 1, src.y() + 1 );
				dest[3] = new Coordinate(src.x() + 1, src.y() + 1 );
				for(int i = 2; i<4; i++){
					if (dest[i].isValid() && state.board.isEmpty(dest[i])){
						Action a = new Action(new Coordinate(src), new Coordinate(dest[i]));
						state.actions.add(a);
					}	
				}
			}	
	    }
	}
	
	class AddTakingActions implements CallBack {
		
		private Coordinate[] possibleDest = new Coordinate[4];
		private Coordinate[] inbetween = new Coordinate[4];
		
		private void calculatePossibleDest(Piece p, Color c){
			Coordinate src = p.getCoordinate();
			if( (c == Color.WHITE)|| p.isPromoted() ){
				possibleDest[0] = new Coordinate(src.x()+2, src.y()-2);
				inbetween[0]    = new Coordinate(src.x()+1, src.y()-1);
				possibleDest[1] = new Coordinate(src.x()-2, src.y()-2);
				inbetween[1]    = new Coordinate(src.x()-1, src.y()-1);
			}
			if( (c == Color.BLACK)|| p.isPromoted()){
				possibleDest[2] = new Coordinate(src.x()+2, src.y()+2);
				inbetween[2]    = new Coordinate(src.x()+1, src.y()+1);
				possibleDest[3] = new Coordinate(src.x()-2, src.y()+2);
				inbetween[3]    = new Coordinate(src.x()-1, src.y()+1);
			}
		}
		
		public void methodToCallBack(Piece p, GameState state) {
			if (p.getColor() != state.turn)
	    		return;
			calculatePossibleDest(p, state.turn);
			Coordinate src = p.getCoordinate();
			int index, max;
			
			if(p.isPromoted()){
				index = 0; max = 4; 
			}else if(state.turn == Color.WHITE){
				index = 0; max = 2;
			}else{
				index = 2; max = 4;
			}
			
			for(; index<max; index++){
				if (this.possibleDest[index].isValid() 
					&& state.board.isEmpty(this.possibleDest[index]) 
					&& !state.board.isEmpty(this.inbetween[index])
					&& state.board.lookAtPiece(inbetween[index]).getColor() != state.turn){
						Action a = new Action(new Coordinate(src), new Coordinate(this.possibleDest[index]));
						//System.out.println("YES!");
						state.actions.add(a);
						state.takingActions.add(a);
				}
			}
		}
	}
	
	public boolean isCoordinateInTakingActions(Coordinate c){
		Iterator<Action> actionIterator = this.takingActions.iterator();
		while(actionIterator.hasNext()){
			Action a = actionIterator.next();
			Coordinate src = a.getSource();
			//System.out.println(c.print() + "==" + src.print());
			if( c.equals(src)){
				return true;
			}
		}
		return false;
	}
	
	public void removeTakingActions(Coordinate c){
		Iterator<Action> actionIterator = this.takingActions.iterator();
		while(actionIterator.hasNext()){
			Action a = actionIterator.next();
			Coordinate src = a.getSource();
			if( !src.equals(c)){
				System.out.println(this.takingActions.size());
				this.takingActions.remove(a);
				this.actions.remove(a);
				System.out.println(this.takingActions.size());
			}
		}
	}
	
	public boolean isWin() {
		if(this.turn == Color.WHITE){
			return this.board.getBlackpieces().size() == 0;
		}
		return this.board.getWhitepieces().size() == 0;
	}
	
	public boolean isLose(){
		return this.getActions().size() == 0;
	}
	
	public Color getTurn(){ 
		return this.turn;
	}
	
	public Board getBoard(){
		return this.board;
	}

	/**
	 * @return the moveAgain
	 */
	public boolean isMoveAgain() {
		return moveAgain;
	}

	/**
	 * @param moveAgain the moveAgain to set
	 */
	public void setMoveAgain(boolean moveAgain) {
		this.moveAgain = moveAgain;
	}
	
}

