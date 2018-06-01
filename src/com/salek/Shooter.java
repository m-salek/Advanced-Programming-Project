package com.salek;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Shooter {

	private Game game;
	private Gun gun;

	private BufferedImage body_image;
	private BufferedImage head_image;
	private BufferedImage gun_image;
	private int body_width;
	private int body_height;
	private int head_width;
	private int head_height;
	
	private final URL address_body_image = getClass().getResource("/body.png");
	private final URL address_head_image = getClass().getResource("/head.png");
	private final URL address_gun_image = getClass().getResource("/gun1.png");

	private double y;
	private double x;
	private double velocity_x = 0;
	private double velocity_y = 0;
	private double maxRunSpeed = 2;
	private double maxJumpSpeed = 12;
	private int jumpPower =  110;
	private int earth;
	private boolean reachedTheSky = false;

	private int[] keyboard = new int[5];
	
	
	public Shooter(Game game) {
		this.game = game;	
	}
	
	private void initialize() {
		this.gun = new Gun(this, gun_image);
		this.body_width = this.body_image.getWidth();
    	this.body_height = this.body_image.getHeight();
    	this.head_width = this.head_image.getWidth();
    	this.head_height = this.head_image.getHeight();
    	        
    	x = game.width/2 - body_width/2;
        earth = game.height - (body_height + game.ground);
        y = earth;
	}
	
	public void move() {
		if ((x + velocity_x > 0) && (x + velocity_x < (game.width - body_width)))
			x += velocity_x;
		
		if(velocity_y > 0 && !reachedTheSky) {
			if(y > earth-jumpPower) {
				move_Up();
			}else {
				reachedTheSky = true;
			}
		}
		if(reachedTheSky) {
			move_Down();
			if(y >= earth) {
				reachedTheSky = false;
				velocity_y = 0;
			}
		}
	}
	private void move_Up() {
		setVelocity();
		y -= velocity_y;
	}
	private void move_Down() {
		setVelocity();
		velocity_y *= 1.5; //This is more desired
		y += velocity_y ;
		if(y >= earth) y = earth;
	}
	
	
	private void setVelocity() {
		
		int currentPosition = (int) (earth - y);
		double subtractSpeed = (maxJumpSpeed * ((double) currentPosition/(jumpPower+1)));
		
		// limit decimals to fewer places
		long factor = (long) Math.pow(10, 3) ;
		subtractSpeed = subtractSpeed * factor;
		long temp = Math.round(subtractSpeed);
		subtractSpeed = (double) temp / factor;
		
		//speed limit to avoid delay in higher places
		double speedLimit = (double) 90/100 * maxJumpSpeed;
		if(subtractSpeed >= speedLimit) {
			subtractSpeed = speedLimit;
		}
		
		velocity_y = maxJumpSpeed - subtractSpeed;
	}
	
	public void loadImage() throws IOException {
		head_image = ImageIO.read(address_head_image);
		body_image = ImageIO.read(address_body_image);
		gun_image = ImageIO.read(address_gun_image);
		
		initialize();
	}
	
	public int getPositionX() {
		return (int) this.x;
	}
	public int getPositionY() {
		return (int) this.y;
	}
	public int getBodyWidth() {
		return this.body_width;
	}
	public int getBodyHeight() {
		return this.body_height;
	}
	public int getGunX() {
		return getPositionX() + getBodyWidth()/2 + 8; //TODO: Standardize this beautiful
	}
	public int getGunY() {
		return getPositionY() + getBodyHeight()/2 - 8;
	}

	
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(body_image,(int) x,(int) y, null);
		g2d.drawImage(head_image,(int) x + (body_width-head_width)/2,(int) y - head_height, null);
		this.gun.paint(g2d);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			keyboard[0] = 0;
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			keyboard[1] = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			keyboard[2] = 0;
		}
		
		//fixes keyboard bug: holding multiple keys issue
		if(keyboard[0] == 0 && keyboard[1] == 0) {
			velocity_x = 0;
		}else if(keyboard[0] == 0) {
			velocity_x =  maxRunSpeed;
		}else if(keyboard[1] == 0) {
			velocity_x = -1 * maxRunSpeed;
		}
		if(keyboard[2] == 1 && velocity_y == 0) {
			velocity_y = 1;
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		gun.keyPressed(e);
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			velocity_x = -1 * maxRunSpeed;
			keyboard[0] = 1;
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			velocity_x = maxRunSpeed;
			keyboard[1] = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			velocity_y = 1;
			keyboard[2] = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		//fixes bug: holding multiple keys issue
		if(keyboard[2] == 1 && velocity_y == 0) {
			velocity_y = 1;
		}

	}		
}