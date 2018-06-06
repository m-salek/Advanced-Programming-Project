package com.salek;

import java.awt.image.BufferedImage;

public class Enemy1 extends EnemyABS{
	
	private BufferedImage e1_bodyrl, e1_bodylr, e1_headrl, e1_headlr;

	public Enemy1(Game game, boolean right_to_left) {
		super(game, right_to_left);
		loadImage();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	private void loadImage() {
		e1_bodyrl = Image.e1_bodyrl;
		e1_bodylr = Image.e1_bodylr;
		e1_headrl = Image.e1_headrl;
		e1_headlr = Image.e1_headlr;
	}
	

}

