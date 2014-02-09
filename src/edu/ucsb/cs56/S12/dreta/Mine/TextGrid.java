package edu.ucsb.cs56.S12.dreta.Mine;

/**
 * CLI MineSweeper Hidden (Functioning) Class
 */

public class TextGrid implements Interface
{

    // instance variables
    private char[][] grid = new char[10][10];
    private char[][] map = new char[10][10];
    
    /**
     * Default constructor for objects of class TextGrid
     */
    public TextGrid() {
	setZero();	
	for(int i = 0; i < 10; i++){
	    blankToMine();
	}
	insertNums();
	mapMaker(map);
    }

    /**
     * Sets all cells in the grid to zero.
     */

    public void setZero(){ 
	for(int i = 0;i < 10;i++){
	    for(int j = 0; j < 10;j++){
		grid[i][j] = '0';
	    }
	}
	return;
    }

    /**
     * Finds a random Empty cell and makes it a Mine
     */

    public void blankToMine(){ 
	int spotX = (int) (100.0 * Math.random());
	int a = spotX/10;
	int b = spotX%10;
	if(grid[a][b] != 'X'){
	    grid[a][b] = 'X';
	}
	else
	    blankToMine(); 
	return;
    }

    /**
     * Finds all Mines in the grid and increases the number of its surrounding integer Cells by 1
     */

    public void insertNums(){ 
	for(int i = 0;i < 10;i++){
	    for(int j = 0; j < 10;j++){
		if(isMine(i*10+j)){
		    for(int k = i-1; k <= i+1; k++){
			for(int l = j-1; l <= j+1; l++){
			    if(k >= 0 && k <= 9 && l >= 0 && l <= 9 && !(isMine(k*10+l))){
				grid[k][l]++;}
			}
		    }
		}
	    }
	}
	return;
    }

    /**
     * Creates a map filled with question marks
     */

    public void mapMaker(char map[][]){ 
	for(int i = 0;i < 10;i++){
	    for(int j = 0; j < 10;j++){
		map[i][j] = '?';
	    }
	}
	return;
    }

    /**
     * Prints out the map in a table
     */

    public String toString() {
	final String borders = " ---------------------";
	final String line = "|";
	
	String game = "";

	game += "\n";
	game += "  0 1 2 3 4 5 6 7 8 9 ";
	game += "\n";
	game += borders;
	game += "\n";
	for(int i = 0;i < 10;i++){
	    game += i + line;
	    for(int j = 0; j < 10;j++){
		game += map[i][j];
		game += line;
	    }
	    game += "\n";
	}
	game += borders;

	return game;  
    }

    /**
     * Checks a cell to see if it has been opened
     */

    public boolean isOpen(int i) {
	if( i >= 0 && i <= 99){
	    if(map[i/10][i%10]  != '?')
		return true;
	    else
		return false;
    }
	else
	    throw new IllegalArgumentException("I don't know where this exists :(");
    }

    /**
     * Checks a cell to see if there is a grid underneath 
     */

    public boolean isMine(int i) {
	if( i >= 0 && i <= 99){
	    if(grid[i/10][i%10]  == 'X')
		return true;
	    else
		return false;
    }
	else
	    throw new IllegalArgumentException("I don't know where this exists :(");
    }

    /**
     * Check to see if a user placed a flag on that cell
     */

    public boolean isFlag(int i) {
	if( i >= 0 && i <= 99){
	    if(map[i/10][i%10]  == 'F')
		return true;
	    else
		return false;
    }
	else
	    throw new IllegalArgumentException("I don't know where this exists :(");
    }

    /**
     * Opens the cell
     */

    public void searchBox(int box){ 
	if(box >= 0 && box < 100){
	    char spot = grid[box/10][box%10];
	    if(isFlag(box)){
		System.out.println("You cannot search a Flaged box!");
	    }
	    else if(isMine(box)){ //opens a box that has a mine
		map[box/10][box%10] = 'X';
	    }
	    else if(isOpen(box)){
		System.out.println("You cannot search an opened box!");
	    }
	    else{
		if(spot == '0')
		    findAllZeros(box/10, box%10);
		else
		   map[box/10][box%10] =  spot;
	    }	
	}
	return;
    }
 
    /**
     * Places a flag on the cell
     */

    public void flagBox(int box){ 
	if(isFlag(box)){
	    System.out.println("This box is already flaged!");
	    return;
	}
	else if(isOpen(box)){
	    System.out.println("You cannot put a flag on an opened box!");
	    return;
	}
	else{
	    map[box/10][box%10] = 'F';
	    return;
	}
    }

    /**
     * Removes a flag on a cell that has one
     */

    public void deflagBox(int box){
	if(!(isFlag(box))){
	    System.out.println("That box does not have a flag on it!");
	    return;
	}
	else{
	    map[box/10][box%10] = '?';
	}
	return;
    }

    /**
     * Looks for surrounding numbers near the cell and opens them, repeats when find another zero
     */

    public void findAllZeros(int row, int col){
	for(int i = row-1; i <= row+1; i++){
	    for(int j = col-1; j <= col+1; j++){
		if(i >= 0 && i <= 9 && j >= 0 && j <= 9 && !(isMine(i*10+j)) && !(isOpen(i*10+j))){
		    map[i][j] = grid[i][j];
		    if(grid[i][j] == '0')
			findAllZeros(i,j);
		}
		
	    }
	}
    }

    /**
     * Updates the state of the game
     */

    public int gameStatus(int stat){ 
	if(stat == 0){ // runs only if player hasn't hit a mine
	    int correctPercent = 0;
	    for(int i = 0; i < 10;i++){
		for(int j = 0; j < 10; j++){
		    if(( isMine(i*10+j) && isFlag(i*10+j))||(grid[i][j] == map[i][j]))
			correctPercent++; //the map has the correct move for that cell
		}
	    }
	    if(correctPercent == 100) //all correct moves have been made
		stat++;
	}

	for(int i = 0; i<10;i++){
	    for(int j=0; j<10; j++){
		if(map[i][j] == 'X')
		    stat--;
	    }
	}

	return stat;
    }

    /**
     * Finds the current condition of a cell
     */

    public char getCell(int cell){
	return map[cell/10][cell%10];   
    }

}