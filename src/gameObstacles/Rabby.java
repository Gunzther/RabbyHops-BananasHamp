package gameObstacles;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class Rabby {
	public static final int LAND_POSY = 77;
	public static final float GRAVITY = 0.6f;
	//rabbit's state
	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DASHING = 2;
	private static final int DEATH = 3;
	private int state = NORMAL_RUN;
	//rabbit's position
	private float posY;
	private float posX;
	private float speedX;
	private float speedY;
	//player's score
	public int score = 0;
	//rabbit's bound images and animation
	private Rectangle rabbyBound;
	private Animation runAnimation;
	private BufferedImage jumping;
	private BufferedImage dashing;
	private BufferedImage death;
	//sound
	private AudioClip jumpSound;
	private AudioClip deadSound;
	
	public Rabby(int xPos) {
		posX = xPos;
		posY = LAND_POSY;
		rabbyBound = new Rectangle();
		//downloading rabbit images
		runAnimation = new Animation(90);
		runAnimation.addFrame(Resource.getResourceImage("run1.png"));
		runAnimation.addFrame(Resource.getResourceImage("run2.png"));
		jumping = Resource.getResourceImage("jump.png");
		dashing = Resource.getResourceImage("dash.png");
		death = Resource.getResourceImage("stand.png");
		//downloading sound file
		try {
			jumpSound =  Applet.newAudioClip(new URL("file","","src/sound/jump.wav"));
			deadSound =  Applet.newAudioClip(new URL("file","","src/sound/dead.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	/** Create ui of rabbit. */
	public void draw(Graphics g) {
		switch(state) {
			case NORMAL_RUN:
				g.drawImage(runAnimation.getFrame(), (int) posX, (int) posY, null);
				break;
			case JUMPING:
				g.drawImage(jumping, (int) posX, (int) posY, null);
				break;
			case DASHING:
				g.drawImage(dashing, (int) posX, (int) (posY + 18), null);
				break;
			case DEATH:
				g.drawImage(death, (int) posX, (int) posY, null);
				break;
		}
	}
	
	/** Update animation and position of rabbit.*/
	public void update() {
		runAnimation.updateFrame();
		if(posY >= LAND_POSY) {
			posY = LAND_POSY;
			if(state != DASHING) {
				state = NORMAL_RUN;
			}
		} else {
			speedY += GRAVITY;
			posY += speedY;
		}
	}
	
	public void jump() {
		if(posY >= LAND_POSY) {
			if(jumpSound != null) {
				jumpSound.play();
			}
			speedY = -7.5f;
			posY += speedY;
			state = JUMPING;
		}
	}
	
	public void dash(boolean isDown) {
		if(state == JUMPING) {
			return;
		}
		if(isDown) {
			state = DASHING;
		} else {
			state = NORMAL_RUN;
		}
	}
	
	public void dead(boolean isDeath) {
		if(isDeath) {
			state = DEATH;
			deadSound.play();
		} else {
			state = NORMAL_RUN;
		}
	}
	
	/** Set x, y position(computing ui size) and bounds of rabbit.
	 * @return rabbit as an object that has rectagle shape.
	 */
	public Rectangle getBound() {
		rabbyBound = new Rectangle();
		if(state == DASHING) {
			rabbyBound.x = (int) posX + 5;
			rabbyBound.y = (int) posY + 18;
			rabbyBound.width = dashing.getWidth() - 10;
			rabbyBound.height = dashing.getHeight();
		} else {
			rabbyBound.x = (int) posX + 5;
			rabbyBound.y = (int) posY;
			rabbyBound.width = runAnimation.getFrame().getWidth() - 10;
			rabbyBound.height = runAnimation.getFrame().getHeight();
		}
		return rabbyBound;
	}
	
	public void reset() {
		posY = LAND_POSY;
		this.score = 0;
	}
	
	public void upScore() {
		score += 1;
	}
	
	public int getScore() {
		return this.score;
	}
	
}
