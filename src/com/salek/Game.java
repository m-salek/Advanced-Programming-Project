package com.salek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel {

	boolean play;;

	final int WIDTH = 800;
	final int HEIGHT = 600;
	final int GROUND = 100;
	final int CEIL = 60;
	final int EARTH = HEIGHT - GROUND;
	
    long startTime;
    long gameTime;
    final long miliSec = 1000L;
    final int fps = 60;
    final int updateTime = (int) (miliSec / fps);
	
	private BufferedImage skyImage, groundImage, frameImage;
    
	Gun gunShooter;
	Shooter shooter;
	Cloud cloud;
	AirPlane airplane;
	CenterCollision centerCollision;
	CenterEnemy centerEnemy;
	CenterBullet centerBullet;
	
	public static final int deathArea = 1000;
		
		
	public Game() {
		this.play = true;
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				shooter.keyReleased(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				shooter.keyPressed(e);
			}
			@Override
			public void keyTyped(KeyEvent e) { }
		});
		
		setFocusable(true);
	}
	
	void loadContent() {
		try {
			Image.load();
			Sound.load();
			
			this.frameImage = Image.frameImage;
			this.skyImage = Image.skyImage;
			this.groundImage = Image.groundImage;
			
		}catch (Exception e) {
			System.out.println("Error Loading Content:");
			System.out.println(e);
			System.exit(1);
		}
	}
	
	void action() {
		airplane.action();
		cloud.action();
		shooter.action();
	}
	
	void musicPlay() {
		try {
//			Sound.Background.loop();
		}catch (Exception e) {
			System.out.println("Error loading music");
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(skyImage, 0, 0, null);
		g2d.drawImage(groundImage, 0, EARTH, null);
		airplane.paint(g2d);
		cloud.paint(g2d);
		shooter.paint(g2d);
		g2d.drawImage(frameImage, 0, 0, null);
	}
}
