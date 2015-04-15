package Checkers;
// I use this for on the fly testing
public class Test {
	
	public static void main(String[] args) {
		
	Coordinate c1 = new Coordinate(1,5);
	Coordinate c2 = new Coordinate(0,4);
	
	GameState state = new GameState();
	state.printActions();
	Action a1 = new Action(c1, c2);
	System.out.println(state.hasAction(a1));
	state = state.getNextGameState(a1);
	state.printActions();
	
	Board b1 = new Board();
	b1.printPieces();
	
	Board b2 = new Board(b1);
	b2.printPieces();
	
	}
	
	
}
