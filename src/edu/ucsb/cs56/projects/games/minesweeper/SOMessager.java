package edu.ucsb.cs56.projects.games.minesweeper;

/**
 An interface to represent a place to send messages.  Used in MineComponent
  
   @author Daniel Reta
   @see MineComponent
 */
public class SOMessager implements Messager
{
    /** 
	Get this string to the user via System.out

	@param msg message to display to user

    */

    public void append(String msg) {
	System.out.println(msg);
    }

}