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
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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
		return ("[" + this.x + ";" + this.y + "]"); 
	}

}
