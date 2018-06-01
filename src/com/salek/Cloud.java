package com.salek;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

public class Cloud {
	
	private Game game;
	
	private Random r = new Random();
	
	private int leftBorder;
	private int rightBorder;
	private double speed = 0.7;
	private double speedMin = 0.2;
	private double speedMax = 0.5;
	private int number_of_cloud = 6;
	private BufferedImage[] images = new BufferedImage[number_of_cloud];
	private BufferedImage[] images_clone = new BufferedImage[number_of_cloud];

	private double[][] position = new double[number_of_cloud][2];
	private double[] velocity = new double[number_of_cloud];
	
	private final URL url1 = getClass().getResource("/cloud1.png");
	private final URL url2 = getClass().getResource("/cloud2.png");
	private final URL url3 = getClass().getResource("/cloud3.png");
	private final URL url4 = getClass().getResource("/cloud4.png");
	private final URL url5 = getClass().getResource("/cloud5.png");

	public Cloud(Game game) {
		
		this.game = game;
		leftBorder = -1 * game.width/2;
		rightBorder = game.width + game.width/2;
		
		//initilizing positions
		position[0][0] = game.width/3;
		position[0][1] = game.ceil;
		velocity[0] = speed + (speedMin + (speedMax - speedMin) * r.nextDouble());
		for(int i = 1; i < number_of_cloud; i++) {
			position[i][0] = position[i - 1][0] + (r.nextInt(250) + 200);
			position[i][1] = game.ceil + (r.nextInt(50) + 20);
			velocity[i] = speed + (speedMin + (speedMax - speedMin) * r.nextDouble());

		}
	}
	
	public void loadImage() throws IOException {
		images[0] = ImageIO.read(url1);
		images[1] = ImageIO.read(url2);
		images[2] = ImageIO.read(url3);
		images[3] = ImageIO.read(url4);
		images[4] = ImageIO.read(url5);
		images[5] = ImageIO.read(url5);
		images_clone = images.clone();
	}
	
	public void move() {
		for(int i = 0; i < number_of_cloud; i++) {
			position[i][0] -= velocity[i];
			if(position[i][0] < leftBorder) {
				images[i] = images_clone[r.nextInt(5)];
				position[i][0] = rightBorder - r.nextInt(50);
				position[i][1] = game.ceil + (r.nextInt(50) + 20);
			}
		}
	}
	
	public void paint(Graphics2D g2d) {
		for(int i = 0; i < number_of_cloud; i++) {
			g2d.drawImage(images[i],(int) position[i][0],(int) position[i][1], null);
		}
	}
}
