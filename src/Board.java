public class Board {
	
	private Piece[][] board = new Piece[8][8];
	
	private void init() {
		for (int x = 0 ; x < 7; x += 2 ) {
			board[x][0] = new Piece( new Coordinate(x,0), Color.BLACK );
		}
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][1] = new Piece( new Coordinate(x,1), Color.BLACK );
		}
		for (int x = 0 ; x < 8; x += 2 ) {
			board[x][2] = new Piece( new Coordinate(x,2), Color.BLACK );
		}
		
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][5] = new Piece( new Coordinate(x,5), Color.WHITE );
		}
		for (int x = 0 ; x < 8; x += 2 ) {
			board[x][6] = new Piece( new Coordinate(x,6), Color.WHITE );
		}
		for (int x = 1 ; x < 8; x += 2 ) {
			board[x][7] = new Piece( new Coordinate(x,7), Color.WHITE );
		}
	}
	
	Board(){
		init();
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
	
	public Piece takePiece(Coordinate c) {
		Piece p = board[c.x()][c.y()];
		board[c.x()][c.y()] = null;
		return p;
	}
	
	public void removePice(Coordinate c) {
		board[c.x()][c.y()] = null;
	}
	
	public void placePice(Piece p){
		Coordinate c = p.getCoordinate();
		board[c.x()][c.y()] = p;
	}
}
