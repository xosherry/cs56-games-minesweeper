package edu.ucsb.cs56.projects.games.minesweeper;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

/** MineGUI.java is a base that calls all GUI objects and handles tasks
    such as pausing the game, creating the GUI, making the escape key functional,
	and allowing for a new game.

     @author David Acevedo
     @version 2015/03/04 for lab07, cs56, W15
	 @see MineGUI
*/

public class MineGUI {
    JToolBar toolbar;
    JButton mainMenu;
    JButton quitMine;
    JButton inGameHelp;
    JButton refresh;
	JButton easyGame;
	JButton medGame;
	JButton hardGame;
	JButton load; //loads game
	JButton help;	//Main Menu Help Button
	JButton save;
	JFrame frame;	//The frame is where all the good stuff is displayed e.g. Everything
	JPanel menu;	//Menu Panel, initial panel at initial creation of the game e.g. Main Menu
	JPanel game; 	//Game Panel, where the game is played
	boolean inUse; //if game is started and in use
    
    
	MineComponent mc; //MineComponent is the actual layout of the game, and what makes the game function
	JLabel status;		//the game status label that is displayed during the game

	/** no-arg constructor which creates the GUI of the Main Menu for Minesweeper
	 *  This menu includes a start and help button
     */
	public MineGUI() {
        System.out.println("called MineGUI");
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMainMenu();

		frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.setSize(650, 600);
        System.out.println("next line will make GUI visible");
		frame.setVisible(true);
	}

	/** 
	 *  Starts a new Minesweeper game from the main menu
	 */
	public void newGame() {
        
        System.out.println("called new game");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		game = new JPanel(new BorderLayout());			//our game panel e.g. where everything will be put in for this display
		Grid grid = new Grid(true);
		mc = new MineComponent(grid, this);	//creates our game interface
		frame.setSize((65*mc.getGrid().getSize() > screenSize.width
						? screenSize.width : 65*mc.getGrid().getSize()),
					  (60*mc.getGrid().getSize() > screenSize.height-40
						? screenSize.height-40 : 60*mc.getGrid().getSize()));

        JToolBar toolbar = new JToolBar("In-game toolbar");
        createButtons(toolbar);
        game.add(mc);							//puts the game in the jPanel
		game.add(toolbar,BorderLayout.NORTH);	//puts the game toolbar at the top of the screen
		menu.setVisible(false);					//puts the menu away
		frame.getContentPane().add(game);
        inUse = true;
	}

	public void newGame(int difficulty) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		game = new JPanel(new BorderLayout());			//our game panel e.g. where everything will be put in for this display
        Grid grid = new Grid(true, difficulty);
		mc = new MineComponent(grid, this);	//creates our game interface
		frame.setSize((65*mc.getGrid().getSize() > screenSize.width
						? screenSize.width : 65*mc.getGrid().getSize()),
					  (60*mc.getGrid().getSize() > screenSize.height-30
						? screenSize.height-30 : 60*mc.getGrid().getSize()));
        
        JToolBar toolbar = new JToolBar("In-game toolbar");
        createButtons(toolbar);
        game.add(mc);//puts the game in the jPanel
        
		game.add(toolbar,BorderLayout.NORTH);	//puts the game toolbar label at the top of the screen
		menu.setVisible(false);					//puts the menu away
		frame.getContentPane().add(game);
        inUse = true;
	}
	
	/**
	 *  Creates the main menu, the menu when you launch the application
	 */
	public void createMainMenu(){
        frame.setSize(650, 600);
		menu = new JPanel(new GridLayout(4,0));		//our 2 section grid layout for our main menu

		easyGame = new JButton("New Easy Game");
		medGame = new JButton("New Medium Game");
		hardGame = new JButton("New Hard Game");
		help = new JButton("Help");
		load = new JButton("load last game");
		addActionListener(easyGame, "New Easy Game");
		addActionListener(medGame, "New Medium Game");
		addActionListener(hardGame, "New Hard Game");
		addActionListener(help, "Help");
		addActionListener(load,"Load");	
		menu.add(easyGame);
		menu.add(medGame);
		menu.add(hardGame);
		menu.add(load);
		menu.add(help);
		frame.getContentPane().add(menu);
        inUse = false;
	}
    
    public void createButtons(JToolBar toolbar){
        //make buttons
        
        refresh = new JButton("Reset Game");
        mainMenu = new JButton("Main Menu");
        //quitMine = new JButton("Quit Minesweeper"); //based on principle that we complete toolbar
        inGameHelp = new JButton("Help");
        
        addActionListener(refresh, "Reset Game");
        addActionListener(mainMenu, "Main Menu");
        addActionListener(inGameHelp, "Help");
        toolbar.add(mainMenu);
        toolbar.add(refresh);
        toolbar.add(inGameHelp);
    }
	
	/**
	 *  Sets a message on the status bar on the top of the GUI
	 *
	 *	@param status - message you wish the status bar to display
	 */
	public void setLabel(String status){
		this.status.setText(status);
	}
	
	/**
	 * Creates a specified task for the buttons
	 * @param button - The JButton that you want to assign a task to
	 * @param action - the action you would like to give the button
	 */
	public void addActionListener(JButton button, String action){
		if(action == "New Easy Game")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						newGame(0);
			}
		});	
		}
		else if(action == "New Medium Game")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						newGame(1);
			}
		});	
		}
		else if(action == "New Hard Game")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						newGame(2);
			}
		});	
		}
		else if(action == "Main Menu")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
                        System.out.println("save");
                        save();
                        game.setVisible(false);
                        inUse = false;
                        menu.setVisible(true);
			}
		});	
		}
        else if(action == "Reset Game")
            {
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Execute when button is pressed
                        createMainMenu();
                        int diff = mc.getGrid().getSize();
                        System.out.println("game size = " + diff);
                        if (diff ==10)
                        {
                            newGame(0);
                        }
                        else if (diff ==15)
                        {
                            newGame(1);
                        }
                        else if (diff ==20)
                        {
                            newGame(2);
                        }

            }
        });
        }
		else if(action == "Help")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						if(inUse==false){
							HelpScreen helpScreen=new HelpScreen(frame, menu);
							}
						else{
							HelpScreen helpScreen = new HelpScreen(frame, game);
							}
			}
		});	
	}
		else if(action == "Load"){
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Execute when button is pressed
					System.out.println("load");
					load();
				}	
			});
		}
		else if(action == "Save"){
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Execute when button is pressed
					System.out.println("save");
					save();
				}	
			});	
		}
}
	public void load(){
		try {
			FileInputStream fileStream = new FileInputStream("MyGame.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			Object one;
			try {
				one = os.readObject();
				Grid grid=(Grid)one;
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				game = new JPanel(new BorderLayout());			//our game panel e.g. where everything will be put in for this display
				mc = new MineComponent(grid, this);//creates our game interface
                int gridSize = mc.getGrid().getSize();
				frame.setSize((65*gridSize > screenSize.width
								? screenSize.width : 65*gridSize),
							  (60*gridSize > screenSize.height-30
								? screenSize.height-30 : 60*gridSize));
				game.add(mc);							//puts the game in the jPanel
                JToolBar toolbar = new JToolBar("In-game toolbar");
                createButtons(toolbar);
				game.add(toolbar,BorderLayout.NORTH);	//puts the game toolbar at the top of the screen
				menu.setVisible(false);					//puts the menu away
				frame.getContentPane().add(game);
				mc.refresh();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void save(){
		try{
			FileOutputStream fileStream=new FileOutputStream("MyGame.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(mc.getGrid());
			os.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
    public static void main (String[] args) {
	MineGUI frame = new MineGUI();
    }
}
