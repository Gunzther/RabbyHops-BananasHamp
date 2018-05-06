package game;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int SCREEN_WIDTH = 600;
	public static boolean whiteTheme;
	private GameScreen gameScreen;
	
	public GameWindow() {
		super("Rabby hops - Bananas Hamp");
		setSize(SCREEN_WIDTH, 170);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		gameScreen = new GameScreen();
		addKeyListener(gameScreen);
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true);
		gameScreen.startGame();
	}
	
	public static void main(String args[]) {
		(new GameWindow()).startGame();
	}
}