package edu.ucsb.cs56.S12.dreta.Mine;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event. ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboPopup;

/**
 * An Swing component for playing MineSweeper

   @author Daniel Reta
   @version CS56 Spring 2012
   @see GUIGrid
 */
public class MineComponent extends JComponent 
{
    private Interface game;
    private Messager m;
    

    private JButton[][] buttons = new JButton[10][10];

    /** Constructor
	
	@param game an object that implements the Inferface interface to keep track
	            of the moves in each game, ensuring the rules are followed and detecting
	            when someone has won.
	@param md an object that implements the MessageDestination interface.  This is just
	           a place to send any messages that need to be communicated to the user.
		   Making this separate allows a user of this components to decide to
		   send those messages to the console, or to a variety of different
		   swing Widgets, or even to a web page, as needed.
    */
       
    public MineComponent(Interface game, Messager m) {
	super(); // is this line necessary?  what does it do?
	
	this.game = game;  // the Interface game
	this.m = m;  // a place we can write messages to

	// note columns ignored when rows are set
	// number of columns is implicit from the number of things added

	this.setLayout(new GridLayout(10,0)); 
	for(int i=0; i<10; i++) {
	    for(int j=0; j<10; j++){
		String label=String.format("%d",i*10+j);
		JButton jb = new JButton(label);
		buttons[i][j] = jb;
		jb.addMouseListener(new ButtonListener(i*10+j));
		jb.setFont(new Font("sansserif",Font.BOLD,12));
		this.add(jb);
	    }
	}
	
    }    
 

    /**
     * Inner Class, responds to the event source.
    */

    class ButtonListener extends MouseAdapter
    {
	private int num;

	public ButtonListener(int i) {
	    super();  
	    this.num = i;
	}

	/**
	   Places player's symbol on button, checks for a winner or tie 
	   
	   @param event when a button is clicked

	 */

	int status = 0;

	public void mouseReleased(MouseEvent event) {
	    if(game.gameStatus(status) == 0){
		if(event.getButton() == MouseEvent.BUTTON1){
		    game.searchBox(num);
		    for(int i=0; i<10; i++){
			for(int j=0; j<10; j++){
			    JButton jb = buttons[i][j];
			    if(game.getCell(i*10+j) != '?'){
				jb.setFont(new Font("sansserif",Font.BOLD,36));
				jb.setText(Character.toString(game.getCell(i*10+j)));
			    }
			}
		    }

		status = game.gameStatus(status);

		if (status == -1){
		    m.append("You lose!!\n");
		}
		else if (status == 1){
		    m.append("You win!!\n");
		}
		
	    }

	    else if(event.getButton() == MouseEvent.BUTTON3){
		if(game.isFlag(num)){
		    game.deflagBox(num);
		    JButton jb = buttons[num/10][num%10];
		    int digit1 = num%10;
		    int digit2 = num/10;
		    char c1 = (char)(digit1 + 48);
		    char c2 = (char)(digit2 + 48);
		    String s1 = Character.toString(c1);
		    String s2 = Character.toString(c2);
		    String s = s1 + s2;
		    jb.setFont(new Font("sansserif",Font.BOLD,12));
		    jb.setText(s); 
		}	    
		else if(!(game.isOpen(num))){
		    game.flagBox(num);
		    JButton jb = buttons[num/10][num%10];
		    jb.setFont(new Font("sansserif",Font.BOLD,36));
		    jb.setText("F"); 
		}

		int status = game.gameStatus(0);

		if (status == 1){
		    m.append("You win!!\n");
		}
	    }
	    }
	}
	
    }
}

