package MVC;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.*;

public class View implements java.util.Observer{
		
	private JLabel lab = new JLabel("This is North");
	private JButton button1 = new JButton("New game");
	private JButton button2 = new JButton("Random agent");
	private JButton button3 = new JButton("Minimax agent");
	private JButton button4 = new JButton("botVSbot");
	private JButton[] bArray = new JButton[64];
	JFrame frame = new JFrame("Checkers");

	public View(){	    
		//frame = new JFrame("simple MVC");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel parent = new JPanel(new BorderLayout());
	    Panel p = new Panel();
        p.add(lab);
        p.add(this.button1);
        p.add(this.button2);
        p.add(this.button3);
        p.add(this.button4);
        parent.add("North",p);
        
        JPanel newpanel = new JPanel(new GridBagLayout());
	    JPanel panel = new JPanel(new GridLayout(8,8,0,0));
	    
	    boolean change = true;
  
	    for(int i=0 ; i<64 ; i++){
	    	ImageIcon b;
	    	
	    	if (change) {
	    		b = new ImageIcon("img/gray.gif");
	    		change = false;
	    		if (i == 15 || i == 31 || i == 47 ){
	    			change = true;
	    		}
	    	}else{
	    		b = new ImageIcon("img/black.gif");
	    		change = true;
	    		if (i == 7 || i == 23 || i == 39 || i == 55){
	    			change = false;
	    		}	
	    	}
	    	    	
	        bArray[i] = new JButton(b);	        
	        bArray[i].setActionCommand(i%8+ ""+ i/8);	        	        
	        bArray[i].setPreferredSize(new Dimension(64, 64));
	        panel.add(bArray[i]);
	    }
	    
	    newpanel.add(panel, new GridBagConstraints());
	    panel.setPreferredSize(new Dimension(512,512));
	    parent.add("Center", newpanel);
	        
	    frame.setContentPane(parent);
	    frame.pack();
	    frame.setSize(530,530);
	    frame.setMinimumSize(new Dimension(600,600));
	    frame.setVisible(true);
	}
	
	public void update(Observable obs, Object obj) {
		System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());
		this.setEmptyBoard();
		this.setPieces( ((String)obj) );
    	}
	
	public void setTextField1(String s){
		this.lab.setText(s);
	}
	
	void addBoardListener(ActionListener listenForButton ){
		for(int i = 0 ; i < 64; i++){
			this.bArray[i].addActionListener(listenForButton);
		}
	}
	
	void addButton1Listener(ActionListener listenForButton ){
		this.button1.addActionListener(listenForButton);
	}
	
	void addButton2Listener(ActionListener listenForButton ){
		this.button2.addActionListener(listenForButton);
	}
	
	void addButton3Listener(ActionListener listenForButton ){
		this.button3.addActionListener(listenForButton);
	}
	
	void addButton4Listener(ActionListener listenForButton ){
		this.button4.addActionListener(listenForButton);
	}
	
	public void setPieces(String s){
		for(int i = 0; i<64; i++){
			char c = s.charAt(i);
			if(c == 'B'){
				ImageIcon b = new ImageIcon("img/blackpiece.gif");
				this.bArray[i].setIcon(b);
			}else if(c == 'W'){
				ImageIcon b = new ImageIcon("img/whitepiece.gif");
				this.bArray[i].setIcon(b);
			}else if(c == '1'){
				ImageIcon b = new ImageIcon("img/whitePromoted.gif");
				this.bArray[i].setIcon(b);
			}else if(c == '2'){
				ImageIcon b = new ImageIcon("img/blackPromoted.gif");
				this.bArray[i].setIcon(b);
			}
		}
	}
	
	public void setEmptyBoard(){
		String s = "1010101001010101101010100101010110101010010101011010101001010101";
		for(int i = 0; i<64; i++){
			char c = s.charAt(i);
			if(c == '0'){
				ImageIcon b = new ImageIcon("img/black.gif");
				this.bArray[i].setIcon(b);
			}else if(c == '1'){
				ImageIcon b = new ImageIcon("img/gray.gif");
				this.bArray[i].setIcon(b);
			}
			
		}
	}
	
	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} //windowClosing()
	} //CloseListener
	
}
