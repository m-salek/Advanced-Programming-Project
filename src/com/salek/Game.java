package com.salek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	public static boolean play = true;

	final int width = 800;
	final int height = 600;
	final int ground = 100;
	final int ceil = 60;
	final int earth = height - ground;
	
    private long startTime;
    private long gameTime;
    private final long miliSec = 1000L;
    private final int fps = 60;
    private final int updateTime = (int) (miliSec / fps);
    
	private final URL address_ground = getClass().getResource("/Ground2.png");
	private final URL address_sky = getClass().getResource("/Sky1.png");
	private final URL address_frame = getClass().getResource("/Frame.png");

	private BufferedImage skyImage, groundImage, frameImage;
	
	Shooter shooter;
	private Cloud cloud;
	private AirPlane airplane;
	CenterCollision centerCollision;
	
	public static final int deathArea = 1000;
		
		
	public Game() {
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
	
	private void loadContent() {
		try {
			skyImage = ImageIO.read(address_sky);
			groundImage = ImageIO.read(address_ground);
			frameImage = ImageIO.read(address_frame);
			Sound.loadSound();
			shooter.loadImage();
			cloud.loadImage();
			airplane.loadImage();
		}catch (Exception e) {
			System.out.println("Error Loading Content:");
			System.out.println(e);
			System.exit(0);
		}
	}
	
	private void action() {
		airplane.action();
		cloud.action();
		shooter.action();
	}
	
	private void musicPlay() {
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
		g2d.drawImage(groundImage, 0, earth, null);
		airplane.paint(g2d);
		cloud.paint(g2d);
		shooter.paint(g2d);
		g2d.drawImage(frameImage, 0, 0, null);
	}

	public static void main(String[] args) throws InterruptedException {
		
		Game game = new Game();
		game.shooter = new Shooter(game);
		game.cloud = new Cloud(game);
		game.airplane = new AirPlane(game);
		game.centerCollision = new CenterCollision(game, game.shooter);
		
		game.loadContent();
		game.musicPlay();
		
		JFrame frame = new JFrame();
		frame.setSize(game.width, game.height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("The Shooter Game");
		frame.add(game);
		frame.setVisible(true);
		
		try { 
			while(play) {
				game.startTime = System.nanoTime();				
				game.action();
				game.repaint();
				game.gameTime = (int) Math.ceil((double) (System.nanoTime() - game.startTime)/1000000);
	
//				System.out.println(game.updateTime - game.gameTime);
				Thread.sleep(game.updateTime - game.gameTime);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
