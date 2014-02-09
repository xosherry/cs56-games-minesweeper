package edu.ucsb.cs56.S12.dreta.Mine;
import javax.swing.JFrame;
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
       JFrame frame = new JFrame() ;
       frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
       
       Interface game = new GUIGrid();
       Messager m = new SOMessager();

       MineComponent mc = new MineComponent(game, m);
       frame.getContentPane().add(mc);

       frame .applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
       frame. setSize(650,600) ;
       frame. setVisible(true) ;
    }
}
