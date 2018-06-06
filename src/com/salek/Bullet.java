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
	private boolean isDead = false; 
		
	public Bullet(Game game, Gun gun, boolean right_to_left, double destinyX, double destinyY) {
		this.game = game;
		this.gunWidth = gun.getGunWidth();
		if(!right_to_left) this.x = game.shooter.getGunX(right_to_left, gun);
		else this.x = game.shooter.getGunX(right_to_left, gun);
		this.y = game.shooter.getGunY();
		this.destination_x = destinyX;
		this.destination_y = destinyY;
		if(right_to_left) {
			this.right_to_left = true;
		}else {
			this.right_to_left = false;
			this.x += this.gunWidth; //fixes self shot
		}
	}
	
	public void kill() {
		isDead = true;
		x = bulletDeathArea;
		y = bulletDeathArea;
	}
	
	private void shotFired(){
		//TODO: do sth with bulletStatus
		game.centerCollision.bulletCollidingWithShooter(this);
		if(!isDead) {
			if(right_to_left == false && x < destination_x) {
				x += speed;
			}else if(right_to_left == true && x > destination_x) {
				x -= speed;
			}else {
				kill();
			}
		}
	}
	
	public void paint(Graphics2D g2d) {
		if(!isDead) {
			g2d.fillOval((int) x,(int) y,(int) width,(int) height);
			shotFired();
		}
	}
	
	public int getX() {
		return (int) this.x;
	}
	public int getY() {
		return (int) this.y;
	}
	public boolean isBulletDead() {
		return isDead;
	}
}



