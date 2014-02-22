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
       StartMenu frame = new StartMenu();
    }
}
