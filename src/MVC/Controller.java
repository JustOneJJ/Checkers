package MVC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import Agents.MiniMaxAgent;
import Agents.RandomAgent;
import Checkers.Coordinate;

public class Controller {
		
	private Model theModel;
	private View theView;
	
	public Controller(Model theModel, View theView){
		this.theModel = theModel;
		this.theView = theView;
		
		this.theView.addBoardListener(new BoardListener());
		this.theView.addButton1Listener(new NewGameListener());
		this.theView.addButton2Listener(new RandomAgentListener());
		this.theView.addButton3Listener(new MinimaxAgentListener());
		this.theView.addButton4Listener(new BotListener());
	}

	class BoardListener implements ActionListener{
		private boolean status = true;
		private Coordinate from;
		
		public void actionPerformed(final ActionEvent e){
			
			if(status){
				//this.status = false;
				this.from = this.extractCoordinate(e.getActionCommand());
				theView.setTextField1(this.from.print());			    
				if( !theModel.isEmpty(this.from)){
					this.status = false;
				}
			}else{
				this.status = true;
				//Coordinate to = this.extractCoordinate(e.getActionCommand());
				//theModel.move(this.from, to);
				//==========
			    SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {
			        @Override
			        protected String doInBackground() throws Exception { 
			        	//theModel.moveAgent();
			        	Coordinate to = extractCoordinate(e.getActionCommand());
			        	theModel.move(from, to);
			        	return "DONE";
			        }
			        @Override
			        protected void done() {
			            try {
			            	//theView.setTextField1();
			            } catch (Exception e) {
			                //ignore
			            }
			        }
			    };      
			    worker.execute();
			    //=========
			}
		}
		
		private Coordinate extractCoordinate(String s) {
			return new Coordinate( s.charAt(0)-48, s.charAt(1)-48);
		}
	}
	
	class NewGameListener implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			theModel.newGame(); 
			theView.setTextField1("new game started");
		}
	}
	
	class RandomAgentListener implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			theModel.setAgent(new RandomAgent());
			theView.setTextField1("playing versus random agent");
		}
	}
	
	class MinimaxAgentListener implements ActionListener{
		public void actionPerformed(final ActionEvent e){
			theModel.setAgent(new MiniMaxAgent(4));
			theView.setTextField1("playing versus Minimax agent");
		}
	}
	
	class BotListener implements ActionListener{
		public void actionPerformed(final ActionEvent e){
		    SwingWorker<String, Object> worker = new SwingWorker<String, Object>() {
		        @Override
		        protected String doInBackground() throws Exception { 
		        	//theModel.moveAgent();
		        	theModel.botVSbot();
					theView.setTextField1("BotVSBot");
		        	return "DONE";
		        }
		        @Override
		        protected void done() {
		            try {
		            	//theView.setTextField1();
		            } catch (Exception e) {
		                //ignore
		            }
		        }
		    };      
		    worker.execute();
		    //=========
			
		}
	}
	
}
