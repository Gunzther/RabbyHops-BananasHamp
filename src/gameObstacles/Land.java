package gameObstacles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Create path which are background objects.
 * Update position of path and use iterator to loop and random background. 
 * 
 * @author Gunthee Tawewatmongkol
 */
public class Land {
	public static final int LAND_POS = 103;
	
	private List<ImageLand> listLand;
	private BufferedImage land1;
	private BufferedImage land2;
	private BufferedImage land3;
	private Rabby rabby;
	
	/** Create positions of paths.
	 * Number of paths depend on width of screen window.
	 */
	public Land(int width, Rabby mainCharacter) {
		this.rabby = mainCharacter;
		land1 = Resource.getResourceImage("land1.png");
		land2 = Resource.getResourceImage("land2.png");
		land3 = Resource.getResourceImage("land3.png");
		int numberOfImageLand = width / land1.getWidth() + 1;
		listLand = new ArrayList<ImageLand>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = i * land1.getWidth();
			setImageLand(imageLand);
			listLand.add(imageLand);
		}
	}
	
	/** Make paths move to the left(speed equals rabbit's speed).
	 * Remove the path that move out of screen window
	 * and create a random new one in the same y position at the
	 * right side of the screen window.
	 */
	public void update(){
		Iterator<ImageLand> itr = listLand.iterator();
		ImageLand firstLand = itr.next();
		firstLand.posX -= rabby.getSpeedX();
		float previousPosX = firstLand.posX;
		while(itr.hasNext()) {
			ImageLand nextLand = itr.next();
			nextLand.posX = previousPosX + land1.getWidth();
			previousPosX = nextLand.posX;
		}
		if(firstLand.posX < -land1.getWidth()) {
			listLand.remove(firstLand);
			firstLand.posX = previousPosX + land1.getWidth();
			setImageLand(firstLand);
			listLand.add(firstLand);
		}
	}
	
	/** Random and set path image(land2 is main path). */
	private void setImageLand(ImageLand imgLand) {
		Random rand = new Random();
		int typeLand = rand.nextInt(10);
		if(typeLand == 1) {
			imgLand.image = land1;
		} else if(typeLand == 9) {
			imgLand.image = land3;
		} else {
			imgLand.image = land2;
		}
	}
	
	/** Create ui of path. */
	public void draw(Graphics g) {
		for(ImageLand imgLand : listLand) {
			g.drawImage(imgLand.image, (int) imgLand.posX, LAND_POS, null);
		}
	}
	
	/** Object of x position and image of paths */
	private class ImageLand {
		float posX;
		BufferedImage image;
	}
	
}
