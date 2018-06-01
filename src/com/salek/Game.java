package com.salek;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	final int width = 800;
	final int height = 600;
	final int ground = 113;
	final int ceil = 60;
	
    private long startTime;
    private long gameTime;
    private final long miliSec = 1000L;
    private final int fps = 60;
    private final int updateTime = (int) (miliSec / fps);
    
    
	private final URL address_background_image = getClass().getResource("/background2.png");

	
	private BufferedImage background;
	private BufferedImage head_image;
	private BufferedImage body_image;
	private BufferedImage gun_image;
	
	private Shooter shooter;
	private Cloud cloud;
		
		
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
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.drawImage(background, 0, 0, null);
		cloud.paint(g2d);
		shooter.paint(g2d);
	}

	private void move() {
		cloud.move();
		shooter.move();
	}
	
	private void loadImages() {
		try {
			background = ImageIO.read(address_background_image);

			shooter.loadImage();
			cloud.loadImage();
			
		}catch (Exception e) {
			System.out.println("Error loading the pics:");
			System.out.println(e);
			System.exit(0);
		}
	}
	
	private void musicPlay() {
		try {
//			Sound.Background.loop();
		}catch (Exception e) {
			System.out.println("Error loading music");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Game game = new Game();
		game.shooter = new Shooter(game);
		game.cloud = new Cloud(game);
		
		game.loadImages();
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
			while (true) {
				game.startTime = System.nanoTime();
				game.move();
				game.repaint();
				game.gameTime = (int) Math.ceil((double) (System.nanoTime() - game.startTime)/1000000);
	
//				System.out.println(game.updateTime - game.gameTime);
				Thread.sleep(game.updateTime - game.gameTime);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
