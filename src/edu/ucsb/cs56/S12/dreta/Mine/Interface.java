package edu.ucsb.cs56.S12.dreta.Mine;
/**
 An interface for MineSweeper games
  
   @author Dnaiel Reta
   @see GUIGrid
   @see MineComponent
   @see TextGame
 */
public interface Interface 
{
 
    public boolean isOpen(int i) throws IllegalArgumentException;
    public boolean isMine(int i) throws IllegalArgumentException;
    public boolean isFlag(int i) throws IllegalArgumentException;

    public void searchBox(int box);
    public void flagBox(int box);
    public void deflagBox(int box);

    public int gameStatus(int stat);

    public char getCell(int cell);

    public String toString();
}
