package Checkers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board {
	
	private Piece[][] board = new Piece[8][8];
	private List<Piece> whitepieces = new ArrayList<Piece>();
	private List<Piece> blackpieces = new ArrayList<Piece>();

	private void init() {
		for (int x = 0 ; x < 7; x += 2 ) {
			board[x][0] = new Piece( new Coordinate(x,0), Color.BLACK );
			blackpieces.add(board[x][0]);
		}
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][1] = new Piece( new Coordinate(x,1), Color.BLACK );
			blackpieces.add(board[x][1]);
		}
		for (int x = 0 ; x < 8; x += 2 ) {
			board[x][2] = new Piece( new Coordinate(x,2), Color.BLACK );
			blackpieces.add(board[x][2]);
		}
		
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][5] = new Piece( new Coordinate(x,5), Color.WHITE );
			whitepieces.add(board[x][5]);
		}
		for (int x = 0 ; x < 8; x += 2 ) {
			board[x][6] = new Piece( new Coordinate(x,6), Color.WHITE );
			whitepieces.add(board[x][6]);
		}
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][7] = new Piece( new Coordinate(x,7), Color.WHITE );
			whitepieces.add(board[x][7]);
		}
	}
	
	Board(){
		init();
	}
	
	Board(Board other) {
		Iterator<Piece> PieceIterator = other.whitepieces.iterator();
		while(PieceIterator.hasNext()){
			Piece copy = PieceIterator.next();
			this.placePice(copy);
		}
		PieceIterator = other.blackpieces.iterator();
		while(PieceIterator.hasNext()){
			Piece copy = PieceIterator.next();
			this.placePice(copy);
		}
	};
	
	public List<Piece> getWhitepieces() {
		return whitepieces;
	}

	public List<Piece> getBlackpieces() {
		return blackpieces;
	}
	
	public String print() {
		String result = "";
		for (int y = 0; y <= 7 ; y++){
			for(int x = 0; x <=7 ; x++){
				if (this.board[x][y] == null){
					result += '0';	
				}else if( this.board[x][y].getColor() == Color.WHITE ){
					result += 'W';
				}else{
					result += 'B';
				}
			}
			//result += "\n";
		}
		return result;
	}
	
	public Piece lookAtPiece(Coordinate c){
		return board[c.x()][c.y()];
	}
	
	public boolean isEmpty(Coordinate c){
		return board[c.x()][c.y()] == null;
	}
	
	public Piece takePiece(Coordinate c) {
		Piece p = board[c.x()][c.y()];
		this.removePiece(c);
		return p;
	}
	
	public void removePiece(Coordinate c) {
		if (board[c.x()][c.y()] == null)
			return;
		if (board[c.x()][c.y()].getColor() == Color.WHITE ){
			this.whitepieces.remove(board[c.x()][c.y()]);
		}
		else {
			this.blackpieces.remove(board[c.x()][c.y()]);
		}
		this.board[c.x()][c.y()] = null;
	}
	
	public void placePice(Piece p){
		Coordinate c = p.getCoordinate();
		board[c.x()][c.y()] = p;
		if (p.getColor() == Color.WHITE ){
			this.whitepieces.add(p);
		}
		else {
			this.blackpieces.add(p);
		}
	}
	
	public void printPieces() {
		Iterator<Piece> PieceIterator = this.whitepieces.iterator();
		while(PieceIterator.hasNext()){
			System.out.println(PieceIterator.next().print());
		}
		PieceIterator = this.blackpieces.iterator();
		while(PieceIterator.hasNext()){
			System.out.println(PieceIterator.next().print());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((blackpieces == null) ? 0 : blackpieces.hashCode());
		result = prime * result + Arrays.hashCode(board);
		result = prime * result
				+ ((whitepieces == null) ? 0 : whitepieces.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (blackpieces == null) {
			if (other.blackpieces != null)
				return false;
		} else if (!blackpieces.equals(other.blackpieces))
			return false;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (whitepieces == null) {
			if (other.whitepieces != null)
				return false;
		} else if (!whitepieces.equals(other.whitepieces))
			return false;
		return true;
	}
}
