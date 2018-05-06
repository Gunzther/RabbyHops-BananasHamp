package gameObstacles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameScreen;

/**
 * Read image from file(by using bufferedImage) and return an image object.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class Resource {
	
	public static BufferedImage getResourceImage(String filename) {
		String path = String.format("src/objects/(%s)%s", GameScreen.theme, filename);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
