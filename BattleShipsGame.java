import java.util.Random;

/**
 * 
 */

/**
 * @author ptang
 *
 */

public class BattleShipsGame {

	private BattleShipCell[][] grid;
	private int lives;
	private int hits;
	private int noOfShips;
	private int shipsLeft;

	public BattleShipsGame() {
		//create a grid of 4X4
		grid = new BattleShipCell[4][4];

		initGrid();
		 //** 100 lives for testing **/
	    //lives = 100;
		lives = 7;
		setNoOfShips(7);
		shipsLeft = 0;
	}

	public BattleShipsGame(int livesIn, int shipsIn) {
		grid = new BattleShipCell[4][4];

		initGrid();
		lives = livesIn;
		setNoOfShips(shipsIn);
	}

	public void initGrid() {
		for (int r = 0; r < 4; r++)
			for (int c = 0; c < 4; c++)
				grid[r][c] = new BattleShipCell();
	}

	
	public void showGrid() {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++)
				System.out.print(grid[r][c] + " ");
			System.out.println();
		}
	}

	// creates the random array of ships
	public void setNoOfShips(int noOfShipsIn) {
		Random noGen = new Random();
		noOfShips = noOfShipsIn;
		int count = 0;
		do {
			int r = noGen.nextInt(4);
			int c = noGen.nextInt(4);
			if (!checkIfShip(r, c)) {
				grid[r][c].setToShip();
				count++;
			}

		} while (count < noOfShips);
	}

	
	public boolean checkIfShip(int r, int c) {
		return grid[r][c].isShip();
	}

	
	public int getLives() {
		return lives;
	}

	
	public int getHits() {
		return hits;
	}

	
	public int getShipsLeft() {
		shipsLeft = noOfShips - hits;
		return shipsLeft;
	}

	public int getNoOfShips() {
		return noOfShips;
	}

	
	public String shoot(int r, int c) {
		String s;
		if (grid[r][c].isHit())
			s = "Already chosen";
		else {
			if (grid[r][c].isShip()) {
				s = "HIT-ship sunk!";
				hits++;
			} else {
				s = "Miss!";
				lives--;
			}

			grid[r][c].setToHit();
		}
		return s;

	}

}// end class
