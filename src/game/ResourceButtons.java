package game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameScreen;

/**
 * Read image from file(by using bufferedImage) and return an image object.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class ResourceButtons {
	
	private BufferedImage img;
	
	public ResourceButtons(String filename) {
		String path = String.format("/buttons/(%s)%s", GameScreen.theme, filename);
		try {
		    img = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {}
	}
	
	public BufferedImage getResourceImage() {
		return img;
	}
}
