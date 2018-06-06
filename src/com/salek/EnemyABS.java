package com.salek;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class EnemyABS {
	
	public abstract void action(); //TODO: PUT IN GAME
	
	private double body_X, body_Y, head_X, head_Y;
	private int body_width, body_height, head_width, head_height;
	private BufferedImage bodyImage;
	protected BufferedImage body_image_rl;
	protected BufferedImage body_image_lr;
	protected BufferedImage headImage;
	protected BufferedImage head_image_rl;
	protected BufferedImage head_image_lr;
	private boolean right_to_left;
	
	private Game game;
	Shooter shooter;
	
	public EnemyABS(Game game, boolean right_to_left) {
		this.game = game;
		this.shooter = game.shooter;
		this.right_to_left = right_to_left;
		initialize();
	}
	
	private void initialize() {
		body_Y = game.EARTH;
		if(right_to_left) {
			bodyImage = body_image_rl;
			headImage = head_image_rl;
			body_X = game.WIDTH + bodyImage.getWidth()*1.5;
		}else {
			bodyImage = body_image_lr;
			headImage = head_image_lr;
			body_X = -1 * bodyImage.getWidth()*1.5;
		}
		this.body_width = this.bodyImage.getWidth();
    	this.body_height = this.bodyImage.getHeight();
    	this.head_width = this.headImage.getWidth();
    	this.head_height = this.headImage.getHeight();
	}

	public void paint(Graphics2D g2d) {
		g2d.drawImage(bodyImage,(int) body_X,(int) body_Y, null);
		g2d.drawImage(headImage, (int) head_X, (int) head_Y, null);
	}	
	
	public int getPositionX() {
		// top left body
		return (int) this.body_X;
	}
	public int getPositionY() {
		return (int) this.body_Y;
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
	
}
