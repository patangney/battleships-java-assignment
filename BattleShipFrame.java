
/**
 * 
 */

/**
 * @author ptang
 *
 */

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BattleShipFrame extends JFrame {
	private JButton[] buttonArray;
	private JLabel countLives;
	private JLabel shipsLeft;
	private ActionListener listener;
	private BattleShipsGame game;
	private ImageIcon sunkShip;
	private ImageIcon ship;
	private ImageIcon sea;
	private Color changeColor;
	private JButton reset;
	private JButton cancel;
	private JButton exit;
	private ActionListener listenerR;
	private ActionListener listenerM;

	public BattleShipFrame() {
		game = new BattleShipsGame();
		createMainPanel();
		sunkShip = new ImageIcon("sunkShip.png");
		ship = new ImageIcon("ship.png");
		sea = new ImageIcon("sea.png");

		JMenuBar menuBar = new JMenuBar();
		// sets the menu bar for this frame
		setJMenuBar(menuBar);

		menuBar.add(createLevel());
		menuBar.add(createOptions());

	}

	// creates the main panel for the frame
	public void createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(createBoxPanel(), BorderLayout.CENTER);
		mainPanel.add(createLabelPanel(), BorderLayout.WEST);
		mainPanel.add(createActionPanel(), BorderLayout.SOUTH);
		add(mainPanel);
		mainPanel.setBackground(Color.BLUE);
	}

	// creates the menu names
	public JMenu createLevel() {
		JMenu gameLevel = new JMenu("Game Level");
		gameLevel.add(createEasyItem());
		gameLevel.add(createMedItem());
		gameLevel.add(createHardItem());
		return gameLevel;

	}

	// creates the menu dropdown item easy for the game
	public JMenuItem createEasyItem() {
		JMenuItem easy = new JMenuItem("Easy");
		listenerM = new MenuListener();
		easy.addActionListener(listenerM);
		return easy;
	}

	// creates the menu dropdown item medium for the game
	public JMenuItem createMedItem() {
		JMenuItem med = new JMenuItem("Medium");
		listenerM = new MenuListener();
		med.addActionListener(listenerM);
		return med;
	}

	// creates the menu dropdown item hard for the game
	public JMenuItem createHardItem() {
		JMenuItem hard = new JMenuItem("Hard");
		listenerM = new MenuListener();
		hard.addActionListener(listenerM);
		return hard;
	}

	// action listener for the menu
	public class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) { // code for the easy level
														// game
			if (e.getActionCommand().equalsIgnoreCase("easy")) {
				game = new BattleShipsGame(7, 6);
				for (int i = 0; i < buttonArray.length; i++) {
					buttonArray[i].setEnabled(true);
					buttonArray[i].setIcon(null);
					buttonArray[i].setText(null);
				}
				countLives.setText("Lives To Live " + game.getLives());
				// updating the number of ships left
				shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");
			}
			// code for the medium level game
			else if (e.getActionCommand().equalsIgnoreCase("medium")) {
				game = new BattleShipsGame(6, 5);
				for (int i = 0; i < buttonArray.length; i++) {
					buttonArray[i].setEnabled(true);
					buttonArray[i].setIcon(null);
					buttonArray[i].setText(null);
				}
				countLives.setText("Lives To Live " + game.getLives());
				// updating the number of ships left
				shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");
			}
			// code for the hard level game
			else if (e.getActionCommand().equalsIgnoreCase("hard")) {
				game = new BattleShipsGame(5, 4);

				for (int i = 0; i < buttonArray.length; i++) {
					buttonArray[i].setEnabled(true);
					buttonArray[i].setIcon(null);
					buttonArray[i].setText(null);
				}
				countLives.setText("Lives To Live " + game.getLives());
				// updating the number of ships left
				shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");
			}
		}
	}// close menu listener
		// create menu for options

	public JMenu createOptions() {
		JMenu gameOptions = new JMenu("Game Options");
		gameOptions.add(createExit());
		gameOptions.add(createCancel());

		return gameOptions;

	}

	public JMenuItem createExit() {
		JMenuItem exit = new JMenuItem("Exit");
		listenerR = new ResetListener();

		exit.addActionListener(listenerR);

		return exit;

	}

	public JMenuItem createCancel() {
		JMenuItem cancel = new JMenuItem("Cancel");
		listenerR = new ResetListener();

		cancel.addActionListener(listenerR);

		return cancel;

	}

	// creates the panel which will hold the grid of buttons
	public JPanel createBoxPanel() {
		buttonArray = new JButton[16];
		listener = new ButtonListener();

		// creates 16 buttons for the 4x4 grid
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton();
			buttonArray[i].addActionListener(listener);
			// buttonArray[i].setIcon(ship);
		}

		JPanel boxPanel = new JPanel(new GridLayout(4, 4));

		// adds the buttons to the panel
		for (int i = 0; i < buttonArray.length; i++) {
			boxPanel.add(buttonArray[i]);

		}

		boxPanel.setBorder(
				new TitledBorder(new EtchedBorder(), "Find the Ships", TitledBorder.CENTER, TitledBorder.TOP));
		boxPanel.setBackground(Color.BLUE);
		// boxPanel.setImageIcon(ship);

		return boxPanel;
	}

	// creates a panel for the labels
	public JPanel createLabelPanel() {

		JPanel labelPanel = new JPanel(new GridLayout(2, 1));
		countLives = new JLabel("Lives To Live " + game.getLives());
		labelPanel.add(countLives);

		shipsLeft = new JLabel("Ships left unsunk " + game.getShipsLeft() + " ");
		labelPanel.add(shipsLeft);

		labelPanel.setBackground(Color.LIGHT_GRAY);

		return labelPanel;
	}

	public JPanel createActionPanel() {
		JPanel actionPanel = new JPanel();

		listenerR = new ResetListener();
		reset = new JButton("Reset");
		reset.addActionListener(listenerR);
		actionPanel.add(reset);

		cancel = new JButton("Cancel");
		cancel.addActionListener(listenerR);
		actionPanel.add(cancel);
		actionPanel.setBackground(Color.LIGHT_GRAY);

		exit = new JButton("Exit");
		exit.addActionListener(listenerR);
		actionPanel.add(exit);

		return actionPanel;
	}

	public class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("reset")) {

				game = new BattleShipsGame(7, 6);
				for (int i = 0; i < buttonArray.length; i++) {
					buttonArray[i].setEnabled(true);
					buttonArray[i].setIcon(null);
					buttonArray[i].setText(null);
				}

				// updating the lives left
				countLives.setText("Lives To Live " + game.getLives());
				// updating the number of ships left
				shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");

			}
			if (e.getActionCommand().equalsIgnoreCase("Cancel")) {
				game = new BattleShipsGame(0, 0);
				for (int i = 0; i < buttonArray.length; i++) {
					buttonArray[i].setEnabled(false);
					buttonArray[i].setIcon(null);
					buttonArray[i].setText(null);
				}
				countLives.setText("Lives To Live " + game.getLives());
				// updating the number of ships left
				shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");
			}
			if (e.getActionCommand().equalsIgnoreCase("Exit")) {
				System.exit(0);
			}
		}// close action performed
	}// close reset listener

	public void showShip() {

		game.showGrid();

		for (int i = 0; i < buttonArray.length; i++) {
			int r = i / 4;
			int c = i % 4;

			if (game.checkIfShip(r, c) == true)

				buttonArray[i].setIcon(ship);

		}
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// declare the row and column values
			int r = 0;
			int c = 0;

			// Find which button was pressed
			JButton btn = (JButton) e.getSource();

			// search through the array
			for (int i = 0; i < buttonArray.length; i++) {
				// find what element of the array the button is
				if (btn.equals(buttonArray[i])) {
					r = i / 4;
					c = i % 4;

				}
			}

			// cancelling a button after it is pressed
			btn.setEnabled(false);
			// set the text from the cell to a string
			String s = game.shoot(r, c);
			// set the string on the button
			btn.setText(s);

			if (btn.getText().equalsIgnoreCase("HIT-ship sunk!")) {
				btn.setIcon(sunkShip);
				btn.setDisabledIcon(sunkShip);
			} else if (btn.getText().equalsIgnoreCase("Miss!")) {

				btn.setIcon(sea);
				btn.setDisabledIcon(sea);

			}

			// updating the lives left
			countLives.setText("Lives To Live " + game.getLives());
			// updating the number of ships left
			shipsLeft.setText("Ships left unsunk " + game.getShipsLeft() + " ");

			if (game.getLives() == 0) {
				// game.showShip();
				// btn.setIcon(sea);

				JOptionPane.showMessageDialog(null, "Sorry!!, You loose ", "GAME OVER", JOptionPane.WARNING_MESSAGE);

				int returnedValue = JOptionPane.showConfirmDialog(null, "Would you like another game ?", "ANOTHER GAME",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				String message;
				if (returnedValue == JOptionPane.YES_OPTION)
					// display result in Joption pane
					message = "Please hit the Reset button";
				else if (returnedValue == JOptionPane.NO_OPTION)
					message = "Hope you have enjoyed the game";
				else
					message = "Goodbye";

				JOptionPane.showMessageDialog(null, message);

			} // close lives left dialog box

			if (game.getShipsLeft() == 0) {
				JOptionPane.showMessageDialog(null, "Congratulations  YOU WIN !!", "GAME OVER",
						JOptionPane.PLAIN_MESSAGE);

				int returnedValue = JOptionPane.showConfirmDialog(null, "Would you like another game ?", "ANOTHER GAME",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				String message;
				if (returnedValue == JOptionPane.YES_OPTION)
					// display result in Joption pane
					message = "Please hit the Reset button";
				else if (returnedValue == JOptionPane.NO_OPTION)
					message = "Hope you have enjoyed the game";
				else
					message = "Goodbye";

				JOptionPane.showMessageDialog(null, message);

			} // close ships left dialog box

			// to show where the ships are at the end of the game
			// if(game.getLives()== 0)
			// game.showGrid();

		}// close action performed
	}// close action listener class

}
