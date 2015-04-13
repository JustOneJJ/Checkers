import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameState {
	
	private Board board = new Board();
	private Color turn;
	private List<Action> actions = new ArrayList<Action>();

	GameState(){
		this.board = new Board();
		this.turn = Color.WHITE;
		this.computeActions();
	}
	
	GameState(GameState other) {
		this.board = new Board(other.board);
		this.turn = other.turn;
	}
	
	public void computeActions(){
		if(this.turn == Color.WHITE) {
			List<Piece> pieces = this.board.getWhitepieces();
			Iterator<Piece> PieceIterator = pieces.iterator();
			while(PieceIterator.hasNext()) {
				this.addActions(PieceIterator.next());
				}
			}
		}
	
	private void addActions(Piece p){
		Coordinate src = p.getCoordinate();
		if(p.getColor() == Color.WHITE){
			Coordinate dest1 = new Coordinate(src.x() - 1, src.y() - 1 );
			Coordinate dest2 = new Coordinate(src.x() + 1, src.y() - 1 );
			if (dest1.isValid() && this.board.isEmpty(dest1)){
				Action a = new Action(new Coordinate(src), dest1);
				this.actions.add(a);
			}
			if (dest2.isValid() && this.board.isEmpty(dest2)){
				Action a = new Action(new Coordinate(src), dest2);
				this.actions.add(a);
			}
		}
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
			Piece p = next.board.takePiece(a.getSource());
			p.setCoordinate(a.getDestination());
			next.board.placePice(p);
		}
		next.computeActions();
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
	
}

