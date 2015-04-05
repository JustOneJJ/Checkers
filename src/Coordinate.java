public final class Coordinate {
	private int x;
	private int y;
	
	public Coordinate() {
		this(0, 0);
	}
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//copy constructor
	public Coordinate(final Coordinate another) {
		this.x = another.x();
		this.y = another.y();
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String print() {
		return (this.x + "" + this.y); 
	}

}
