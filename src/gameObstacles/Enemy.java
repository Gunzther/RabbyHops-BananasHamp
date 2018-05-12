package gameObstacles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Obstacles that always try to disturb player.
 * The player must time movements correctly in order to pass 
 * these obstacles without dying. 
 * 
 * @author Gunthee Tawewatmongkol
 */
public class Enemy {
	private int Y_LAND = 120; //equals land's bound
	private int posX;
	private int width;
	private int height;
	
	private BufferedImage image;
	private Rabby rabby;
	private Rectangle enemy;
	
	public static int targetPos;
	
	public Enemy(Rabby rabby, int posX, BufferedImage image) {
		this.rabby = rabby;
		this.posX = posX;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		enemy = new Rectangle();
	}
	
	/** Update enemy's position. */
	public void update() {
		posX -= rabby.getSpeedX();
	}
	
	/** Create ui of enemy. */
	public void draw(Graphics g) {
		g.drawImage(image, posX, Y_LAND - height, null);
	}

	/** Set x, y position(computing ui size) and bounds of enemy.
	 * @return enemy as an object that has rectagle shape.
	 */
	public Rectangle getBound() {
		enemy = new Rectangle();
		enemy.x = posX;
		enemy.y = Y_LAND - height;
		enemy.width = width;
		enemy.height = height;
		return enemy;
	}

	/** Check that enemy is out of screen or not. */
	public boolean isOutOfScreen() {
		if(posX < -width) {
			return true;
		}
		return false;
	}
	
	/** Check that enemy is at half of screen or not. */
	public boolean isHalfOfScreen() {
		if(posX == targetPos) {
			return true;
		}
		return false;
	}

	/** Set y position of enemy without computing ui size. */
	public void setY_LAND(int y_LAND) {
		Y_LAND = y_LAND;
	}
}
