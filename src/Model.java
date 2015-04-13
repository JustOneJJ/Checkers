public class Model {

	//private Board b = new Board();
	private GameState state = new GameState();

	public void move(Coordinate from, Coordinate to){
		Action a = new Action(from, to);
		System.out.println(a.print());
		this.state = this.state.getNextGameState(a);
		//this.state.printActions();
	}
	
	public String getBoardString(){
		return state.boardString();
	}
	
	public boolean isEmpty(Coordinate c){
		return this.state.isEmpty(c);
	}
}