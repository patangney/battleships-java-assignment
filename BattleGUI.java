import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class BattleGUI extends JFrame {

	private BattleShipsGame myGame;
	// grid buttons
	private JButton[] gridArray;

	// labels
	private JLabel livesLeft;
	private JLabel remainderOfShips;
	// Action Listeners
	private ActionListener listener;
	// images
	private ImageIcon submarine, waterbackground2;

	// menu bar
	private JMenuBar bar;
	// menu
	private JMenu fileMenu, optionsMenu;
	private JMenuItem quit, about, easyMode, hardMode, normalMode, subMenu;
	// file - about container
	private Container aboutC;



	/**
	 * Create the panel.
	 */
	public BattleGUI() {
		myGame = new BattleShipsGame();
		createMainPanel();
		// ********************//
		/* Start of Menu Bar */
		// item handler for menu bar
		ItemHandler itemHandler = new ItemHandler();
		// ********************//
		setJMenuBar(bar = new JMenuBar());
		// ********************//// FILE MENU //// ********************//
		(fileMenu = new JMenu("File")).setMnemonic('F');
		fileMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		// ********************//
		fileMenu.addSeparator();
		// ********************//
		about = new JMenuItem("About", 'A');
		about.setFont(new Font("Tahoma", Font.BOLD, 12));
		about.setIcon(new ImageIcon("info.png"));
		about.addActionListener(itemHandler);
		fileMenu.add(about);
		// ********************//
		quit = new JMenuItem("Exit", 'x');
		quit.setFont(new Font("Tahoma", Font.BOLD, 12));
		quit.setIcon(new ImageIcon("exit.png"));
		quit.addActionListener(itemHandler);
		fileMenu.add(quit);
		// ********************//// OPTIONS MENU //// ********************//
		(optionsMenu = new JMenu("Options")).setMnemonic('O');
		optionsMenu.setFont(new Font("Tahoma", Font.BOLD, 12));

		// ********************//Sub Menu // ********************//			
		subMenu = new JMenu("Game Difficulty");
		// *************// Easy // *************//
		easyMode = new JMenuItem("Easy");
		easyMode.addActionListener(itemHandler);	   
		subMenu.add(easyMode);
		// *************// Normal // *************//
		normalMode = new JMenuItem("Medium");
		normalMode.addActionListener(itemHandler);	   
		subMenu.add(normalMode);
		// *************//Hard // *************//
		hardMode = new JMenuItem("Hard");
		hardMode.addActionListener(itemHandler);	   
		subMenu.add(hardMode);

		optionsMenu.add(subMenu);



		// ********************// Add to Menu Bar // ********************//

		bar.add(fileMenu);
		bar.add(optionsMenu);
		/* end Menu Bar */
		// ********************//********************// ********************//

		

		// ********************//// Game Icons//// ********************//
		submarine = new ImageIcon("submarine.png");
		waterbackground2 = new ImageIcon("waterbackground2.png");
		
		

		// ********************//********************// ********************//

	}

	public void createMainPanel() {

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		mainPanel.add(createGamePanel(), BorderLayout.CENTER);
		mainPanel.add(createGameInfo(), BorderLayout.WEST);
		
		mainPanel.setBackground(new Color(169, 169, 169));
		getContentPane().add(mainPanel);
	}

	public JPanel createGamePanel() {
		gridArray = new JButton[16];
		listener = new ButtonListener();

		// creates 16 buttons for the 4x4 grid
		for (int i = 0; i < gridArray.length; i++) {
			gridArray[i] = new JButton();
			gridArray[i].setIcon(new ImageIcon("ocean.png"));
			gridArray[i].addActionListener(listener);

		}

		JPanel gamePanel = new JPanel(new GridLayout(4, 4));
		gamePanel.setToolTipText("Lets Play!");
		gamePanel.setForeground(Color.WHITE);

		// adds the buttons to the panel
		for (int i = 0; i < gridArray.length; i++) {
			gamePanel.add(gridArray[i]);

		}

		gamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Locate the Ships!", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 248, 255)));
		gamePanel.setBackground(new Color(0, 191, 255));

		return gamePanel;
	}

	// creates a panel for the labels
	public JPanel createGameInfo() {

		JPanel gameInfo = new JPanel();
		gameInfo.setToolTipText("Game Information");
		gameInfo.setForeground(Color.WHITE);
		gameInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Game Info", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		livesLeft = new JLabel("Lives Left");
		//set default text - Lives Remaining upon load
		livesLeft.setToolTipText("Lives Remaining");
		livesLeft.setIcon(new ImageIcon("livesLeft.PNG"));
		//set default text - Ships Left
		remainderOfShips = new JLabel("Ships Left");
		remainderOfShips.setIcon(new ImageIcon("shipsRemaining.PNG"));

		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new ImageIcon("alogo.PNG"));

		gameInfo.setBackground(new Color(93, 196, 229));
		gameInfo.setLayout(new BorderLayout(0, 0));
		gameInfo.add(livesLeft, BorderLayout.NORTH);
		gameInfo.add(remainderOfShips, BorderLayout.SOUTH);
		gameInfo.add(backgroundImage, BorderLayout.CENTER);

		return gameInfo;
	}
	


	// ********************// Action Listeners // ********************//

	private class ItemHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quit) {
				System.exit(0);
			}
			
			if (e.getSource() == about) {
				ImageIcon aboutIcon = new ImageIcon("aboutBattleship.png");
				JOptionPane.showMessageDialog(aboutC,
						"BattleShip Game\n\n" + "Designed By:        Patrick Tangney\n"
								+ "Game Logic:            Meave Carr\n\n" + "Created: Feb 08, 2017",
								"About BattleShip", JOptionPane.INFORMATION_MESSAGE, aboutIcon );
			}
					
			

			// ********************// Game Difficulty - EASY // ********************//

			if (e.getActionCommand().equalsIgnoreCase("easy")) {
				ImageIcon infoIcon = new ImageIcon("easy_icon.png");
				JOptionPane.showMessageDialog(null, "Difficulty: Easy\nLives: 10\nEnemy Ships: 6", "Switch Difficulty", JOptionPane.INFORMATION_MESSAGE, infoIcon);
				//10 Lives, 6 Ships
				myGame = new BattleShipsGame(10, 6);
				for (int i = 0; i < gridArray.length; i++) {
					gridArray[i].setEnabled(true);
					gridArray[i].setIcon(new ImageIcon("ocean.png"));
					gridArray[i].setText(null);
				}
				livesLeft.setText("Left: " + myGame.getLives());
				remainderOfShips.setText("Enemies: " + myGame.getShipsLeft() + " ");
			}

			// ********************// Game Difficulty - NORMAL // ********************//
			if (e.getActionCommand().equalsIgnoreCase("medium")) {
				ImageIcon infoIcon2 = new ImageIcon("medium_icon.png");
				JOptionPane.showMessageDialog(null, "Difficulty: Medium\nLives: 7\nEnemy Ships: 7", "Switch Difficulty", JOptionPane.INFORMATION_MESSAGE, infoIcon2);
				//7 Lives, 7 Ships
				myGame = new BattleShipsGame(7, 7);
				for (int i = 0; i < gridArray.length; i++) {
					gridArray[i].setEnabled(true);
					gridArray[i].setIcon(new ImageIcon("ocean.png"));
					gridArray[i].setText(null);
				}
				livesLeft.setText("Left: " + myGame.getLives());
				remainderOfShips.setText("Enemies: " + myGame.getShipsLeft() + " ");
			}

			// ********************// Game Difficulty - NORMAL // ********************//
			if (e.getActionCommand().equalsIgnoreCase("hard")) {
				ImageIcon infoIcon3 = new ImageIcon("hard_icon.png");
				JOptionPane.showMessageDialog(null,"Difficulty: Hard\nLives: 5\nEnemy Ships: 9", "Switch Difficulty", JOptionPane.INFORMATION_MESSAGE, infoIcon3);
				//5 Lives, 9 Ships
				myGame = new BattleShipsGame(5, 9);
				for (int i = 0; i < gridArray.length; i++) {
					gridArray[i].setEnabled(true);
					gridArray[i].setIcon(new ImageIcon("ocean.png"));
					gridArray[i].setText(null);
				}
				livesLeft.setText("Left: " + myGame.getLives());
				remainderOfShips.setText("Enemies: " + myGame.getShipsLeft() + " ");
			}

		}
	}//end ActionListener - ItemHandler

	// ********************// ******************** // ********************//


	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// declare the row and column values
			int r = 0;
			int c = 0;

			// Find which button was pressed
			JButton btn = (JButton) e.getSource();

			// search through the array
			for (int i = 0; i < gridArray.length; i++) {
				// find what element of the array the button is
				if (btn.equals(gridArray[i])) {
					r = i / 4;
					c = i % 4;

				}
			}

			btn.setEnabled(false);
			String s = myGame.shoot(r, c);
			btn.setText(s);

			// ********************//// Apply Icons to Text //// ********************//
			if (btn.getText().equalsIgnoreCase("HIT-ship sunk!")) {
				btn.setIcon(submarine);
				btn.setDisabledIcon(submarine);
				
			} else if (btn.getText().equalsIgnoreCase("Miss!")) {

				btn.setIcon(waterbackground2);
				btn.setDisabledIcon(waterbackground2);

			}
			
			livesLeft.setText("Left: " + myGame.getLives());
			remainderOfShips.setText("Enemies: " + myGame.getShipsLeft() + " ");
			

			// ********************//// Dialog - Run out of Lives //// ********************//
			if (myGame.getLives() == 0) {
				ImageIcon infoIcon4 = new ImageIcon("game_over.png");
				JOptionPane.showMessageDialog(null, "", "Game Over",
						JOptionPane.PLAIN_MESSAGE, infoIcon4);

				int returnedValue = JOptionPane.showConfirmDialog(null, "Try again ?", "Restart Game",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (returnedValue == JOptionPane.YES_OPTION) {
					BattleFrameViewer.closeBattleshipGUI();
					BattleFrameViewer.startBattleShipGUI();

				}

				else {
					BattleFrameViewer.closeBattleshipGUI();

				}

			}

			// ********************//// Game Won - Option Dialog //// ********************//
			
			if (myGame.getShipsLeft() == 0) {
				ImageIcon infoIcon5 = new ImageIcon("you_win.png");
				JOptionPane.showMessageDialog(null, "", "You Won!", JOptionPane.INFORMATION_MESSAGE, infoIcon5);

				int returnedValue = JOptionPane.showConfirmDialog(null, "Would you like to play another game ?", "Play Again",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (returnedValue == JOptionPane.YES_OPTION) {
					BattleFrameViewer.closeBattleshipGUI();
					BattleFrameViewer.startBattleShipGUI();

				}

				else {					
					BattleFrameViewer.closeBattleshipGUI();

				}
			}
		}
	}//end actionEvent - ButtonListener
	// ********************// ******************** // ********************//
}//end class
