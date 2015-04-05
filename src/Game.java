public class Game {

	public static void main(String[] args){
		
		View theView = new View();
		Model theModel = new Model();
		
		Controller theController = new Controller(theModel, theView);
		
		String init = theModel.getBoardString();
		theView.setEmptyBoard();
		theView.setPieces(init);
		
		theView.setVisible(true);
	}
}