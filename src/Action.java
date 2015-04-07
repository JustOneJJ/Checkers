public class Action {

	private Coordinate source;
	private Coordinate destination;
	
	Action(Coordinate src, Coordinate dest) {
		this.source = new Coordinate(src);
		this.destination = new Coordinate(dest);
	}
	
	Action(Action other) {
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
	
}
