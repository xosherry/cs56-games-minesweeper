package edu.ucsb.cs56.projects.games.minesweeper;
import javax.swing.*;
import java.awt.ComponentOrientation;

/** MineGUI.java is a GUI interface for MineSweeper that uses
    System.out as the destination for messages.

     @author Daniel Reta
     @version CS56, Spring 2012, UCSB
*/

public class MineGUI {

    /** main method to fire up a JFrame on the screen  
          @param args not used
     */

    public static void main (String[] args) {
       //JFrame frame = new JFrame() ;
       //frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
       StartMenu frame = new StartMenu();
	   
       Interface game = new GUIGrid();
       Messager m = new SOMessager();

       /*JPanel jp = new JPanel();
       JButton b = new JButton("click me");
       jp.add(b);*/
	   
       
       MineComponent mc = new MineComponent(game, m);
       //jp.add(mc);
	   //frame.add(mc);
       //frame.getContentPane().add(mc);

       //frame .applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
       //frame. setSize(650,600) ;
       //frame. setVisible(true) ;
    }
}
