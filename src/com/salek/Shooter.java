package com.salek;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class Shooter {

	private Game game;
	private Gun gun;

	private BufferedImage body_image;
	private BufferedImage head_image;
	BufferedImage gun_image, gun_image2;
	
	private int body_width;
	private int body_height;
	private int head_width;
	private int head_height;
	private int earth;
	
	private final URL address_body_image = getClass().getResource("/body.png");
	private final URL address_head_image = getClass().getResource("/head.png");
	private final URL address_gun_image = getClass().getResource("/gun1.png");
	private final URL address_gun_image2 = getClass().getResource("/gun1_2.png");

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
	
	private boolean canShootStatus;
	
	private boolean[] keyboard = new boolean[10];
	
	
	public Shooter(Game game) {
		this.game = game;
	}
	
	private void initializeMainGuy() {
		right_to_left = false;
		this.gun = new Gun(game, gun_image, right_to_left);
		this.body_width = this.body_image.getWidth();
    	this.body_height = this.body_image.getHeight();
    	this.head_width = this.head_image.getWidth();
    	this.head_height = this.head_image.getHeight();
    	
    	x = game.width/2 - body_width/2;
        earth = game.height - (body_height + game.ground);
        y = earth;
        head_X = x + (body_width-head_width)/2;
        head_Y = y - head_height;
        canShootStatus = true;
	}
	
	public void loadImage() throws IOException {
		head_image = ImageIO.read(address_head_image);
		body_image = ImageIO.read(address_body_image);
		gun_image = ImageIO.read(address_gun_image);
		gun_image2 = ImageIO.read(address_gun_image2);
		
		initializeMainGuy();
	}
	
	private void turnSide() {
		if(right_to_left == true) {
			gun.turn(true);
		}else {
			gun.turn(false);
		}
	}
	
	public void action() {
		
		keyboardCheker();
		if ((x + velocity_x > 0) && (x + velocity_x < (game.width - body_width))) {	
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
		if(keyboard[2] == true) velocity_y = 1;
		
		if((keyboard[0] == false) && (keyboard[1] == false)) velocity_x = 0;
		
		if(keyboard[9] == true) System.exit(0);
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(body_image,(int) x,(int) y, null);
		g2d.drawImage(head_image,(int) head_X,(int) head_Y, null);
		this.gun.paint(g2d);
	}

	
	public void keyPressed(KeyEvent e) {
		gun.keyPressed(e);
		
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
		gun.keyReleased(e);
		
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
	public int getGunX(boolean right_to_left) {
		if(right_to_left == false) {
			return getPositionX() + getBodyWidth()/2 + 8; //TODO: Standardize this beautiful
		}else {
			return getPositionX() - getBodyWidth()/2 + 3; //TODO: Standardize this beautiful too
		}
	}
	public int getGunY() {
		return getPositionY() + getBodyHeight()/2 - 8; //TODO: Standardize this beautiful also
	}
	public boolean getCanShootStatus() {
		return canShootStatus;
	}
}