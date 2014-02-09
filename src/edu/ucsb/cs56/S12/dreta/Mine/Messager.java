package edu.ucsb.cs56.S12.dreta.Mine;

/**
 An interface to represent a place to send messages.
 Used in MineCompoment
  
   @author Mine
   @see MineComponent
 */
public interface Messager
{
    /** 
	Get this string to the user

	@param msg String to send to the user

     */

    public void append(String msg);

}
