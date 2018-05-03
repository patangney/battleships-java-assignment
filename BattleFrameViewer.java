import javax.swing.JFrame;

public class BattleFrameViewer {

	public static JFrame myFrame = new BattleGUI();
	public static void main(String[] args) {
		startBattleShipGUI();
	}

	public static void startBattleShipGUI() {
		myFrame = new BattleGUI();
		myFrame.setLocation(600, 100);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("BattleShip");
		myFrame.setVisible(true);
		myFrame.setSize(700, 700); 
	}

	public static void closeBattleshipGUI(){
		myFrame.dispose();
	}


}// close main class