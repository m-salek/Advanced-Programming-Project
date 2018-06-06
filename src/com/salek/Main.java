package com.salek;

import javax.swing.JFrame;

public class Main {
	
	
	public static void main(String[] args) {
			
		Game game = new Game();
		game.loadContent();
		game.centerCollision = new CenterCollision(game);
		game.centerEnemy = new CenterEnemy(game);
		game.centerBullet = new CenterBullet(game);
		game.shooter = new Shooter(game);
		game.gunShooter = new Gun(game);
		game.cloud = new Cloud(game);
		game.airplane = new AirPlane(game);
//		game.musicPlay();
		
		JFrame frame = new JFrame();
		frame.setSize(game.WIDTH, game.HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("The Shooter Game");
		frame.add(game);
		frame.setVisible(true);
		
		try { 
			while(game.play) {
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
