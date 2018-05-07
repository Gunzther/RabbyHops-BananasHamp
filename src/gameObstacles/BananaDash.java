package gameObstacles;

import java.awt.image.BufferedImage;

/**
 * Flying bananas,
 * Obstacles that fly above the rabbit(main charactor that player control).
 * The player must press down(dash) to pass these obstacles.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class BananaDash extends Enemy{
	public static final int Y_LAND = 85;
	
	public BananaDash(Rabby rabby, int posX, BufferedImage image) {
		super(rabby, posX, image);
		super.setY_LAND(Y_LAND);
	}
}
