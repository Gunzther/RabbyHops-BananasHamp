package gameObstacles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy {
	private int Y_LAND = 120; //equals land's bound
	private int posX;
	private int width;
	private int height;
	
	private BufferedImage image;
	private Rabby rabby;
	private Rectangle enemy;
	
	public Enemy(Rabby rabby, int posX, BufferedImage image) {
		this.rabby = rabby;
		this.posX = posX;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
		enemy = new Rectangle();
	}

	public void update() {
		posX -= rabby.getSpeedX();
	}

	public void draw(Graphics g) {
		g.drawImage(image, posX, Y_LAND - height, null);
	}

	public Rectangle getBound() {
		enemy = new Rectangle();
		enemy.x = posX;
		enemy.y = Y_LAND - height;
		enemy.width = width;
		enemy.height = height;
		return enemy;
	}

	public boolean isOutOfScreen() {
		if(posX < -width) {
			return true;
		}
		return false;
	}

	public void setY_LAND(int y_LAND) {
		Y_LAND = y_LAND;
	}
}
