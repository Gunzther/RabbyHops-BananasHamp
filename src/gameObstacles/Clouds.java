package gameObstacles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Create clouds which are background objects.
 * Update position of clouds and use iterator to loops background. 
 * 
 * @author Gunthee Tawewatmongkol
 */
public class Clouds {
	private List<CloudPosition> listCloud;
	private BufferedImage cloud;
	private Rabby rabby;
	
	/** Create 5 positions of clouds.*/
	public Clouds(int width, Rabby rabby) {
		this.rabby = rabby;
		cloud = new Resource("cloud.png").getResourceImage();
		listCloud = new ArrayList<CloudPosition>();
		CloudPosition imageCloud = new CloudPosition(0, 20);
		listCloud.add(imageCloud);
		imageCloud = new CloudPosition(150, 30);
		listCloud.add(imageCloud);
		imageCloud = new CloudPosition(250, 50);
		listCloud.add(imageCloud);
		imageCloud = new CloudPosition(450, 25);
		listCloud.add(imageCloud);
		imageCloud = new CloudPosition(550, 40);
		listCloud.add(imageCloud);
	}
	
	/** Make clouds move to the left slowly.
	 * Remove the cloud that move out of screen window
	 * and create new one in the same y position at the
	 * right side of the screen window.
	 */
	public void update(){
		Iterator<CloudPosition> it = listCloud.iterator();
		CloudPosition firstCloud = it.next();
		firstCloud.posX -= rabby.getSpeedX()/8;
		while(it.hasNext()) {
			CloudPosition cloud = it.next();
			cloud.posX -= rabby.getSpeedX()/8;
		}
		if(firstCloud.posX < -cloud.getWidth()) {
			listCloud.remove(firstCloud);
			firstCloud.posX = 600;
			listCloud.add(firstCloud);
		}
	}
	/** Create ui of clouds. */
	public void draw(Graphics g) {
		for(CloudPosition position : listCloud) {
			g.drawImage(cloud, (int) position.posX, position.posY, null);
		}
	}
	
	/** Object of x position and y position of clouds */
	private class CloudPosition {
		private float posX;
		private int posY;
		public CloudPosition(float posX, int posY) {
			this.posX = posX;
			this.posY = posY;
		}
	}
}
