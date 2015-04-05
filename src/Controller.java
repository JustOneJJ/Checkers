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
		
		public void actionPerformed(ActionEvent e){
			theView.setTextField1(e.getActionCommand());
		}
	}
}
