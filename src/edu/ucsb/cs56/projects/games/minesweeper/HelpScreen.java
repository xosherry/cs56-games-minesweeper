package edu.ucsb.cs56.projects.games.minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** HelpScreen.java is a Panel that displays help messages and a button to return to the StartMenu

@author Julian Gee
@version CS56, Winter 2014, UCSB
*/

public class HelpScreen {
    JPanel helpScreen;
    JButton backButton;
    JTextArea helpText;
    
    public HelpScreen(JFrame frame, JPanel panel) {

	helpScreen = new JPanel(new GridLayout(2,0));
	helpText = new JTextArea("stub");
	backButton = new JButton("Back");
	helpScreen.add(helpText);
	helpScreen.add(backButton);
	addActionListener(backButton, frame, panel);
	helpScreen.setPreferredSize(new Dimension(650, 600));
	frame.getContentPane().add(helpScreen);
	helpScreen.setVisible(true);
	panel.setVisible(false);
   }

    public void addActionListener(JButton button, JFrame frame, JPanel panel){
	frame.getContentPane().add(panel);
	helpScreen.setVisible(false);
	panel.setVisible(true);
    }






}
