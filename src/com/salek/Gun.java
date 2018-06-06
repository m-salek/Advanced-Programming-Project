package com.salek;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class Gun {
	
	private Game game;
	private Shooter shooter;
	private BufferedImage paintImage, gun_image, gun_image2;
	
	private double x;
	private double y;
	private int width, height;
	
	private long fireTime = System.currentTimeMillis();
	private int fireRateMili = 350;
	
	private List<Bullet> bulletList;
	
	private boolean shotFired = false;
	private final int  gunJump = 4;
	private final int  gunJumpDelay = 5;
	private int gunJumpDelayCounter = 0;
	private boolean right_to_left;
	
	private final int bulletDeathArea = Game.deathArea;
	
	private boolean[] keyboard = new boolean[5];
	
	public Gun(Game game) {
		this.game = game;
		this.shooter = game.shooter;
		
		paintImage = gun_image = Image.gun_image;
		gun_image2 = Image.gun_image2;
		
		this.width = gun_image.getWidth();
		this.height = gun_image.getHeight();
		this.right_to_left = false;
		
		updatePositions();
	}
	
	private void updatePositions() {
		if(shooter == null) {
			this.shooter = game.shooter;
		}
			
		bulletList = game.centerBullet.getListShooterBullet();
		if(keyboard[0] == true) {
			shoot();
		}
		if(shotFired == true && gunJumpDelayCounter < gunJumpDelay) {
			if(right_to_left == false) {
				this.x = shooter.getGunX(right_to_left, this) - gunJump;
			}else {
				this.x = shooter.getGunX(right_to_left, this) + gunJump;
			}
			gunJumpDelayCounter++;
		}else {
			shotFired = false;
			gunJumpDelayCounter = 0;
			this.x = shooter.getGunX(right_to_left, this);
		}
		this.y = shooter.getGunY();
		
		Shooter.gun_static = this; //give the shooter the gun
	}
	
	
	private void shoot() {
		long fireLength =  System.currentTimeMillis() - fireTime;
		if(fireLength > fireRateMili) {
			shotFired = true;
			shootSound();
			if(right_to_left == false) { 
				game.centerBullet.addShooterBullet(new Bullet(game, this, right_to_left, x + bulletDeathArea, y));
			}else {
				game.centerBullet.addShooterBullet(new Bullet(game, this, right_to_left, x - bulletDeathArea, y));
			}
			
			fireTime = System.currentTimeMillis();
		}
	}
	
	public void turn(boolean right_to_left) {
		this.right_to_left =  right_to_left;
		if(right_to_left) paintImage = gun_image2;
		else paintImage = gun_image;
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
		for(Bullet b:bulletList) {
			if(b != null) {
				b.paint(g2d);
			}
		}
		
		g2d.drawImage(paintImage, (int) x, (int) y, null);
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
