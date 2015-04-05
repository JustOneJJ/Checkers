public class Model {

	private Board b = new Board();

	public void move(Coordinate from, Coordinate to){
		Piece p = b.takePiece(from);
		p.setCoordinate(to);
		b.placePice(p);
	}
	
	public String getBoardString(){
		return b.print();
	}
}