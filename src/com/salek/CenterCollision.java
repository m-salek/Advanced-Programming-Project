package com.salek;

public class CenterCollision {
	
	private Shooter shooter;
	
	private double bullet_X, bullet_Y;
	private double shooter_Body_X_Left, shooter_Body_X_Right,
					shooter_Body_Y_Top, shooter_Body_Y_Down;
	private double shooter_Head_X_Left, shooter_Head_X_Right,
					shooter_Head_Y_Top, shooter_Head_Y_Down;
	
	
	public CenterCollision(Game game, Shooter shooter) {
		this.shooter = shooter;
	}
	
	private void setShooterPosition(){
		shooter_Body_X_Left = shooter.getPositionX();
		shooter_Body_X_Right = shooter_Body_X_Left + shooter.getBodyWidth();
		shooter_Body_Y_Top = shooter.getPositionY();
		shooter_Body_Y_Down = shooter_Body_Y_Top + shooter.getBodyHeight();
		
		shooter_Head_X_Left = shooter.getHeadPositionX();
		shooter_Head_X_Right = shooter_Head_X_Left + shooter.getHeadWidth();
		shooter_Head_Y_Top = shooter.getHeadPositionY();
		shooter_Head_Y_Down = shooter_Head_Y_Top + shooter.getHeadHeight();

	}
	
	public int doesBulletCollideWithShooter(Bullet bullet) {
		// have put in Bullet class.
		// details:
		// 0: nope
		// 1: body collided
		// 2: head collided
		
		bullet_X = bullet.x + bullet.width; //tip of the bullet
		bullet_Y = bullet.y + bullet.height / 2; //exactly the tip
		setShooterPosition();
		
		if(bullet_X >= shooter_Body_X_Left && bullet_X <= shooter_Body_X_Right) {
			if(bullet_Y >= shooter_Body_Y_Top && bullet_Y <= shooter_Body_Y_Down) {
				return 1;
			}
		}
		if(bullet_X >= shooter_Head_X_Left && bullet_X <= shooter_Head_X_Right) {
			if(bullet_Y >= shooter_Head_Y_Top && bullet_Y <= shooter_Head_Y_Down) {
				return 2;
			}
		}
		
		return 0;
	}
}
