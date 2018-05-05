package gameObstacles;

import java.awt.image.BufferedImage;

public class BananaDash extends Enemy{
	public static final int Y_LAND = 90;
	
	public BananaDash(Rabby rabby, int posX, BufferedImage image) {
		super(rabby, posX, image);
		super.setY_LAND(Y_LAND);
	}
}
