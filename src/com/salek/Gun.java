package com.salek;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Gun {
	
	private Game game;
	private Shooter shooter;
	private BufferedImage gun_image;
	
	private double x;
	private double y;
	private int width, height;
	
	private long fireTime = System.currentTimeMillis();
	private int fireRateMili = 350;
	
	private final int number_of_bullets = 20;
	private Bullet[] bullet = new Bullet[number_of_bullets];
	private int next_bullet = 0;
	
	private boolean shotFired = false;
	private final int  gunJump = 4;
	private final int  gunJumpDelay = 5;
	private int gunJumpDelayCounter = 0;
	private boolean right_to_left;
	
	private final int bulletDeathArea = Game.deathArea;
	
	private boolean[] keyboard = new boolean[5];
	
	public Gun(Game game ,BufferedImage gun_image, boolean right_to_left) {
		this.game = game;
		this.shooter = game.shooter;
		this.gun_image = gun_image;
		this.width = gun_image.getWidth();
		this.height = gun_image.getHeight();
		this.right_to_left = right_to_left;
		updatePositions();
	}
	
	private void updatePositions() {
		shoot();
		if(shotFired == true && gunJumpDelayCounter < gunJumpDelay) {
			if(right_to_left == false) {
				this.x = shooter.getGunX(right_to_left) - gunJump;
			}else {
				this.x = shooter.getGunX(right_to_left) + gunJump;
			}
			gunJumpDelayCounter++;
		}else {
			shotFired = false;
			gunJumpDelayCounter = 0;
			this.x = shooter.getGunX(right_to_left);
		}
		this.y = shooter.getGunY();
	}
	
	private void shoot() {
		if(keyboard[0] == true) {
			long fireLength =  System.currentTimeMillis() - fireTime;
			if(fireLength > fireRateMili) {
				shotFired = true;
				shootSound();
				if(right_to_left == false) { 
					bullet[next_bullet] = new Bullet(game, this, right_to_left, x, y, x + bulletDeathArea, y); //TODO: Standardize
				}else {
					bullet[next_bullet] = new Bullet(game, this, right_to_left, x, y, x - bulletDeathArea, y);
				}
				next_bullet++;
				if(next_bullet == number_of_bullets) {
					next_bullet = 0;
				}
				fireTime = System.currentTimeMillis();
			}
		}
	}
	
	public void turn(boolean right_to_left) {
		this.right_to_left = right_to_left;
		if(right_to_left) this.gun_image = shooter.gun_image2;
		else this.gun_image = shooter.gun_image;
	}
	
	private void shootSound() {
		try {
			Sound.Gun1.play();
		}catch (Exception e) {
			System.out.println("Error loading music");
		}
	}
	
	public void paint(Graphics2D g2d) {
		updatePositions();
		for(int i = 0; i < number_of_bullets; i++) {
			if(bullet[i] != null) {
				bullet[i].paint(g2d);
			}
		}
		g2d.drawImage(gun_image, (int) x, (int) y, null);
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keyboard[0] = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keyboard[0] = false;
		}
	}
	
	public int getGunWidth() {
		return this.width;
	}
	public int getGunHeight() {
		return this.height;
	}
	
}
