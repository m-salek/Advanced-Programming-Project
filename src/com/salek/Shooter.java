package com.salek;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Shooter {

	private Game game;
	static Gun gun_static;
	private Gun gun;

	BufferedImage body_image, head_image;
	
	private int body_width;
	private int body_height;
	private int head_width;
	private int head_height;
	private int earth;

	private double y;
	private double x;
	private double head_X;
	private double head_Y;
	private double velocity_x = 0;
	private double velocity_y = 0;
	private final double runSpeed = 2;
	private final double maxJumpSpeed = 12;
	private final int jumpPower =  125;
	private final double desiredFallingSlowness = 1.5;
	private boolean reachedTheSky = false;
	private boolean right_to_left;
	private boolean jumping;
	
	private boolean[] keyboard = new boolean[10];
	
	
	public Shooter(Game game) {
		this.game = game;
		body_image = Image.body_image;
		head_image = Image.head_image;

		initializeMainGuy();
	}
	
	private void initializeMainGuy() {
		right_to_left = false;
		this.body_width = this.body_image.getWidth();
    	this.body_height = this.body_image.getHeight();
    	this.head_width = this.head_image.getWidth();
    	this.head_height = this.head_image.getHeight();
    	
    	x = game.WIDTH/2 - body_width/2;
        earth = game.EARTH - body_height;
        y = earth;
        head_X = x + (body_width-head_width)/2;
        head_Y = y - head_height;
	}
	
	
	private void turnSide() {
		if(this.gun != null) {
			if(right_to_left == true) {
				gun.turn(true);
			}else {
				gun.turn(false);
			}
		}
	}
	
	public void action() {
		if(gun == null) gun = gun_static;
		keyboardCheker();
		if ((x + velocity_x > 0) && (x + velocity_x < (game.WIDTH - body_width))) {	
			if(velocity_x < 0) right_to_left = true;
			else if(velocity_x > 0) right_to_left = false;
			turnSide();
			x += velocity_x;
		}
		
		if(velocity_y > 0 && !reachedTheSky) {
			if(y > earth-jumpPower) {
				setVelocity();
				y -= velocity_y;
			}else {
				reachedTheSky = true;
			}
		}
		else if(reachedTheSky) {
			setVelocity();
			velocity_y *= desiredFallingSlowness;
			y += velocity_y ;
			if(y >= earth) {
				y = earth;
				reachedTheSky = false;
				velocity_y = 0;
				jumping = false;
			}
		}
		head_X = x + (body_width-head_width)/2;
        head_Y = y - head_height;
	}

	
	private void setVelocity() {
		int currentPosition = (int) (earth - y);
		double subtractSpeed = (maxJumpSpeed * ((double) currentPosition/(jumpPower+1)));
		
		// limit decimals to fewer places
		long factor = (long) Math.pow(10, 3) ;
		subtractSpeed = subtractSpeed * factor;
		long temp = Math.round(subtractSpeed);
		subtractSpeed = (double) temp / factor;
		
		//speed limit to avoid *much delay in higher places
		double speedLimit = (double) 90/100 * maxJumpSpeed;
		if(subtractSpeed >= speedLimit) {
			subtractSpeed = speedLimit;
		}
		velocity_y = maxJumpSpeed - subtractSpeed;
	}
	
	private void keyboardCheker() {
		if(keyboard[0] == true) velocity_x = -1 * runSpeed;
		if(keyboard[1] == true) velocity_x = runSpeed;
		if(keyboard[2] == true) {
			jumping = true;
			velocity_y = 1;
		}
		if((keyboard[0] == false) && (keyboard[1] == false)) velocity_x = 0;
		if(keyboard[9] == true) System.exit(0);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(body_image,(int) x,(int) y, null);
		g2d.drawImage(head_image,(int) head_X,(int) head_Y, null);
		if(this.gun != null) this.gun.paint(g2d);
	}

	
	public void keyPressed(KeyEvent e) {
		if(this.gun != null) gun.keyPressed(e);
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keyboard[0] = true;
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keyboard[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			keyboard[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			keyboard[9] = true;
		}
		
	}
	public void keyReleased(KeyEvent e) {
		if(this.gun != null) gun.keyReleased(e);
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keyboard[0] = false;
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keyboard[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			keyboard[2] = false;
		}
	}
	
	public int getPositionX() {
		// top left body
		return (int) this.x;
	}
	public int getPositionY() {
		return (int) this.y;
	}
	public int getHeadPositionX() {
		// top left head
		return (int) this.head_X;
	}
	public int getHeadPositionY() {
		return (int) this.head_Y;
	}
	public int getBodyWidth() {
		return this.body_width;
	}
	public int getBodyHeight() {
		return this.body_height;
	}
	public int getHeadWidth() {
		return this.head_width;
	}
	public int getHeadHeight() {
		return this.head_height;
	}
	public int getGunX(boolean right_to_left, Gun gun) {
		if(right_to_left == false) {
			return getPositionX() + getBodyWidth()/2 + gun.getGunWidth()/2; //TODO: Standardize this beautiful
		}else {
			return getPositionX() - gun.getGunWidth()/2 - 6; //TODO: Standardize this beautiful too
		}
	}
	public int getGunY() {
		return getPositionY() + getBodyHeight()/2 - 8; //TODO: Standardize this beautiful also
	}
	public boolean isJumping() {
		return jumping;
	}

}