package edu.ucsb.cs56.projects.games.minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** StartMenu.java is a base that calls all GUI objects and handles tasks
    such as pausing the game, creating the GUI, making the escape key functional,
	and allowing for a new game.

     @author David Acevedo
     @version CS56, Winter 2014, UCSB
*/

public class StartMenu {
	JButton newGame;
	JButton help;
	JButton newGamePause;
	JButton helpPause;
	JButton resume;
	JFrame frame;
	JPanel menu;
	JPanel game;
	JPanel pause;

	/** no-arg constructor which creates the GUI of the Main Menu for Minesweeper
	 *  This menu includes a start and help button
     */
	public StartMenu() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPausePanel();
		createMainMenu();

		frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.setSize(650, 600);
		frame.setVisible(true);
	}

	/** 
	 *  Starts a new Minesweeper game from the main menu
	 */
	public void newGame() {
		game = new JPanel(new BorderLayout());
		escapeListener();
		JLabel jl= new JLabel("Press esc to pause game");
		JPanel jp= new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		Interface grid = new GUIGrid();
		Messager m = new SOMessager();
		
		MineComponent mc = new MineComponent(grid, m);
		jp.add(jl);
		jp.add(mc);
		game.add(jp);
		menu.setVisible(false);
		pause.setVisible(false);
		frame.getContentPane().add(game);
	}

	/**
	 *  Pauses the game and brings up the pause menu
	 */
	public void pause() {
		frame.getContentPane().add(pause);
		game.setVisible(false);
		pause.setVisible(true);
	}
	
	/**
	 *  Resumes the game and takes you back to the game
	 */
	public void resume() {
		pause.setVisible(false);
		game.setVisible(true);
	}
	
	/**
	 *  creates a Pause menu for when you want to pause the game
	 */
	public void createPausePanel(){
		pause = new JPanel(new GridLayout(3,0));
		newGamePause = new JButton("New Game");
		helpPause = new JButton("Help");
		resume = new JButton("Resume");
		addActionListener(newGamePause, "New Game");
		addActionListener(helpPause, "Help");
		addActionListener(resume, "Resume");
		pause.setPreferredSize(new Dimension(650, 600));
		pause.add(resume);
		pause.add(newGamePause);
		pause.add(helpPause);
		frame.getContentPane().add(pause);
	}
	
	/**
	 *  Creates the main menu, the menu when you launch the application
	 */
	public void createMainMenu(){
		menu = new JPanel(new GridLayout(2,0));

		newGame = new JButton("New Game");
		help = new JButton("Help");

		addActionListener(newGame, "New Game");
		addActionListener(help, "Help");


		menu.setPreferredSize(new Dimension(650, 600));
		menu.setBackground(Color.green);
		menu.add(newGame);
		menu.add(help);
		frame.getContentPane().add(menu);	
	}
	
	/**
	 *  Allows the escape button to function as a pause key
	 */
	public void escapeListener() {
		AbstractAction escapeAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		};
		String key = "ESCAPE";
		KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
		game.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, key);
		game.getActionMap().put(key, escapeAction);

	}
	
	/**
	 * Creates a specified task for the buttons
	 * @param button - The JButton that you want to assign a task to
	 * @param action - the action you would like to give the button
	 */
	public void addActionListener(JButton button, String action){
		if(action == "New Game")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						newGame();
			}
		});	
	}
		else if(action == "Help")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						pause();
			}
		});	
	}
		else if(action == "Resume")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						resume();
			}
		});	
	}
}
}
