import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
		
	private Model theModel;
	private View theView;
	
	Controller(Model theModel, View theView){
		this.theModel = theModel;
		this.theView = theView;
		
		this.theView.addBoardListener(new BoardListener());
	}

	class BoardListener implements ActionListener{
		private boolean status = true;
		private Coordinate from;
		
		public void actionPerformed(ActionEvent e){
			
			if(status){
				this.status = false;
				this.from = this.extractCoordinate(e.getActionCommand());
				theView.setTextField1(this.from.print());
			}else{
				this.status = true;
				Coordinate to = this.extractCoordinate(e.getActionCommand());
				theModel.move(this.from, to);
				String s = theModel.getBoardString();
				theView.setEmptyBoard();
				theView.setPieces(s);
			}
		}
		
		private Coordinate extractCoordinate(String s) {
			return new Coordinate( s.charAt(0)-48, s.charAt(1)-48);
		}
	}
	
}
