// I use this for on the fly testing
public class Test {
	
	public static void main(String[] args) {
		
	Coordinate c1 = new Coordinate(1,1);
	Coordinate c2 = new Coordinate(2,2);
	
	Action a = new Action(c1, c2);
	
	Action a2 = new Action(a);
	
	System.out.println(a.print());
	System.out.println(a2.print());
	
	c1.setX(3);
	
	a2.setSource(new Coordinate (31,41));
	System.out.println(a.print());
	System.out.println(a2.print());
	
	Piece p1 = new Piece(c1, Color.WHITE);
	Piece p2 = new Piece(p1);
	
	Coordinate c3 = new Coordinate(5,5);
	p1.setCoordinate(c3);
	c3.setY(15);
	p1.setColor(Color.BLACK);
	
	System.out.println(p1.print());
	System.out.println(p2.print());
	System.out.println(Piece.getCount());	
	}
	
	
}
