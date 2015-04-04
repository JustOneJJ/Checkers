public class Piece {

	private static int count = 0;
	private final int value = 1;
	
	private Coordinate c;
	private Color col = Color.WHITE;
	
	public Piece(){
		Piece.count++;
	}
	
	public Piece(Coordinate c, Color col){
		this();
		this.c = new Coordinate(c);
		this.col = col;
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
	
	public int getCount() {
		return Piece.count;
	}

	public void setCoordinate(Coordinate c) {
		this.c = c;
	}

	public void setColor(Color col) {
		this.col = col;
	}
	
}
