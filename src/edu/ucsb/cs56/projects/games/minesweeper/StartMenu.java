package edu.ucsb.cs56.projects.games.minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartMenu {
	JButton b1;
	JButton b2;
	JFrame frame;
	JPanel menu;
	JPanel game;
	public StartMenu(){
       frame = new JFrame() ;
       frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;

		menu = new JPanel(new BorderLayout());
		b1 = new JButton("New Game");
		//b1.setPreferredSize(b1.getWidth(),300);
		b1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                newGame();
            }
        });
		b2 = new JButton("Help");
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                menu.setVisible(false);
            }
        });
        menu.setPreferredSize(new Dimension(650,600));
        menu.setBackground(Color.green);
		menu.add(b1,BorderLayout.NORTH);
		menu.add(b2,BorderLayout.SOUTH);
		frame.getContentPane().add(menu);
		frame .applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame. setSize(650,600) ;
		frame. setVisible(true) ;
   }
   
    public void newGame(){
		game = new JPanel(new BorderLayout());
		Interface grid = new GUIGrid();
		Messager m = new SOMessager();
		
        JButton b = new JButton("click me");
		b.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                menu();
            }
        });
        game.add(b,BorderLayout.WEST);
		
		
		
		MineComponent mc = new MineComponent(grid, m);
		game.add(mc,BorderLayout.EAST);
		menu.setVisible(false);
		frame.getContentPane().add(game);


	}
	
	public void menu(){
		menu.setVisible(true);
		game.setVisible(false);
		//frame.getContentPane().add(menu);
		
   
   
}
}