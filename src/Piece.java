public class Piece {

	private static int count = 0;
	private int value = 1;
	
	private Coordinate c;
	private Color col = Color.WHITE;
	
	Piece(){
		Piece.count++;
	}
	
	Piece(Coordinate c, Color col){
		this();
		this.c = new Coordinate(c);
		this.col = col;
	}
	
	//copy constructor
	public Piece(Piece other) {
		Piece.count++;
		this.setCoordinate(other.getCoordinate());
		this.col = other.getColor();
		this.value = other.getValue();
	}
	
	public boolean isInPosition(Coordinate c ){
		if ((c.x() == this.c.x()) && (c.y() == this.c.y())) {
			return true;
		}
		return false;
	}
	
	public boolean isInPosition(int x, int y){
		if ((x == this.c.x()) && (y == this.c.y())) {
			return true;
		}
		return false;		
	}

	public int getValue() {
		return value;
	}

	public Coordinate getCoordinate() {
		return c;
	}

	public Color getColor() {
		return col;
	}
	
	public static int getCount() {
		return Piece.count;
	}

	public void setCoordinate(Coordinate c) {
		this.c = new Coordinate(c);
	}

	public void setColor(Color col) {
		this.col = col;
	}
	
	public String print() {
		return (this.c.print() + " " + this.value + " " + this.col);
	}
	
}
