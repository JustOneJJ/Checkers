package Checkers;

import MVC.Controller;
import MVC.Model;
import MVC.View;

public class Game {

	public static void main(String[] args){
		
		View theView = new View();
		Model theModel = new Model();
		
		theModel.addObserver(theView);
		
		@SuppressWarnings("unused")
		Controller theController = new Controller(theModel, theView);
		
		String init = theModel.getBoardString();
		theView.setEmptyBoard();
		theView.setPieces(init);
		
		//theView.setVisible(true);
	}
}