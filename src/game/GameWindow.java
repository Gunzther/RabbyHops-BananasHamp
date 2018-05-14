package game;

import javax.swing.JFrame;

/**
 * Create game window of each mode
 * 
 * @author Gunthee Tawewatmongkol
 */
public class GameWindow extends JFrame {
	public static final int SCREEN_WIDTH = 600;
	private GameScreen gameScreen;
	private GameScreenTeam gameScreenTeam;
	
	public GameWindow() {
		super();
		setSize(SCREEN_WIDTH, 180);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(420,450);
		setResizable(false);
		if(application.ThemeController.mode.equalsIgnoreCase("team")) {
			gameScreenTeam = new GameScreenTeam();
			addKeyListener(gameScreenTeam);
			add(gameScreenTeam);
		}
		if(application.ThemeController.mode.equalsIgnoreCase("multi")) {
			gameScreen = new GameScreen("second.png");
			addKeyListener(gameScreen);
			add(gameScreen);
		}
		else {
			gameScreen = new GameScreen("replay.png");
			addKeyListener(gameScreen);
			add(gameScreen);
		}
		setVisible(true);
		System.out.println("set window");
	}
	
	public void setReplay(String replay) {
		gameScreen.setReplay(replay);
	}
	
	public boolean getEndGame() {
		return gameScreen.getEndGame();
	}
	
	public void startGame() {
		if(application.ThemeController.mode.equalsIgnoreCase("team")) {
			gameScreenTeam.startGame();
		}
		else {
			setVisible(true);
			gameScreen.startGame();
		}
	}
	
	public void resetGame() {
		gameScreen.resetGame();
	}
	
//	public static void main(String[]args) {
//		(new GameWindow()).startGame();
//	}
}
