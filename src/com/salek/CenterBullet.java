package com.salek;

import java.util.LinkedList;
import java.util.List;

public class CenterBullet {
	
	private final int MAXCLEARLIMIT = 10;

	private List<Bullet> arrayShooterBullet= new LinkedList<>();
	private List<Bullet> arrayEnemyBullet= new LinkedList<>();
	
	public CenterBullet(Game game) {
	}
	
	private void deleteDeadFromList(List<Bullet> lst) {
		for(int i = 0; i < lst.size(); i++) {
			Bullet bullet = lst.get(i);
			if(bullet.isBulletDead()) {
				lst.remove(i);
			}
		}
	}
	
	public void addShooterBullet(Bullet b) {
		if(arrayShooterBullet.size() > MAXCLEARLIMIT) {
			deleteDeadFromList(arrayShooterBullet);
		}
		arrayShooterBullet.add(b);
	}
	public void addEnemyBullet(Bullet b) {
		if(arrayEnemyBullet.size() > MAXCLEARLIMIT) {
			deleteDeadFromList(arrayEnemyBullet);
		}
		arrayEnemyBullet.add(b);
	}
	
	public List<Bullet> getListShooterBullet() {
		return arrayShooterBullet;
	}
	public List<Bullet> getListEnemyBullet() {
		return arrayEnemyBullet;
	}
}
