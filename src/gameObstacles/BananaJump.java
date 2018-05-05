package gameObstacles;

import java.awt.image.BufferedImage;

/**
 * Banana peels,
 * Obstacles that obstruct the rabbit(main charactor that player control).
 * The player must press up(jump) to pass these obstacles.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class BananaJump extends Enemy{
	public static final int Y_LAND = 120;
	
	public BananaJump(Rabby rabby, int posX, BufferedImage image) {
		super(rabby, posX, image);
		super.setY_LAND(Y_LAND);
	}
}
