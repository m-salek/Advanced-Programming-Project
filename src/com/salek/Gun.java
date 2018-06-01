package com.salek;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Gun {
	
	private Shooter shooter;
	private BufferedImage gun_image;
	private double x;
	private double y;
	
	private long fireTime = System.currentTimeMillis();
	private int fireRateMili = 400;
	
	private int number_of_bullets = 7;
	private Bullet[] bullet = new Bullet[number_of_bullets];
	private int next_bullet = 0;
	
//	private boolean fireButtonPressed = false;

	public Gun(Shooter shooter ,BufferedImage gun_image) {
		this.shooter = shooter;
		this.gun_image = gun_image;
		updatePositions();
	}
	
	private void updatePositions() {
		this.x = shooter.getGunX();
		this.y = shooter.getGunY();
	}
	
	private void shoot() {
//		fireButtonPressed = true;
		long fireLength =  System.currentTimeMillis() - fireTime;
		if(fireLength > fireRateMili) {
			bullet[next_bullet] = new Bullet(x, y, x + 800, y);
			next_bullet++;
			if(next_bullet == number_of_bullets) {
				next_bullet = 0;
			}
			fireTime = System.currentTimeMillis();
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
	
	public void keyReleased(KeyEvent e) {
		//TODO
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			shoot();
		}		
	}
}
