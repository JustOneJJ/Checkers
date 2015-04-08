// I use this for on the fly testing
public class Test {
	
	public static void main(String[] args) {
		
	Coordinate c1 = new Coordinate(6,2);
	Coordinate c2 = new Coordinate(6,3);
	
	Board b1 = new Board();
	Board b2 = new Board(b1);
	System.out.println(b1.print());
	b1.printPieces();
	System.out.println(b1.equals(b2));
	System.out.println(b2.equals(b1));
	b1.removePiece(c1);
	System.out.println(b1.equals(b2));
	
	}
	
	
}
