package com.salek;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AirPlane {
	
	private Game game;
	private Random r = new Random();
	
	private BufferedImage image, image_rl, image_lr;
	private boolean rl;	
	private double x, y;
	private final double speed = 5;  
	private int leftBorder;
	private int rightBorder;
	private boolean newPlane = true;
	
	private long lastPlaneTime; 
	private long nextPlaneTime;
	private int minimumTimeForNextPlane = 30000;
	private int maxRandomTimePlusForNextPlane = 30000;
	
	
	public AirPlane(Game game) {	
		this.game = game;
		
		image_rl = Image.plane1_rl;
		image_lr = Image.plane1_lr;
		
		x = leftBorder = -1 * game.WIDTH/2;
		rightBorder = game.WIDTH + game.WIDTH/2;
		nextPlaneTime = System.currentTimeMillis() + minimumTimeForNextPlane;
	}
	
	public void action() {
		
		if(newPlane == true && System.currentTimeMillis() > nextPlaneTime) {
			if((r.nextInt(2))%2 == 0) {
				rl = true;
			}else {
				rl = false;
			}
			if(rl == true) {
				image = image_rl;
				x = rightBorder;
			}else {
				image = image_lr;
				x =	leftBorder;
			}
			y = game.CEIL + r.nextInt(20);
			newPlane = false;
			lastPlaneTime = System.currentTimeMillis();
			nextPlaneTime = lastPlaneTime + (r.nextInt(maxRandomTimePlusForNextPlane) + minimumTimeForNextPlane);
		}
		
		if(rl == true) {
			x -= speed;
		}else {
			x += speed;
		}
		
		if((rl == true && x < leftBorder) || (rl == false && x > rightBorder)) {
			newPlane = true;
		}
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, null);
	}
}




