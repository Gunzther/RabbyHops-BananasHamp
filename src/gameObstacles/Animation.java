package gameObstacles;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Create animation frame by frame from images in the list.
 * 
 * @author Gunthee Tawewatmongkol
 */
public class Animation {
	private List<BufferedImage> list;
	private long targetTime;
	private int frame = 0;
	private long previousTime;

	public Animation(int targetTime) {
		this.targetTime = targetTime;
		list = new ArrayList<BufferedImage>();
		previousTime = 0;
	}

	/** Change frame when the time is the target time */
	public void updateFrame() {
		if (System.currentTimeMillis() - previousTime >= targetTime) {
			frame++;
			if (frame >= list.size()) {
				frame = 0;
			}
			previousTime = System.currentTimeMillis();
		}
	}

	/** Add image that want to create an animation to the list */
	public void addFrame(BufferedImage image) {
		list.add(image);
	}
	
	/** Return frame at that time. */
	public BufferedImage getFrame() {
		return list.get(frame);
	}

}
