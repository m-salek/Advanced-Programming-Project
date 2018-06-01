package com.salek;

import java.awt.Graphics2D;

public class Bullet {
	
	double x;
	double y;
	double destination_x;
	double destination_y;
	double speed = 8;
	
	public Bullet(double gunX, double gunY, double destinyX, double destinyY) {
		this.x = gunX;
		this.y = gunY;
		this.destination_x = destinyX;
		this.destination_y = destinyY;
	}
	
	private void shotFired(){
		if(x < destination_x) {
			x += speed;
		}
	}
	
	public void paint(Graphics2D g2d) {
		g2d.fillOval((int) x,(int) y, 7, 3);
		shotFired();
	}
}



