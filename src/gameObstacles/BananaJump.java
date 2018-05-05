package gameObstacles;

import java.awt.image.BufferedImage;

public class BananaJump extends Enemy{
	public static final int Y_LAND = 120;
	
	public BananaJump(Rabby rabby, int posX, BufferedImage image) {
		super(rabby, posX, image);
		super.setY_LAND(Y_LAND);
	}
}
