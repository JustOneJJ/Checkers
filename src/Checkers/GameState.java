package Checkers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameState {
	
	private Board board = new Board();
	private Color turn;
	private List<Action> actions = new ArrayList<Action>();
	private List<Action> takingActions = new ArrayList<Action>();

	public GameState(){
		this.board = new Board();
		this.turn = Color.WHITE;
		this.computeActions();
	}
	
	GameState(GameState other) {
		this.board = new Board(other.board);
		this.turn = other.turn;
	}
	
	public void printStatus() {
		System.out.println("Turn: " + this.turn);
		System.out.println("White Pieces: " + this.board.getWhitepieces().size());
		System.out.println("Black Pieces: " + this.board.getBlackpieces().size());
		this.printActions();
	}
	
	public void computeActions(){
		this.forEachPiece(new AddTakingActions());
		this.forEachPiece(new AddActions());
		}
		
	public boolean hasAction(Action a) {
		return this.actions.contains(a);
	}
	
	public boolean isEmpty(Coordinate c){
		return this.board.isEmpty(c);
	}
	
	public GameState getNextGameState(Action a) {
		GameState next = new GameState(this);
		if(this.hasAction(a)) {
			if (this.takingActions.contains(a)){
				Coordinate src = a.getSource();
				Coordinate dest = a.getDestination();
				Coordinate mid = new Coordinate( (src.x()+dest.x())/2, (src.y()+dest.y())/2 );
				next.board.removePiece(mid);
			}
			Piece p = next.board.takePiece(a.getSource());
			p.setCoordinate(a.getDestination());
			next.board.placePice(p);
			next.turn = (next.turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
		}
		next.computeActions();
		next.printActions();
		return next;
	}
	
	public String boardString(){
		return this.board.print();
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
		
	    public void methodToCallBack(Piece p, GameState state) {
	    	if (p.getColor() != state.turn)
	    		return;
			Coordinate src = p.getCoordinate();
			Coordinate dest1 = null, dest2 = null;
			if(p.getColor() == Color.WHITE && state.turn == Color.WHITE){
				dest1 = new Coordinate(src.x() - 1, src.y() - 1 );
				dest2 = new Coordinate(src.x() + 1, src.y() - 1 );
			}else if (p.getColor() == Color.BLACK && state.turn == Color.BLACK){
				dest1 = new Coordinate(src.x() - 1, src.y() + 1 );
				dest2 = new Coordinate(src.x() + 1, src.y() + 1 );
			}	
			if (dest1.isValid() && state.board.isEmpty(dest1)){
				Action a = new Action(new Coordinate(src), dest1);
				state.actions.add(a);
			}
			if (dest2.isValid() && state.board.isEmpty(dest2)){
				Action a = new Action(new Coordinate(src), dest2);
				state.actions.add(a);
			}
	    }
	}
	
	class AddTakingActions implements CallBack {
		
		private Coordinate[] possibleDest = new Coordinate[2];
		private Coordinate[] inbetween = new Coordinate[2];
		
		private void calculatePossibleDest(Piece p, Color c){
			Coordinate src = p.getCoordinate();
			if(c == Color.WHITE){
				possibleDest[0] = new Coordinate(src.x()+2, src.y()-2);
				inbetween[0]    = new Coordinate(src.x()+1, src.y()-1);
				possibleDest[1] = new Coordinate(src.x()-2, src.y()-2);
				inbetween[1]    = new Coordinate(src.x()-1, src.y()-1);
			}else{
				possibleDest[0] = new Coordinate(src.x()+2, src.y()+2);
				inbetween[0]    = new Coordinate(src.x()+1, src.y()+1);
				possibleDest[1] = new Coordinate(src.x()-2, src.y()+2);
				inbetween[1]    = new Coordinate(src.x()-1, src.y()+1);
			}
		}
		
		public void methodToCallBack(Piece p, GameState state) {
			if (p.getColor() != state.turn)
	    		return;
			calculatePossibleDest(p, state.turn);
			Coordinate src = p.getCoordinate();
			for(int i = 0;i<2;i++){
				if (this.possibleDest[i].isValid() 
					&& state.board.isEmpty(this.possibleDest[i]) 
					&& !state.board.isEmpty(this.inbetween[i])
					&& state.board.lookAtPiece(inbetween[i]).getColor() != state.turn){
						Action a = new Action(new Coordinate(src), this.possibleDest[i]);
						System.out.println("YES!");
						state.actions.add(a);
						state.takingActions.add(a);
				}
			}
		}
	}
	
}

