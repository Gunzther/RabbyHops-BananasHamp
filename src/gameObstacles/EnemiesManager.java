package gameObstacles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create enemies and remove them when they are out of the screen window. 
 * 
 * @author Gunthee Tawewatmongkol
 */
public class EnemiesManager {
	private BufferedImage bananaJump;
	private BufferedImage bananaDash;
	private Random rand;
	private List<Enemy> enemies;
	private Rabby rabby;
	private boolean half = false;
	
	public EnemiesManager(Rabby mainCharacter) {
		Enemy.targetPos = 320;
		rand = new Random();
		bananaJump = Resource.getResourceImage("banana1.png");
		bananaDash = Resource.getResourceImage("banana2.png");
		enemies = new ArrayList<Enemy>();
		this.rabby = mainCharacter;
		enemies.add(createEnemy());
	}
	
	/** Make enemies move to the rabbit.
	 * Remove the enemy that move out of screen window.
	 * Random number to create new one in the same y position 
	 * when the previous enemy is at half of the window.
	 * If enemy is not created when the previous enemy is at the half of window,
	 * it will be created when the previous enemy is out of the window.
	 */
	public void update() {
		rabby.upScore();
		for(Enemy e1 : enemies) {
			e1.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isHalfOfScreen()) {
			Random rand2 = new Random();
			if(rand2.nextInt(2) == 0) {
				enemies.add(createEnemy());
				half = true;
			}
			else {
				half = false;
			}
		}
		if(enemy.isOutOfScreen()) {
			if(!half) enemies.add(createEnemy());
			enemies.remove(0);
			half = false;
		}
	}
	
	/** Create ui of all enemies. */
	public void draw(Graphics g) {
		for(Enemy e1 : enemies) {
			e1.draw(g);
		}
	}
	
	/** Random type of enemy and create. */
	private Enemy createEnemy() {
		int type = rand.nextInt(3);
		if(type == 0) {
			return new BananaDash(rabby, 800, bananaDash);
		} else {
			return new BananaJump(rabby, 800, bananaJump);
		}
	}
	
	/**
	 * Check that rabbit hits banana or not.
	 * @return true if rabbit hits banana.
	 *		   false if not.
	 */
	public boolean isCollision() {
		for(Enemy e1 : enemies) {
			if (rabby.getBound().intersects(e1.getBound())) return true;
		}
		return false;
	}
	
	public void reset() {
		Enemy first = createEnemy();
		enemies.clear();
		enemies.add(first);
	}
	
}
