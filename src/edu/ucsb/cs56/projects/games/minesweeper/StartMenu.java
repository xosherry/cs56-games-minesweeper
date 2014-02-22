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
	JButton NewGame;
	JButton Help;
	JFrame frame;
	JPanel menu;
	JPanel game;

	/** no-arg constructor which creates the GUI of the Main Menu for Minesweeper
	 *  This menu includes a start and help button
     */
	public StartMenu() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu = new JPanel(new BorderLayout());
		NewGame = new JButton("New Game");
		Help = new JButton("Help");
		addActionListener(NewGame, "New Game");
		addActionListener(Help, "Help");

		menu.setPreferredSize(new Dimension(650, 600));
		menu.setBackground(Color.green);
		menu.add(NewGame, BorderLayout.NORTH);
		menu.add(Help, BorderLayout.SOUTH);
		frame.getContentPane().add(menu);
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

		Interface grid = new GUIGrid();
		Messager m = new SOMessager();

		MineComponent mc = new MineComponent(grid, m);
		game.add(mc);
		menu.setVisible(false);
		frame.getContentPane().add(game);
	}

	/**
	 *  Pauses the game and brings up the main menu once again
	 */
	public void pause() {
		menu.setVisible(true);
		game.setVisible(false);
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
						newGame();
			}
		});	
	}
		else if(action == "Resume")
			{
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Execute when button is pressed
						newGame();
			}
		});	
	}
}
}