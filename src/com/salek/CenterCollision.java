package com.salek;

public class CenterCollision {
	
	private Game game;
	
	private double bullet_X, bullet_Y;
	private double shooter_Body_X_Left, shooter_Body_X_Right,
					shooter_Body_Y_Top, shooter_Body_Y_Down,
					shooter_Head_X_Left, shooter_Head_X_Right,
					shooter_Head_Y_Top, shooter_Head_Y_Down;
	private double enemy_Body_X_Left, enemy_Body_X_Right,
					enemy_Body_Y_Top, enemy_Body_Y_Down,
					enemy_Head_X_Left, enemy_Head_X_Right,
					enemy_Head_Y_Top, enemy_Head_Y_Down;
	
	
	public CenterCollision(Game game) {
		this.game = game;
	}
	
	private void setShooterPosition(){
		shooter_Body_X_Left = game.shooter.getPositionX();
		shooter_Body_X_Right = shooter_Body_X_Left + game.shooter.getBodyWidth();
		shooter_Body_Y_Top = game.shooter.getPositionY();
		shooter_Body_Y_Down = shooter_Body_Y_Top + game.shooter.getBodyHeight();
		
		shooter_Head_X_Left = game.shooter.getHeadPositionX();
		shooter_Head_X_Right = shooter_Head_X_Left + game.shooter.getHeadWidth();
		shooter_Head_Y_Top = game.shooter.getHeadPositionY();
		shooter_Head_Y_Down = shooter_Head_Y_Top + game.shooter.getHeadHeight();
	}
	private void setEnemyPosition(EnemyABS enemy){
		enemy_Body_X_Left = enemy.getPositionX();
		enemy_Body_X_Right = enemy_Body_X_Left + enemy.getBodyWidth();
		enemy_Body_Y_Top = enemy.getPositionY();
		enemy_Body_Y_Down = enemy_Body_Y_Top + enemy.getBodyHeight();
		
		enemy_Head_X_Left = enemy.getHeadPositionX();
		enemy_Head_X_Right = enemy_Head_X_Left + enemy.getHeadWidth();
		enemy_Head_Y_Top = enemy.getHeadPositionY();
		enemy_Head_Y_Down = enemy_Head_Y_Top + enemy.getHeadHeight();
	}

	
	private int isBulletCollidingWithShooter(Bullet bullet) {
		// had put in Bullet class.
		// details:
		// 0: nope
		// 1: body collided
		// 2: head collided
		
		setShooterPosition();
		
		bullet_X = bullet.x + bullet.width; //tip of the bullet
		bullet_Y = bullet.y + bullet.height / 2; //exactly the tip
		
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
	
	private boolean isShooterJumpingOnEnemyHead(EnemyABS enemy) {
		
		if(game.shooter.isJumping() == false) {
			return false;
		}
		
		setShooterPosition();
		setEnemyPosition(enemy);
		
		if((shooter_Body_X_Left <= enemy_Head_X_Right) && (shooter_Body_X_Right >= enemy_Head_X_Left)) {
			if(shooter_Body_Y_Down >= enemy_Head_Y_Top){
				return true;
			}
		}
		return false;
	}
	
	public void bulletCollidingWithShooter(Bullet bullet) {
		if(isBulletCollidingWithShooter(bullet) != 0){
			bullet.kill();
		}
	}
	
	public void shooterJumpingOnEnemyHead(EnemyABS enemy) {
		if(isShooterJumpingOnEnemyHead(enemy)){
			game.centerEnemy.kill(enemy);
		}
	}
}
