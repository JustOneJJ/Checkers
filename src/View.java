import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lab = new JLabel("This is North");
	private JButton[] bArray = new JButton[64];

	View(){	    
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel parent = new JPanel(new BorderLayout());
	    Panel p = new Panel();
        p.add(lab);
        p.add(new Button("this will do something"));
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
	        
	    this.setContentPane(parent);
	    this.pack();
	    this.setSize(530,530);
	    this.setMinimumSize(new Dimension(600,600));
	}
	
	public void setTextField1(String s){
		this.lab.setText(s);
	}
	
	void addBoardListener(ActionListener listenForButton ){
		for(int i = 0 ; i < 64; i++){
			this.bArray[i].addActionListener(listenForButton);
		}
	}
	
	void setPieces(String s){
		for(int i = 0; i<64; i++){
			char c = s.charAt(i);
			if(c == 'B'){
				ImageIcon b = new ImageIcon("img/blackpiece.gif");
				this.bArray[i].setIcon(b);
			}else if(c == 'W'){
				ImageIcon b = new ImageIcon("img/whitepiece.gif");
				this.bArray[i].setIcon(b);
			}
		}
	}
	
	void setEmptyBoard(){
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
	
}
