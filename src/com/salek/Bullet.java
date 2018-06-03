package com.salek;

import java.awt.Graphics2D;

public class Bullet {
	
	private Game game;
	private int gunWidth;
	double x, y;
	double destination_x;
	double destination_y;
	double width = 7;
	double height = 3;
	double speed = 8;
	boolean right_to_left;
	private final int bulletDeathArea = Game.deathArea; 
	
	private int bulletStatus; 
	
	public Bullet(Game game, Gun gun, boolean right_to_left, double gunX, double gunY, double destinyX, double destinyY) {
		this.game = game;
		this.gunWidth = gun.getGunWidth();
		this.x = game.shooter.getGunX(right_to_left);
		this.y = game.shooter.getGunY();
		this.destination_x = destinyX;
		this.destination_y = destinyY;
		if(right_to_left) {
			this.right_to_left = true;
		}else {
			this.right_to_left = false;
			//fixes self shot:
			this.x += this.gunWidth;
		}
	}
	
	private void shotFired(){
		//TODO: do sth with bulletStatus
		bulletStatus = game.centerCollision.doesBulletCollideWithShooter(this);
		if(bulletStatus != 0) {
			//TODO:
			System.out.println(bulletStatus);
			x = bulletDeathArea;
			y = bulletDeathArea;
		}
		if(right_to_left == false && x < destination_x) {
			x += speed;
		}else if(right_to_left == true && x > destination_x) {
			x -= speed;
		}
	}
	
	public void paint(Graphics2D g2d) {
		g2d.fillOval((int) x,(int) y,(int) width,(int) height);
		shotFired();
	}
}



