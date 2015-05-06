package Checkers;
public class Action implements Cloneable{

	private Coordinate source;
	private Coordinate destination;
	
	public Action(Coordinate src, Coordinate dest) {
		//this.source = new Coordinate(src);
		try {
			this.source = (Coordinate)src.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.destination = new Coordinate(dest);
	}
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
        Action cloned = (Action)super.clone();
        cloned.setSource( (Coordinate)cloned.getSource().clone() );
        cloned.setDestination( (Coordinate)cloned.getDestination().clone() );
        return cloned;
    }
	
	public Action(Action other) {
		setSource(other.getSource());
		setDestination(other.getDestination());
	}
	
	public Coordinate getSource() {
		return source;
	}

	public Coordinate getDestination() {
		return destination;
	}

	public void setSource(Coordinate source) {
		this.source = new Coordinate(source);
	}

	public void setDestination(Coordinate destination) {
		this.destination = new Coordinate(destination);
	}

	public String print() {
		return ( "[ "+ this.source.print() + " " + this.destination.print() + " ]" );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Action other = (Action) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
}
