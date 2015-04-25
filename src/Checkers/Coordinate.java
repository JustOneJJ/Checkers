package Checkers;
public final class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(){
		this(0, 0);
	}
	
	public Coordinate(int x, int y){
		if (x < -2 || x > 9){
			try {
				throw new InvalidX("x is invalid: " + x);
			} catch (InvalidX e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.x = x;
		if (y < -2 || y > 9){
			try {
				throw new InvalidY("y is invalid: " + y);
			} catch (InvalidY e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	public void setX(int x) throws CoordinateExcepion {
		if (x < -2 || x > 9){
			throw new InvalidX("x is invalid: " + x);
		}
		this.x = x;
	}

	public void setY(int y)  throws CoordinateExcepion{
		if (y < -2 || y > 9){
			throw new InvalidY("x is invalid: " + y);
		}
		this.y = y;
	}
	
	public String print() {
		return ("[" + this.x + ";" + this.y + "]"); 
	}
	
	public boolean isValid() {
		if( this.x < 0 || this.y < 0) {
			return false;
		}
		if (this.x > 7 || this.y > 7) {
			return false;
		}
		return true;
	}

}
