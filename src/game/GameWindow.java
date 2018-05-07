package game;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	public static final int SCREEN_WIDTH = 600;
	private GameScreen gameScreen;
	
	public GameWindow() {
		super();
		setSize(SCREEN_WIDTH, 180);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(420,450);
		setResizable(false);
		
		gameScreen = new GameScreen();
		addKeyListener(gameScreen);
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true);
		gameScreen.startGame();
	}
	
//	public static void main(String[]args) {
//		(new GameWindow()).startGame();
//	}
}
