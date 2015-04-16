package Checkers;
public class Piece {

	private static int count = 0;
	private int value = 1;
	
	private Coordinate c;
	private Color col = Color.WHITE;
	private boolean promoted = false;
	
	Piece(){
		Piece.count++;
	}
	
	Piece(Coordinate c, Color col){
		this();
		this.c = new Coordinate(c);
		this.col = col;
		this.promoted = false;
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
	
	public boolean isPromoted(){
		return this.promoted;
	}
	
	public void promote() {
		this.promoted = true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((col == null) ? 0 : col.hashCode());
		result = prime * result + value;
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
		Piece other = (Piece) obj;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (col != other.col)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
}
