package com.salek;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;


public class CenterEnemy {
	
	private int number = 5;
	private Game game;
	
	private List<EnemyABS> arrayEnemy= new LinkedList<>();
	
	public CenterEnemy(Game game){
		this.game = game;
		initializeArrays();
	}
	
	// TABADOLE DATA ONLY BEINE CENTER HA:
	private void collision() {
		//checks shooter jump on enemy:
		//TODO: shooter		
		//TODO: bullet
		//TODO: borders
	}
	
	private void initializeArrays() {
		for(int i = 0; i < number; i++) {
			//arrayEnemy.add(new Enemy1(game, false)); //TODO: fix (game, false)
		}
	}
	public void kill(EnemyABS enemy) {
		arrayEnemy.remove(enemy);
		//TODO: add more: what happens when dies
	}
	
	public void paint(Graphics2D g2d) {
		for(EnemyABS e:arrayEnemy) {
			e.paint(g2d);
		}
	}

}
