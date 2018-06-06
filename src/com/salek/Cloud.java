package com.salek;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Cloud {
	
	private Game game;
		
	private Random r = new Random();
	
	private int leftBorder;
	private int rightBorder;
	private double speed = 0.2;
	private double speedMin = 0.1;
	private double speedMax = 0.2;
	private int number_of_cloud = 6;
	private BufferedImage[] images = new BufferedImage[number_of_cloud];
	private BufferedImage[] images_clone = new BufferedImage[number_of_cloud];

	private double[][] position = new double[number_of_cloud][2];
	private double[] velocity = new double[number_of_cloud];

	public Cloud(Game game) {
		this.game = game;
		leftBorder = -1 * game.WIDTH/2;
		rightBorder = game.WIDTH + game.WIDTH/2;
		
		images[0] = Image.cloud1;
		images[1] = Image.cloud2;
		images[2] = Image.cloud3;
		images[3] = Image.cloud4;
		images[4] = Image.cloud5;
		images[5] = Image.cloud6;
		images_clone = images.clone();
		
		//initilizing positions
		position[0][0] = game.WIDTH/3;
		position[0][1] = game.CEIL;
		velocity[0] = speed + (speedMin + (speedMax - speedMin) * r.nextDouble());
		for(int i = 1; i < number_of_cloud; i++) {
			position[i][0] = position[i - 1][0] + (r.nextInt(250) + 200);
			position[i][1] = game.CEIL + (r.nextInt(50) + 20);
			velocity[i] = speed + (speedMin + (speedMax - speedMin) * r.nextDouble());

		}
	}
	
	public void action() {
		for(int i = 0; i < number_of_cloud; i++) {
			position[i][0] -= velocity[i];
			if(position[i][0] < leftBorder) {
				images[i] = images_clone[r.nextInt(5)];
				position[i][0] = rightBorder - r.nextInt(50);
				position[i][1] = game.CEIL + (r.nextInt(50) + 20);
			}
		}
	}
	
	public void paint(Graphics2D g2d) {
		for(int i = 0; i < number_of_cloud; i++) {
			g2d.drawImage(images[i],(int) position[i][0],(int) position[i][1], null);
		}
	}
}
