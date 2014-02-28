package edu.ucsb.cs56.projects.games.minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** EndGameMessage.java is a panel that is shown only at games end. If you win/lose it will notify you and ask to play again.
@author Julian Gee
@version CS56, Winter 2014, UCSB
*/

public class EndGameMessage {
	JPanel endGame;
	JTextArea endGameText;
	JButton yes;
	JButton no;
	JFrame temp;
	JPanel game;
	JPanel startMenu;

	public EndGameMessage(JFrame frame, JPanel game, JPanel startMenu, int status){
		this.temp=frame;
		this.game=game;
		this.startMenu=startMenu;
		endGame = new JPanel(new GridLayout(3,0));
		yes = new JButton("Yes");
		no = new JButton("No");
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAgain();
			}
		});
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToStartMenu();
			}
		});
		if(status==1){
			endGameText = new JTextArea("You Win!\nPlay Again?");
			endGame.add(endGameText);
			endGame.add(yes);
			endGame.add(no);
			endGame.setPreferredSize(new Dimension(650,600));
			frame.getContentPane().add(endGame);
			endGame.setVisible(true);
			game.setVisible(false);
		}
		if(status==-1){
			endGameText = new JTextArea("You Lose...\nTry Again?");
			endGame.add(endGameText);
			endGame.add(yes);
			endGame.add(no);
			endGame.setPreferredSize(new Dimension(650,600));
			temp.getContentPane().add(endGame);
			endGame.setVisible(true);
			game.setVisible(false);
		}
	}
	
	public void playAgain(){
		temp.getContentPane().add(game);
		endGame.setVisible(false);
		game.setVisible(true);
	}
	
	public void returnToStartMenu(){
		temp.getContentPane().add(startMenu);
		endGame.setVisible(false);
		startMenu.setVisible(true);
	}
}
		
		