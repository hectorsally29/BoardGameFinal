import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
public class Board implements ActionListener {

	Random random = new Random();
	boolean playerTurn;
	JFrame frame = new JFrame();
	JPanel buttonPanel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];

	Object[] options = {"Play Again" , "Close Game"};
	
	Board() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		// size of the tic tac toe game board
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.getContentPane().setBackground(Color.white);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.add(buttonPanel);

		//buttons for the game
		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setBackground(Color.black);
		

		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonPanel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		addMusic();
		playerTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	// when the button is clicked the action will make the X and O appear
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i]) {
				if (playerTurn) {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(Color.RED);
						buttons[i].setText("X");
						playerTurn = false;
						checkCols();
						checkDiagonal();
						checkRow();
						
						
					}
				} else {
					if (buttons[i].getText() == "") {
						buttons[i].setForeground(Color.BLACK);
						buttons[i].setText("O");
						playerTurn = true;
						checkCols();
						checkDiagonal();
						checkRow();
					}
				}
			}
		}
	}

	// change players turn each time
	public void playerTurn() {

		if (random.nextInt(2) == 0) {
			playerTurn = true;
		} else {
			playerTurn = false;
		}
	}

	// these are to check the wins
	public void checkDiagonal() {

		if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") {
			X();
		} 
		if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X") {
			X();
		} 

		//O wins
		if (buttons[0].getText() == "O" && buttons[4].getText() == "O" && buttons[8].getText() == "O") {
			O();
		}
		if (buttons[2].getText() == "O" && buttons[4].getText() == "O" && buttons[6].getText() == "O") {
			O();
		}
	}

	public void checkRow() {

		if (buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X") {
			X();
		} 
		if (buttons[3].getText() == "X" && buttons[4].getText() == "X" && buttons[5].getText() == "X") {
			X();
		} 

		//O wins
		if (buttons[0].getText() == "O" && buttons[1].getText() == "O" && buttons[2].getText() == "O") {
			O();
		}
		if (buttons[3].getText() == "O" && buttons[4].getText() == "O" && buttons[5].getText() == "O") {
			O();
		}
	
	}

	public void checkCols() {

		if ((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
			X();
		} 
		if ((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
			X();
		} 
		if ((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
			X();
		}

		//O wins
		if ((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
			O();
		}
		if ((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
			O();
		}
		if ((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
			O();
		}
	}

	// if X or O wins, an option will pop up to see if they wanna play again
	public void O() {
		int n = JOptionPane.showOptionDialog(null, "Player O has won the game!",null, JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[1]);
		
		for(int i = 0; i<9; i++) {
			if(n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			if(n == JOptionPane.YES_OPTION) {
				buttons[i].setText("");
			}
		}
	}
	
	public void X() {
		int n = JOptionPane.showOptionDialog(null, "Player X has won the game!",null, JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[1]);
		
		for(int i = 0; i<9; i++) {
			if(n == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			if(n == JOptionPane.YES_OPTION) {
				buttons[i].setText("");
			}
		}
	}
	
	 public static void addMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		   File file = new File("chill.wav");
		   AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		   Clip clip = AudioSystem.getClip();
		   clip.open(audioStream);
		   clip.start();
	   }
}
