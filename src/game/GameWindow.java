package game;

import javax.swing.JFrame;

import application.ThemeController;
import serverAndClient.Client;

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
		else if(application.ThemeController.mode.equalsIgnoreCase("multi")) {
			gameScreen = new GameScreen(setLastRank());
			addKeyListener(gameScreen);
			add(gameScreen);
		}
		else {
			gameScreen = new GameScreen("replay.png");
			addKeyListener(gameScreen);
			add(gameScreen);
		}
		setVisible(true);
	}
	
	public String setLastRank() {
		if (Client.rankPb == 2) return "second.png";
		else if (Client.rankPb == 3) return "third.png";
		else if (Client.rankPb == 4) return "fourth.png";
		return "first.png";
	}
	
	public void startGame() {
		if(ThemeController.mode.equalsIgnoreCase("team")) {
			gameScreenTeam.startGame();
		}
		if(ThemeController.mode.equals("multi") || ThemeController.mode.equals("single")) {
			setVisible(true);
			gameScreen.startGame();
		}
	}
	
	public boolean isEnd() {
		return gameScreen.getEndGame();
	}
	
	public void reset() {
		gameScreen.resetGame();
	}
}
