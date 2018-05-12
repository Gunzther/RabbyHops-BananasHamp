package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameObstacles.*;

public class GameScreen extends JPanel implements Runnable, KeyListener {
	public static String theme;
	public static int score = 0;
	//game's state
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	//objects
	private Land land;
	private Rabby rabby;
	private EnemiesManager enemiesManager;
	private Clouds clouds;
	private Thread thread;
	private int timeCheck;
	
	private boolean isKeyPressed;

	private int gameState = GAME_PLAYING_STATE;
	//button images
	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;
	//player buttons control
	public static int singleJump;
	public static int singleDash;

	public GameScreen() { 
		timeCheck = 0;
		rabby = new Rabby(50);
		land = new Land(GameWindow.SCREEN_WIDTH, rabby);
		rabby.setSpeedX(8);
		replayButtonImage = getResourceImage("replay.png");
		gameOverButtonImage = getResourceImage("gameover.png");
		clouds = new Clouds(GameWindow.SCREEN_WIDTH, rabby);
	}

	public void startGame() {
		thread = new Thread(this);
		thread.start();
	}

	public void gameUpdate() {
		timeCheck++;
		if (gameState == GAME_PLAYING_STATE) {
			clouds.update();
			land.update();
			rabby.update();
			if(timeCheck == 400) enemiesManager = new EnemiesManager(rabby);
			if(timeCheck >= 400) {
				enemiesManager.update();
				if (enemiesManager.isCollision()) {
					if(rabby.score > score) score = rabby.getScore();
					gameState = GAME_OVER_STATE;
					rabby.dead(true);
				}
			}
		}
	}

	public void paint(Graphics g) {
		if(theme.equalsIgnoreCase("b")) g.setColor(Color.decode("#535353"));
		else g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		switch (gameState) {
		case GAME_PLAYING_STATE:
		case GAME_OVER_STATE:
			clouds.draw(g);
			land.draw(g);
			if(timeCheck >= 400) enemiesManager.draw(g);
			rabby.draw(g);
			if(theme.equalsIgnoreCase("b")) g.setColor(Color.WHITE);
			else g.setColor(Color.BLACK);
			g.drawString("" + (rabby.score)/10, 530, 20);
			g.drawString("HI " + (score)/10, 450, 20);
			if (gameState == GAME_OVER_STATE) {
				g.drawImage(gameOverButtonImage, 200, 30, null);
				g.drawImage(replayButtonImage, 283, 60, null);
			}
			break;
		}
	}

	@Override
	public void run() {
		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		while (true) {
			gameUpdate();
			repaint();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed) {
			isKeyPressed = true;
			switch (gameState) {
			case GAME_PLAYING_STATE:
				if (e.getKeyCode() == singleJump) {
					rabby.jump();
				} else if (e.getKeyCode() == singleDash) {
					rabby.dash(true);
				}
				break;
			case GAME_OVER_STATE:
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameState = GAME_PLAYING_STATE;
					resetGame();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		if (gameState == GAME_PLAYING_STATE) {
			if (e.getKeyCode() == singleDash) {
				rabby.dash(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	private void resetGame() {
		enemiesManager.reset();
		rabby.dead(false);
		rabby.reset();
		timeCheck = 0;
	}
	
	public BufferedImage getResourceImage(String filename) {
		String path = String.format("src/buttons/(%s)%s", theme, filename);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
