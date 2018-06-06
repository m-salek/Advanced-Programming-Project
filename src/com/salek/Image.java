package com.salek;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image {
	
	static BufferedImage 
	//background
	skyImage,
	groundImage,
	frameImage,
	
	//shooter
	head_image, 
	body_image,
	gun_image, 
	gun_image2,
	
	//airplane
	plane1_rl,
	plane1_lr,
	
	//cloud
	cloud1,
	cloud2,
	cloud3,
	cloud4,
	cloud5,
	cloud6,
	
	//enemies
	e1_bodyrl,
	e1_bodylr,
	e1_headrl,
	e1_headlr
	
	;
	
	private final static URL adrs_skyImage = Image.class.getResource("/Sky1.png");
	private final static URL adrs_groundImage = Image.class.getResource("/Ground2.png");
	private final static URL adrs_frameImage = Image.class.getResource("/Frame.png");
	
	private final static URL adrs_head_image = Image.class.getResource("/head.png");
	private final static URL adrs_body_image = Image.class.getResource("/body.png");
	private final static URL adrs_gun_image = Image.class.getResource("/gun1.png");
	private final static URL adrs_gun_image2 = Image.class.getResource("/gun1_2.png");
	
	private final static URL adrs_plane1_rl =  Image.class.getResource("/airplane1_90x45.png");
	private final static URL adrs_plane1_lr =  Image.class.getResource("/airplane1_90x45_2.png");
	
	private final static URL adrs_cloud1 = Image.class.getResource("/cloud1.png");
	private final static URL adrs_cloud2 = Image.class.getResource("/cloud2.png");
	private final static URL adrs_cloud3 = Image.class.getResource("/cloud3.png");
	private final static URL adrs_cloud4 = Image.class.getResource("/cloud4.png");
	private final static URL adrs_cloud5 = Image.class.getResource("/cloud5.png");
	private final static URL adrs_cloud6 = Image.class.getResource("/cloud6.png");
	
	private final static URL adrs_e1body_lr = Image.class.getResource("/enemy1_body.png");
	private final static URL adrs_e1body_rl = Image.class.getResource("/enemy1_body.png");
	private final static URL adrs_e1head_lr = Image.class.getResource("/enemy1_head.png");
	private final static URL adrs_e1head_rl = Image.class.getResource("/enemy1_head.png");

	

	public static void load() throws IOException {
		skyImage = ImageIO.read(adrs_skyImage);
		groundImage = ImageIO.read(adrs_groundImage);
		frameImage = ImageIO.read(adrs_frameImage);
		
		head_image = ImageIO.read(adrs_head_image);
		body_image = ImageIO.read(adrs_body_image);
		gun_image = ImageIO.read(adrs_gun_image);
		gun_image2 = ImageIO.read(adrs_gun_image2);
		
		plane1_rl = ImageIO.read(adrs_plane1_rl);
		plane1_lr = ImageIO.read(adrs_plane1_lr);

		cloud1 = ImageIO.read(adrs_cloud1);
		cloud2 = ImageIO.read(adrs_cloud2);
		cloud3 = ImageIO.read(adrs_cloud3);
		cloud4 = ImageIO.read(adrs_cloud4);
		cloud5 = ImageIO.read(adrs_cloud5);
		cloud6 = ImageIO.read(adrs_cloud6);
		
		e1_bodyrl = ImageIO.read(adrs_e1body_lr);
		e1_bodylr = ImageIO.read(adrs_e1body_rl);
		e1_headrl = ImageIO.read(adrs_e1head_lr);
		e1_headlr = ImageIO.read(adrs_e1head_rl);

	}
	
}